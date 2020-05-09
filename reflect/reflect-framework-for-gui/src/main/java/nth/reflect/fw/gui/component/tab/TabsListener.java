package nth.reflect.fw.gui.component.tab;

public interface TabsListener<TAB> {

	void onRemoveTab(TAB removedTab);

	void onAddTab(TAB newTab);

	void onSelectTab(TAB selectedTab);

}
