package nth.reflect.fw.javafx.control.popup;

import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.Window;

/**
 * A pop up window that can be used for a context window or drop down menu. It
 * tries to position it self above or below a given location (depending where
 * there is the most space) and will try to stay within the main {@link Window}
 * 
 * @author nilsth
 *
 */
public class RfxPopup extends Popup {

	public RfxPopup() {
		setAutoFix(true);
		setAutoHide(true);
		setHideOnEscape(true);

	}

	@Override
	public void show(Node ownerNode, double anchorX, double anchorY) {
		Node content = getContent().get(0);// yuk
		content.setStyle("-fx-background-color: lightgray; ");// yuk
//	TODO	JFXDepthManager.createMaterialNode(content, 4);// yuk
		super.show(ownerNode, anchorX, anchorY);
	}
}
