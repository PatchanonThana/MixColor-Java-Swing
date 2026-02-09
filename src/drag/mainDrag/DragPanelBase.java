package drag.mainDrag;

import listener.ComponentOfLayer;
import window.windowComponent.Middle.MiddlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public abstract class DragPanelBase extends JPanel implements ComponentOfLayer {

    final protected Dimension thisSize = new Dimension(100,100);

    protected int maxX;
    protected int maxY;

    protected MiddlePanel target;
    protected Container prevParent;

    public Point startLocation;

    public DragPanelBase(Color color, Point startLocation, MiddlePanel target ) {

        this.target = target;
        this.startLocation = startLocation;

        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,4));

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setThisLocation(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                Point mouse = e.getLocationOnScreen();
                Rectangle targetBounds = target.getBounds();

                if (targetBounds.contains(mouse)) {
                    removeSelf();
                    JPanel color = new JPanel();
                    color.setBackground(getBackground());
                    target.add(color);
                }
                else {
                    setLocation(startLocation);
                }

            }

        }) ;

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setThisLocation(e);
            }
        });


    }

    @Override
    public void layerResized(Dimension size) {
        setBounds(
                startLocation.x,
                startLocation.y,
                thisSize.width,
                thisSize.height
        );

        prevParent = getParent();

        maxX = getParent().getWidth() - getWidth();
        maxY = getParent().getHeight() - getHeight();
    }

    protected void setThisLocation(MouseEvent e) {

        Point mouse = e.getLocationOnScreen();
        Point parent = getParent().getLocationOnScreen();

        int x = mouse.x - parent.x - getWidth()/2;
        int y = mouse.y - parent.y - getHeight()/2;

        if (x < 0) x = 0;
        if (y < 0) y = 0;
        if (x > maxX) x = maxX;
        if (y > maxY) y = maxY;

        setLocation(x, y);


    }

    protected void removeSelf() {
        getParent().remove(this);
        prevParent.revalidate();
        prevParent.repaint();
    }

}
