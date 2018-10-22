package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.html.Span;

import nth.reflect.ui.vaadin.css.Cursor;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.css.WhiteSpace;
import nth.reflect.ui.vaadin.tab.Tab;

@SuppressWarnings("serial")
public class TabHeaderTitle extends Span {

	public TabHeaderTitle(Tab tab) {
		super(tab.getDisplayName());
		new StyleBuilder().setWhiteSpace(WhiteSpace.NOWRAP).setOverflow(Overflow.HIDDEN).setCursor(Cursor.DEFAULT)
				.setFor(this);
	}

}
