package nth.reflect.ui.vaadin.tab;

import com.vaadin.flow.component.dependency.HtmlImport;

import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.ui.vaadin.button.Button;
import nth.reflect.ui.vaadin.button.ButtonColor;
import nth.reflect.ui.vaadin.button.ButtonType;

@SuppressWarnings("serial")
@HtmlImport("/frontend/styles/reflect-tab-header-button.html")
public class TabHeaderButton extends Button {

	private static final String CLASS_NAME = "reflect-tab-header-button";
	private static final String CLASS_NAME_SELECTED = CLASS_NAME+"-selected";
	private static final String CLASS_NAME_UNSELECTED = CLASS_NAME+"-unselected";
	
	
	private final Tab tab;
	private final TabHeaderCloseButton closeButton;
	private final Tabs<Tab> tabs;

	public TabHeaderButton(Tabs<Tab> tabs, Tab tab) {
		super(ButtonType.TEXT, ButtonColor.PRIMARY);
		this.tabs = tabs;
		this.tab = tab;
		TabHeaderTitle title = new TabHeaderTitle(tab);
		closeButton = new TabHeaderCloseButton(this);

		add(title, closeButton);
		
		getClassNames().add(CLASS_NAME);
		
		getElement().addEventListener("click", e -> onClick());
	}

	public void onClick() {
		tabs.setSelected(tab);
	}

	public void onCloseButtonClick() {
		tabs.remove(tab);
	}

	public Object getTab() {
		return tab;
	}

	public void setSelected(boolean isSelected) {
		closeButton.setVisible(isSelected);
		if (isSelected) {
			getClassNames().remove(CLASS_NAME_UNSELECTED);
			getClassNames().add(CLASS_NAME_SELECTED);
		} else {
			getClassNames().remove(CLASS_NAME_SELECTED);
			getClassNames().add(CLASS_NAME_UNSELECTED);
		}
	}

}
