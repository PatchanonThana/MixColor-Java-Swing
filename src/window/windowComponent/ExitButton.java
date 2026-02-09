package window.windowComponent;

import listener.ComponentOfLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExitButton extends JLabel implements ComponentOfLayer {

    JFrame MainWindow;

    public ExitButton(JFrame MainWindow) {

        this.MainWindow = MainWindow;
        setText("Exit");
        setOpaque(true);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(new Color(128,128,128),3));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                MainWindow.dispose();
            }
        });

    }

    @Override
    public void layerResized(Dimension size) {
        setBounds(50,
                20,
                70,
                30);
    }

}
