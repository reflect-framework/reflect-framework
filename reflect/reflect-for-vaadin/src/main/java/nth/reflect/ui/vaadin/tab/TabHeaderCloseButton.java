package nth.reflect.ui.vaadin.tab;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class TabHeaderCloseButton extends Icon {

	public TabHeaderCloseButton(TabHeaderButton tabHeaderButton) {
		super(VaadinIcon.CLOSE_SMALL);
//		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()) .setPadding(0).setMargin(0).setFor(this);
		getElement().addEventListener("click", e-> tabHeaderButton.onCloseButtonClick());
	}

	
}
