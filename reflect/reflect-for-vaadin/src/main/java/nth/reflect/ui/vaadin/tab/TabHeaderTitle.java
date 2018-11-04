package nth.reflect.ui.vaadin.tab;

import com.vaadin.flow.component.html.Span;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.Overflow;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.css.WhiteSpace;

@SuppressWarnings("serial")
public class TabHeaderTitle extends Span {

	public TabHeaderTitle(Tab tab) {
		super(tab.getDisplayName());
		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()) .setWhiteSpace(WhiteSpace.NOWRAP).setOverflow(Overflow.HIDDEN).setUserSelectNone()
				.setFor(this);
	}

}
