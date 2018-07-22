package nth.reflect.fw.ui.swing.mainwindow;

import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

import nth.reflect.fw.ui.swing.component.tabpanel.SwingViewContainer2;

public class KeyboardDispatcher implements KeyEventDispatcher {

	private final SwingViewContainer2 viewContainer;

	public KeyboardDispatcher(SwingViewContainer2 viewContainer) {
		this.viewContainer = viewContainer;
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
					viewContainer.closeOtherViews();
					return true;
				} else {
					viewContainer.closeCurrentView();
					return true;
				}
			} else if (e.getKeyCode() == KeyEvent.VK_F6 && e.isControlDown()) {
				if (e.isShiftDown()) {
					viewContainer.selectPreviousView();
					return true;
				} else {
					viewContainer.selectNextView();
					return true;
				}
			}
		}
		return false;
	}

}
