package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class TabHeaderCloseButton extends Icon {

	public TabHeaderCloseButton(TabHeader tabHeader) {
		super(VaadinIcon.CLOSE_SMALL);
		new StyleBuilder().setColor(ReflectColorName.PRIMARY.FOREGROUND()) .setPadding(0).setMargin(0).setFor(this);
		getElement().addEventListener("click", e-> tabHeader.onCloseButtonClick());
	}

	
}
