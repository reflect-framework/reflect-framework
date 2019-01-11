package nth.reflect.fw.gui.component.tab;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.component.ReflectGuiComponent;

/**
 * {@link Tabs} is a collection of {@link Tab}s that are displayed in a
 * Graphical User Interface for the {@link ReflectFramework}.
 * <p>
 * It is a read-only {@link Iterable} so that a for loop can be used to iterate
 * trough the {@link Tab}s.
 * </p>
 * <p>
 * It as a selected {@link Tab}. This is the {@link Tab} that is currently
 * visible.
 * </p>
 * <p>
 * It can inform {@link TabsListener}s when tabs are added, removed or a other
 * tab is selected. This can be used for {@link ReflectGuiComponent} such as the
 * TabHeaderBar, TabHeader, TabSelectionButton, TabContentPanel
 * </p>
 * 
 * @author nilsth
 *
 */
// TODO describe the concepts above in ReflectDocumentation interfaces and
// change the concepts above to (@link...}
public class Tabs<TAB extends Tab> implements Iterable<TAB> {

	private final List<TAB> tabs;
	private TAB selectedTab;
	private final List<TabsListener<TAB>> tabsListeners;

	public Tabs() {
		tabs = new ArrayList<>();
		tabsListeners = new ArrayList<>();
	}

	public int size() {
		return tabs.size();
	}

	public TAB get(int index) {
		return tabs.get(index);
	}

	public void setSelected(TAB tab) {
		if (!tabs.contains(tab) && tab!=null) {
			tabs.add(tab);
			fireOnAddTab(tab);
		}
		selectedTab = tab;
		fireOnSelectTab(tab);
	}

	public TAB getSelected() {
		return selectedTab;
	}

	public void add(TAB newTab) {
		setSelected(newTab);
	}

	public void remove(TAB tabToRemove) {
		int tabToRemoveIndex=tabs.indexOf(tabToRemove);
		if (tabs.contains(tabToRemove)) {
			tabs.remove(tabToRemove);
			fireOnRemoveTab(tabToRemove);
		}
		if (tabs.isEmpty()) {
			setSelected(null);
		} else if (selectedTab == tabToRemove) {
			int newSelectedIndex=(tabToRemoveIndex>=tabs.size())?tabs.size()-1:tabToRemoveIndex;
			setSelected(tabs.get(newSelectedIndex));
		}
	}

	public Tab selectPrevious() {
		if (tabs.size() == 0) {
			setSelected(null);
			return null;
		} else if (tabs.size() == 1) {
			setSelected(tabs.get(0));
			return tabs.get(0);
		} else {
			int selectedTabIndex = tabs.indexOf(selectedTab);
			int previousTabIndex = selectedTabIndex - 1;
			if (previousTabIndex < 0) {
				previousTabIndex = tabs.size() - 1;
			}
			TAB previousTab = tabs.get(previousTabIndex);
			setSelected(previousTab);
			return previousTab;
		}
	}

	public TAB selectNext() {
		if (tabs.size() == 0) {
			setSelected(null);
			return null;
		} else if (tabs.size() == 1) {
			setSelected(tabs.get(0));
			return tabs.get(0);
		} else {
			int selectedTabIndex = tabs.indexOf(selectedTab);
			int nextTabIndex = selectedTabIndex + 1;
			if (nextTabIndex >= tabs.size()) {
				nextTabIndex = 0;
			}
			TAB nextTab = tabs.get(nextTabIndex);
			setSelected(nextTab);
			return nextTab;
		}
	}

	/**
	 * The {@link Tabs<TAB>} implements a read only {@link Iterable} so that it
	 * can be used in a for loop
	 */

	@Override
	public Iterator<TAB> iterator() {
		Iterator<TAB> tabsIterator = tabs.iterator();
		return new Iterator<TAB>() {

			@Override
			public boolean hasNext() {
				return tabsIterator.hasNext();
			}

			@Override
			public TAB next() {
				return tabsIterator.next();
			}

		};
	}

	public void addListener(TabsListener<TAB> tabsListener) {
		tabsListeners.add(tabsListener);
	}

	public void removeListener(TabsListener<TAB> tabsListener) {
		tabsListeners.remove(tabsListener);
	}

	private void fireOnAddTab(TAB newTab) {
		for (TabsListener<TAB> tabsListener : tabsListeners) {
			tabsListener.onAddTab(newTab);
		}
	}

	private void fireOnRemoveTab(TAB removedTab) {
		for (TabsListener<TAB> tabsListener : tabsListeners) {
			tabsListener.onRemoveTab(removedTab);
		}
	}

	private void fireOnSelectTab(TAB selectedTab) {
		for (TabsListener<TAB> tabsListener : tabsListeners) {
			tabsListener.onSelectTab(selectedTab);
		}
	}

}
