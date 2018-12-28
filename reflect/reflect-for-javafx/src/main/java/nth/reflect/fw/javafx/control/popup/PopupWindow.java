package nth.reflect.fw.javafx.control.popup;

import com.jfoenix.effects.JFXDepthManager;

import javafx.scene.Node;
import javafx.stage.Popup;
import javafx.stage.Window;
import nth.reflect.fw.javafx.control.style.StyleProperties;
import nth.reflect.fw.ui.style.ReflectColorName;

/**
 * A pop up window that can be used for a context window or drop down menu. It
 * tries to position it self above or below a given location (depending where
 * there is the most space) and will try to stay within the main {@link Window}
 * 
 * @author nilsth
 *
 */
public class PopupWindow extends Popup {

	public PopupWindow() {
		setAutoFix(true);
		setAutoHide(true);
		setHideOnEscape(true);
	}

	@Override
	public void show(Node ownerNode, double anchorX, double anchorY) {
		Node content = getContent().get(0);// yuk
		content.setStyle(new StyleProperties().setBackground(ReflectColorName.CONTENT.BACKGROUND()).toString());
		JFXDepthManager.setDepth(content, 3);
		super.show(ownerNode, anchorX, anchorY);
	}
}
