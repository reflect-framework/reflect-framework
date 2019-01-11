package nth.reflect.fw.gui.item.tab;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class SelectTabItem<TAB extends Tab> extends Item {

	private final TAB tab;

	public SelectTabItem(LanguageProvider languageProvider, final Tabs<TAB> tabs ,  final TAB tab) {
		super(languageProvider);
		this.tab = tab;
		setAction(new Action() {
			@Override
			public void run() {
				tabs.setSelected(tab);
			}
		});
	}
	public String getText() {
		return tab.getDisplayName();
	};

	@Override
	public String getDescription() {
		return tab.getDescription();
	}

}
