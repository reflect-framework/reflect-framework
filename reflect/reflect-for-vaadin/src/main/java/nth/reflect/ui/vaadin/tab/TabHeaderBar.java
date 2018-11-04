package nth.reflect.ui.vaadin.tab;

import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.tab.TabsListener;

/**
 * The {@link TabHeaderBar} HTML element is a placeholder (a container) for the
 * {@link TabHeaderButton}s. The {@link TabHeaderBar} element and all its children will be
 * repositioned, resized, hidden or displayed with javascript (see tabHeaderBar.js)
 */
public class TabHeaderBar extends HorizontalLayout implements TabsListener<Tab> {

	private static final long serialVersionUID = 6459272335586683593L;
	private final TabSelectionButton tabSelectionButton;
	private final Tabs<Tab> tabs;
	
	public TabHeaderBar(Tabs<Tab> tabs) {
		this.tabs = tabs;
		tabs.addListener(this);
		tabSelectionButton=new TabSelectionButton(tabs);
		add(tabSelectionButton);
	}
	
	@Override
	public void onRemoveTab(Tab removedTab) {
		List<Component> children = getChildren().collect(Collectors.toList());
		for (Component component : children) {
			if (component instanceof TabHeaderButton) {
				TabHeaderButton tabHeaderButton = (TabHeaderButton) component;
				if ( tabHeaderButton.getTab().equals(removedTab)) {
					remove(tabHeaderButton);
				};
			}
		}
	}


	/**
	 * Adds tab header at the end of the {@link TabHeaderBar}, just before the {@link TabSelectionButton}
	 */
	@Override
	public void onAddTab(Tab newTab) {
		TabHeaderButton tabHeaderButton=new TabHeaderButton(tabs, newTab);
		remove(tabSelectionButton);
		add(tabHeaderButton);
		add(tabSelectionButton);
	}


	@Override
	public void onSelectTab(Tab selectedTab) {
		List<Component> children = getChildren().collect(Collectors.toList());
		for (Component component : children) {
			if (component instanceof TabHeaderButton) {
				TabHeaderButton tabHeaderButton = (TabHeaderButton) component;
				boolean isSelected = tabHeaderButton.getTab().equals(selectedTab);
				tabHeaderButton.setSelected(isSelected);
			}
		}
	}




}
