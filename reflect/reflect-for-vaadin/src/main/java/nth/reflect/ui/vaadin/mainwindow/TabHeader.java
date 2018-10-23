package nth.reflect.ui.vaadin.mainwindow;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.ui.style.ReflectColorName;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.ui.vaadin.css.StyleBuilder;
import nth.reflect.ui.vaadin.tab.Tab;

public class TabHeader extends HorizontalLayout {

	private static final long serialVersionUID = -2676923445967047647L;
	private final Tab tab;
	private final TabHeaderCloseButton closeButton;
	private final Tabs<Tab> tabs;

	public TabHeader(Tabs<Tab> tabs, Tab tab) {
		this.tabs = tabs;
		this.tab = tab;
		TabHeaderTitle title = new TabHeaderTitle(tab);
		closeButton = new TabHeaderCloseButton(this);

		add(title, closeButton);
		// https://vaadin.com/docs/v11/flow/element-api/tutorial-dynamic-styling.html
		getElement().addEventListener("click", e -> onClick());
		// new StyleBuilder().setDisplay(Display.INLINE_BLOCK).setFor(this);
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
			// new StyleBuilder().setProperty("--my-color",
			// "red").setBackground("--my-color").setFor(this);
			// UI.getCurrent().getPage().executeJavaScript("", parameters)//set
			// color parameters, see
			// https://medium.com/@_bengarrison/accessing-and-modifying-css-variables-with-javascript-2ccb735bbff0
			new StyleBuilder().setBorderStyle(BorderStyle.SOLID).setBorderWidth(0, 0, 4, 0).setBorderColor(ReflectColorName.ACCENT.BACKGROUND()).setBackground(ReflectColorName.PRIMARY.BACKGROUND_20()).setFor(this);
		} else {
			new StyleBuilder().setBorderStyle(BorderStyle.SOLID).setBorderWidth(0, 0, 4, 0)
					.setBorderColor(ReflectColorName.PRIMARY.BACKGROUND()).setFor(this);
		}
	}

}
