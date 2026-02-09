package drag.mainDrag;

import window.windowComponent.Middle.MiddlePanel;

import java.awt.*;

public class BluePanel extends DragPanelBase {

    public BluePanel(MiddlePanel target) {
        super(Color.BLUE, new Point(100, 450), target);
    }


}
