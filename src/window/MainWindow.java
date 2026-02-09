package window;

import drag.mainDrag.BluePanel;
import drag.mainDrag.DragPanelBase;
import drag.mainDrag.RedPanel;
import drag.mainDrag.YellowPanel;
import listener.ComponentOfLayer;
import window.windowComponent.*;
import window.windowComponent.Middle.MiddlePanel;
import window.windowComponent.Middle.MiddlePanelListener;
import window.windowComponent.Middle.MixButton;
import window.windowComponent.Middle.MixProgressBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;


public class MainWindow extends JFrame {

    JLayeredPane layer;

    public MainWindow() {

        defaultSetting();

        ExitButton exit = new ExitButton(this);
        layer.add(exit, JLayeredPane.MODAL_LAYER);

        Background background = new Background();
        layer.add(background, JLayeredPane.DEFAULT_LAYER);

        MiddlePanel middlePanel = new MiddlePanel();
        layer.add(middlePanel, JLayeredPane.PALETTE_LAYER);

        MixProgressBar mixProgressBar = new MixProgressBar();
        layer.add(mixProgressBar, JLayeredPane.MODAL_LAYER);

        MixButton mixButton = new MixButton(mixProgressBar);
        layer.add(mixButton, JLayeredPane.MODAL_LAYER);

        middlePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                for (Component c: layer.getComponents()) {
                    if (c instanceof MiddlePanelListener r) {
                        r.middlePanelResized(middlePanel);
                    }
                }
            }
        });

        RedPanel redPanel = new RedPanel(middlePanel);
        layer.add(redPanel, JLayeredPane.DRAG_LAYER);

        BluePanel bluePanel = new BluePanel(middlePanel);
        layer.add(bluePanel, JLayeredPane.DRAG_LAYER);

        YellowPanel yellowPanel = new YellowPanel(middlePanel);
        layer.add(yellowPanel, JLayeredPane.DRAG_LAYER);

        List<DragPanelBase> dragList = new ArrayList<>();
        dragList.add(redPanel);
        dragList.add(bluePanel);
        dragList.add(yellowPanel);
        mixButton.setDragList(dragList);

        addComponentListener(new ComponentAdapter(){
           @Override
           public void componentResized(ComponentEvent e) {

               Dimension size = layer.getSize();

               for (Component c: layer.getComponents()) {
                   if (c instanceof ComponentOfLayer r) {
                       r.layerResized(size);
                   }
               }

           }
        });

        setVisible(true);

    }


    private void defaultSetting() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        layer = getLayeredPane();
        layer.setLayout(null);
    }

}
