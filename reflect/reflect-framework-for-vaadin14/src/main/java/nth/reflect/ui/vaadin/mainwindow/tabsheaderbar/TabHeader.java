package nth.reflect.ui.vaadin.mainwindow.tabsheaderbar;

import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;

import nth.reflect.fw.gui.component.tab.Tabs;

public class TabHeader extends Tab {

	private static final long serialVersionUID = -224589135647253332L;
	private final nth.reflect.ui.vaadin.tab.Tab tab;

	public TabHeader(Tabs<nth.reflect.ui.vaadin.tab.Tab> tabs, nth.reflect.ui.vaadin.tab.Tab tab) {
		this.tab = tab;
		setLabel(tab.getDisplayName());

		Icon closeIcon = createCloseIcon(tabs, tab);
		addComponentAtIndex(1, closeIcon);
	}

	private Icon createCloseIcon(Tabs<nth.reflect.ui.vaadin.tab.Tab> tabs, nth.reflect.ui.vaadin.tab.Tab tab) {
		Icon closeIcon = VaadinIcon.CLOSE_SMALL.create();
		closeIcon.addClickListener(l -> {
			tabs.remove(tab);
		});
		return closeIcon;
	}

	public nth.reflect.ui.vaadin.tab.Tab getTab() {
		return tab;
	}

}
