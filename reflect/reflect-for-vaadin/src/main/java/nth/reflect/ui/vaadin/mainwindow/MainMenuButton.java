package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.ui.vaadin.button.Button;

@SuppressWarnings("serial")
public class MainMenuButton extends Button {

	public MainMenuButton() {
		super(ReflectColorName.PRIMARY);
		add(new Icon(VaadinIcon.MENU));
		setId("mainMenuButton");
		getElement().setAttribute("onclick", "toggleMainMenu()");
		//TODO include CSS for smaller icon size + changed padding
	}
}
