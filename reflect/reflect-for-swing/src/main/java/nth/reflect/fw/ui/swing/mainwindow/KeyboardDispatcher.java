package nth.reflect.fw.ui.swing.mainwindow;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nth.reflect.fw.ui.component.tab.Tabs;
import nth.reflect.fw.ui.swing.tab.Tab;

public class KeyboardDispatcher implements KeyEventDispatcher {

	private final Tabs<Tab> tabs;

	public KeyboardDispatcher(Tabs<Tab> tabs) {
		this.tabs = tabs;
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if (e.getKeyCode() == KeyEvent.VK_F1) {
				//TODO about
			}
			if (e.getKeyCode() == KeyEvent.VK_F2) {
				//TODO toggle visibility of menu (and when visible set focus to it)
			}
			if (e.getKeyCode() == KeyEvent.VK_F3) {
				//TODO search
			}
			if (e.getKeyCode() == KeyEvent.VK_F4 && e.isControlDown()) {
				if (e.isShiftDown()) {
					closeOtherTabs();
					return true;
				} else {
					closeCurrentTab();
					return true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_F6 && e.isControlDown()) {
				if (e.isShiftDown()) {
					tabs.selectPrevious();
					return true;
				} else {
					tabs.selectNext();
					return true;
				}
			}
		}
		return false;
	}

	private void closeCurrentTab() {
		tabs.remove(tabs.getSelected());
	}

	public void closeOtherTabs() {
		Tab selectedTab = tabs.getSelected();
		Iterator<Tab> iterator = tabs.iterator();
		while (iterator.hasNext()) {
			Tab tab = iterator.next();
			if (tab != selectedTab) {
				tabs.remove(tab);
			}
		}
	}

	

}
