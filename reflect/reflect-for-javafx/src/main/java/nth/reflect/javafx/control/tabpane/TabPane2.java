package nth.reflect.javafx.control.tabpane;

import javafx.scene.control.Skin;
import javafx.scene.control.TabPane;
import javafx.scene.input.MouseEvent;
import nth.introspect.ui.style.MaterialStyle;

public class TabPane2 extends TabPane {
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Skin<?> createDefaultSkin() {
		return new TabPaneSkin4(this);
	}

	
	/**
	 * Initialize the style class to 'jfx-tab-pane'.
	 *
	 * This is the selector class from which CSS can be used to style
	 * this control.
	 */
	private static final String DEFAULT_STYLE_CLASS = "jfx-tab-pane";
	
	/**
	 * propagate any mouse events on the tab pane to its parent
	 */
	public void propagateMouseEventsToParent(){
		this.addEventHandler(MouseEvent.ANY, (e)->{
			e.consume();
			this.getParent().fireEvent(e);
		});
	}
}
