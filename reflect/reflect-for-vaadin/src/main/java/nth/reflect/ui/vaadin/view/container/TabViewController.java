package nth.reflect.ui.vaadin.view.container;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.ui.vaadin.mainwindow.MainWindow;
import nth.reflect.ui.vaadin.view.TabView;

public class TabViewController implements ViewController<TabView> {

	private final List<TabView> views;
	private View selectedView;
	private final MainWindow mainWindow;

	public TabViewController(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		views = new ArrayList<TabView>();
	}

	@Override
	public int getViewCount() {
		return views.size();
	}

	@Override
	public TabView getView(int index) {
		return views.get(index);
	}

	@Override
	public void setSelectedView(TabView view) {
		if (!views.contains(view)) {
			views.add(view);
			mainWindow.onAddTab(view);
		}
		selectedView = view;
		mainWindow.onSelectTabView(view);
	}

	@Override
	public View getSelectedView() {
		return selectedView;
	}

	@Override
	public void addView(TabView newView) {
		setSelectedView(newView);
	}

	@Override
	public void removeView(TabView view) {
		views.remove(view);
		if (selectedView == view) {
			if (views.size() == 0) {
				selectedView = null;
			} else {
				selectedView = views.get(views.size() - 1);
			}
		}
		mainWindow.onRemoveTab(view);
	}


}
