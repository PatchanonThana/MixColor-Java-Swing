package window.windowComponent.Middle;

import javax.swing.*;
import java.awt.*;

public class MixProgressBar extends JProgressBar implements MiddlePanelListener{

    final private Dimension size = new Dimension(300,30);

    public MixProgressBar() {

        setMinimum(0);
        setMaximum(100);
        setString("");
        setBorder(BorderFactory.createLineBorder(Color.BLACK,3));
        setStringPainted(true);
        setVisible(false);

    }

    @Override
    public void middlePanelResized(Container middlePanel) {
        Rectangle middlePanelRec = middlePanel.getBounds();
        setBounds(middlePanelRec.x + middlePanelRec.width/2 - size.width/2,
                middlePanelRec.y - 150,
                size.width,
                size.height
        );
    }




}
