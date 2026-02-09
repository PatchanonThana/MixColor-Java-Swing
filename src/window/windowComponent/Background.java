package window.windowComponent;

import listener.ComponentOfLayer;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel implements ComponentOfLayer {

    private JFrame frame;

    public Background() {
        setLocation(0,0);
        setBackground(new Color(255,229,204));
        setLayout(null);

    }

    @Override
    public void layerResized(Dimension size) {
        setSize(size);
    }

}
