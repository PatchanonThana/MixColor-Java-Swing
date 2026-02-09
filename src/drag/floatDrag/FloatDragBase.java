package drag.floatDrag;

import listener.ComponentOfLayer;
import window.windowComponent.Middle.MiddlePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public abstract class FloatDragBase extends JPanel {

    final protected Dimension thisSize = new Dimension(100,100);

    protected int maxX;
    protected int maxY;

    public FloatDragBase(Color color) {

        setBackground(color);
        setBorder(BorderFactory.createLineBorder(Color.BLACK,4));
        setSize(100,100);

        addHierarchyListener(e -> {

            if ((e.getChangeFlags() & HierarchyEvent.PARENT_CHANGED) != 0) {
                setLocation(
                        FloatDragBase.this.getParent().getWidth() - 225,
                        375
                );
                maxX = getParent().getWidth() - getWidth();
                maxY = getParent().getHeight() - getHeight();
            }

        });

        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                setThisLocation(e);
            }

        }) ;

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setThisLocation(e);
            }
        });


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

}
