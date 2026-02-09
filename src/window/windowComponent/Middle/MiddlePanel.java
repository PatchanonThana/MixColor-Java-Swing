package window.windowComponent.Middle;

import listener.ComponentOfLayer;

import javax.swing.*;
import java.awt.*;

public class MiddlePanel extends JPanel implements ComponentOfLayer {

    Dimension thisSize = new Dimension(308,300);

    public MiddlePanel() {

        setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.BLACK));
        setBackground(new Color(224,224,224));
        setLayout(new GridLayout(1,3));


    }

    @Override
    public void layerResized(Dimension size) {
        setBounds(
                size.width/2 - thisSize.width/2 ,
                size.height/2 - thisSize.height/2,
                thisSize.width,
                thisSize.height
        );

    }
}


