package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;

import nth.reflect.ui.vaadin.button.Button;
import nth.reflect.ui.vaadin.button.ButtonColor;
import nth.reflect.ui.vaadin.button.ButtonType;

@SuppressWarnings("serial")
public class MainMenuButton extends Button {

	public MainMenuButton() {
		super(ButtonType.ICON, ButtonColor.PRIMARY);
		Icon icon = new Icon(VaadinIcon.MENU);
		
		//TODO do the following inside Button
		icon.setSize(String.valueOf(20));//TODO does not work
		// TODO icon color (also when focused or active)
		// TODO icon padding (2px)
		// TODO icon button bigger border corners
		
		add(icon);
		setId("mainMenuButton");
		setFocusable(false);
		getElement().setAttribute("onclick", "this.blur();toggleMainMenu()");
	}
}
