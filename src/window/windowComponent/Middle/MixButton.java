package window.windowComponent.Middle;

import drag.floatDrag.*;
import drag.mainDrag.DragPanelBase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MixButton extends JLabel implements MiddlePanelListener {

    final private Dimension size = new Dimension(100,40);
    final private MixProgressBar mixProgressBar;
    private Container middlePanel;
    private List<DragPanelBase> dragList;
    private boolean isMixing = false;


    public MixButton(MixProgressBar mixProgressBar) {

        this.mixProgressBar = mixProgressBar;

        setText("MIX");
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(128,128,128),3));
        setEnabled(false);

        Timer checkComponent = new Timer(100, e -> {

            Container parent = MixButton.this.getParent();
            if (parent == null || !parent.isDisplayable()) {
                ((Timer)e.getSource()).stop();
                return;
            }

            if (middlePanel == null) return;

            if (!isMixing) {
                int count = middlePanel.getComponentCount();
                if (count <= 1 && MixButton.this.isEnabled()) {
                    MixButton.this.setEnabled(false);
                } else if (count > 1 && !MixButton.this.isEnabled()) {
                    MixButton.this.setEnabled(true);
                    MixButton.this.setForeground(Color.WHITE);
                    MixButton.this.repaint();
                }
            }
        });
        checkComponent.start();

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                if (!MixButton.this.isEnabled()) return;
                isMixing = true;
                MixButton.this.setEnabled(false);
                MixButton.this.mixProgressBar.setVisible(true);

                SwingWorker<Void, Integer> worker = new SwingWorker<>() {

                    int process = 0;
                    final int max = MixButton.this.mixProgressBar.getMaximum();

                    @Override
                    protected Void doInBackground() throws InterruptedException{
                        while (process < max) {
                            publish(process);
                            Thread.sleep(50);
                            process++;
                        }

                        return null;
                    }
                    @Override
                    protected void process(List<Integer> chunks) {
                        int curProcess = chunks.get(chunks.size()-1);
                        mixProgressBar.setValue(curProcess);
                    }
                    @Override
                    protected void done() {

                        boolean haveRed = false;
                        boolean haveYellow = false;
                        boolean haveBlue = false;

                        for (Component c : middlePanel.getComponents()){
                            if (!haveRed) haveRed = c.getBackground() == Color.RED;
                            if (!haveBlue) haveBlue = c.getBackground() == Color.BLUE;
                            if (!haveYellow) haveYellow = c.getBackground() == Color.YELLOW;

                        }

                        middlePanel.removeAll();

                        FloatDragBase dragResult = result(haveRed, haveBlue, haveYellow);
                        MixButton.this.getParent().add(dragResult, JLayeredPane.DRAG_LAYER);

                        for (DragPanelBase d : dragList) {
                            MixButton.this.getParent().add(d);
                            d.setLocation(d.startLocation);
                            d.setSize(new Dimension(100,100));
                        }

                        mixProgressBar.setVisible(false);
                        mixProgressBar.setValue(0);
                        isMixing = false;

                        middlePanel.repaint();
                        middlePanel.revalidate();

                        MixButton.this.getParent().repaint();
                        MixButton.this.getParent().revalidate();
                    }
                };
                worker.execute();



            }

        });

    }

    @Override
    public void middlePanelResized(Container middlePanel) {
        this.middlePanel = middlePanel;
        Rectangle middlePanelRec = middlePanel.getBounds();
        setBounds(middlePanelRec.x + middlePanelRec.width/2 - size.width/2,
                middlePanelRec.y + middlePanelRec.height + 50,
                size.width,
                size.height
        );
    }

    public void setDragList(List<DragPanelBase> dragList) {
        this.dragList = dragList;
    }

    private FloatDragBase result(boolean red, boolean blue, boolean yellow) {

        if (red && blue && yellow) return new DarkBrownPanel();
        else if (red && blue) return new PurplePanel();
        else if (red && yellow) return new OrangePanel();
        else {
            return new GreenPanel();
        }

    }
}
