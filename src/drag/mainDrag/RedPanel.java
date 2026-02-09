package drag.mainDrag;

import window.windowComponent.Middle.MiddlePanel;

import java.awt.*;

public class RedPanel extends DragPanelBase {

    public RedPanel(MiddlePanel target) {
        super(Color.RED, new Point(100,300), target);
    }


}
