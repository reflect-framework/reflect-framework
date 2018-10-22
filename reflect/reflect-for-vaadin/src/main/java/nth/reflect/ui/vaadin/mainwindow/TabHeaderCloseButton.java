package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import nth.reflect.ui.vaadin.css.StyleBuilder;

@SuppressWarnings("serial")
public class TabHeaderCloseButton extends Button {

	public TabHeaderCloseButton(TabHeader tabHeader) {
		super(new Icon(VaadinIcon.CLOSE_SMALL));
		new StyleBuilder().setPadding(0).setMargin(0).setFor(this);
		getElement().addEventListener("click", e-> tabHeader.onCloseButtonClick());
	}

	
}
