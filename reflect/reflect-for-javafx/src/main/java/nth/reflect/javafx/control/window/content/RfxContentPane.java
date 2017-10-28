package nth.reflect.javafx.control.window.content;

import javafx.scene.layout.BorderPane;
import nth.introspect.ui.style.MaterialColors;
import nth.reflect.javafx.control.style.RfxColorFactory;

public class RfxContentPane extends BorderPane {

	public RfxContentPane() {
		super();
		setBackground(RfxColorFactory
				.createBackGround(MaterialColors.getContentColorSet().getBackground()));
	}
}
