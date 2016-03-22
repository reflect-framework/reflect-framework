package nth.introspect.swing.component.tabpanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JToolBar;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.swing.component.toolbar.MaterialAppBar;
import nth.introspect.ui.swing.view.SwingView;

public class SwingViewContainer2 extends JPanel implements ViewContainer<SwingView> {

	private static final long serialVersionUID = 1L;
	private List<SwingView> views;
	private SwingView currentView;
	private MaterialTabBar tabBar;
	private ContentPanel contentPanel;

	public SwingViewContainer2(UserInterfaceContainer userInterfaceContainer) {
		super(new BorderLayout());
		views = new ArrayList<>();
		tabBar = new MaterialTabBar(userInterfaceContainer, this);
		contentPanel = new ContentPanel(userInterfaceContainer);
		add(tabBar, BorderLayout.NORTH);
		add(contentPanel, BorderLayout.CENTER);
		update();
	}

	public void closeCurrentView() {
		removeView(currentView);
	}

	public void closeOtherViews() {
		Iterator<SwingView> iterator = views.iterator();
		while (iterator.hasNext()) {
			SwingView view = iterator.next();
			if (view != currentView) {
				removeView(view);
			}
		}
	}

	public void selectPreviousView() {
		int index = views.indexOf(currentView) - 1;
		if (index < 0) {
			index = views.size() - 1;
		}
		setSelectedView(views.get(index));
	}

	public void selectNextView() {
		int index = views.indexOf(currentView) + 1;
		if (index >= views.size()) {
			index = 0;
		}
		setSelectedView(views.get(index));
	}

	private void update() {
		tabBar.update(views, currentView);
		contentPanel.setView(currentView);
	}

	@Override
	public int getViewCount() {
		return views.size();
	}

	@Override
	public SwingView getView(int index) {
		return views.get(index);
	}

	@Override
	public void setSelectedView(SwingView newView) {
		if (!views.contains(newView)) {
			views.add(newView);
		}
		currentView = newView;
		update();
	}

	@Override
	public SwingView getSelectedView() {
		return currentView;
	}

	@Override
	public void addView(SwingView newView) {
		views.add(newView);
		setSelectedView(newView);
	}

	@Override
	public void removeView(SwingView view) {
		views.remove(view);
		if (currentView == view) {
			if (views.size() == 0) {
				currentView = null;
			} else {
				currentView = views.get(views.size() - 1);
			}
		}
		update();
	}

}
