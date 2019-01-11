package nth.reflect.fw.gui.item.tab;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.item.HierarchicalItem;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
/**
 * TODO remove this class???
 * @author nilsth
 *
 */
public class TabsItem<TAB extends Tab> extends HierarchicalItem {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TabsItem(LanguageProvider languageProvider, Tabs<? extends Tab> tabs, Tab tab) {
		super(languageProvider);
		setText("Tabs");
		setDescription("Tabs menu");

		List<Item> children = new ArrayList<Item>();
		children.add(new CloseThisTabItem(languageProvider, tabs, tab));
		children.add(new CloseOtherTabsItem(languageProvider,tabs,tab));
		children.add(new CloseAllTabsItem(languageProvider,tabs));
		if (tabs.size() > 1) {
			for (int i = 0; i < tabs.size(); i++) {
				tab = tabs.get(i);
				children.add(new SelectTabItem(languageProvider,tabs,tab));
			}
		}

		getChildren().addAll(children);

	}
}
