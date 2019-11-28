package nth.reflect.ui.vaadin.mainwindow.menu;

import java.util.List;
import java.util.Optional;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.charts.model.style.FontWeight;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.gui.item.method.MethodOwnerItem;
import nth.reflect.ui.vaadin.css.StyleBuilder;

public class ServiceObjectButton extends Tab {

	private static final long serialVersionUID = 6150025484113398700L;
	private Icon ANGLE_DOWN_ICON = VaadinIcon.ANGLE_DOWN.create();
	private Icon ANGLE_RIGHT_ICON = VaadinIcon.ANGLE_RIGHT.create();
	private boolean expaneded = true;
	private final List<Tab> actionMethodButtons;

	public ServiceObjectButton(MethodOwnerItem serviceObjectItem, List<Tab> actionMethodButtons) {
		this.actionMethodButtons = actionMethodButtons;
		new StyleBuilder().setPadding(0, 0, 0, 0).setFontWeight(FontWeight.BOLD).setFor(this);
		setVisible(serviceObjectItem.isVisible());// TODO dynamic??
		setLabel(serviceObjectItem.getText());// TODO dynamic??

		Div icon = new Div(ANGLE_DOWN_ICON);
		addComponentAsFirst(icon);

		addListener(ClickEvent.class, l -> {
			onClick();
		});
	}

	private void onClick() {
		expaneded = !expaneded;
		updateIcon();
		for (Tab actionMethodButton : actionMethodButtons) {
			actionMethodButton.setVisible(expaneded);
		}
	}

	private void updateIcon() {
		Optional<Component> firstChild = getChildren().findFirst();
		if (firstChild.isPresent()) {
			Div icon = (Div) firstChild.get();
			icon.removeAll();
			icon.add(expaneded ? ANGLE_DOWN_ICON : ANGLE_RIGHT_ICON);
		}

	}

}
