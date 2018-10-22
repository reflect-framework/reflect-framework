package nth.reflect.ui.vaadin.mainwindow;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;
import nth.reflect.ui.vaadin.tab.Tab;

/**
 * The {@link TabHeaderBar2} HTML element is a placeholder (a container) for the
 * {@link TabHeader}s. The {@link TabHeaderBar2} element and all its children will be
 * repositioned, resized, hidden or displayed with javascript (see tabHeaderBar.js)
 */
public class TabHeaderBar2 extends HorizontalLayout implements TabsListener<Tab> {

	private static final long serialVersionUID = 6459272335586683593L;
	private final TabSelectionButton tabSelectionButton;
	private final Tabs<Tab> tabs;
	
	public TabHeaderBar2(Tabs<Tab> tabs) {
		this.tabs = tabs;
		tabs.addListener(this);
		tabSelectionButton=new TabSelectionButton(tabs);
		add(tabSelectionButton);
	}
	
	@Override
	public void onRemoveTab(Tab removedTab) {
		List<Component> children = getChildren().collect(Collectors.toList());
		for (Component component : children) {
			if (component instanceof TabHeader) {
				TabHeader tabHeader = (TabHeader) component;
				if ( tabHeader.getTab().equals(removedTab)) {
					remove(tabHeader);
				};
			}
		}
	}


	/**
	 * Adds tab header at the end of the {@link TabHeaderBar2}, just before the {@link TabSelectionButton}
	 */
	@Override
	public void onAddTab(Tab newTab) {
		TabHeader tabHeader=new TabHeader(tabs, newTab);
		remove(tabSelectionButton);
		add(tabHeader);
		add(tabSelectionButton);
	}


	@Override
	public void onSelectTab(Tab selectedTab) {
		List<Component> children = getChildren().collect(Collectors.toList());
		for (Component component : children) {
			if (component instanceof TabHeader) {
				TabHeader tabHeader = (TabHeader) component;
				boolean isSelected = tabHeader.getTab().equals(selectedTab);
				tabHeader.setSelected(isSelected);
			}
		}
	}




}
