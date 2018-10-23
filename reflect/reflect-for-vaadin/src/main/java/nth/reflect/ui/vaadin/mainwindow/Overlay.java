package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.html.Div;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.Cursor;
import nth.reflect.ui.vaadin.css.Display;
import nth.reflect.ui.vaadin.css.Position;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class Overlay extends Div {

	private static final long serialVersionUID = 15105336519692586L;

	/**
	 * The {@link Overlay} HTML element covers the other content when the
	 * {@link MainMenu} element is visible in drawer mode. The {@link Overlay}
	 * element's visibility, position and size is set with javascript (see
	 * main-window.js)
	 */

	public Overlay() {
		getElement().setAttribute("onclick", "closeMainMenu()");
		new StyleBuilder().setPosition(Position.FIXED).setDisplay(Display.NONE)
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_20()).setZIndex(MainWindow.Z_INDEX_CONTENT_OVERLAY)
				.setCursor(Cursor.POINTER).setFor(this);
		setId("overlay");
	}
}
