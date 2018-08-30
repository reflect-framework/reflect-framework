package nth.reflect.ui.vaadin10.view.container;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.ui.vaadin10.ReflectApplicationForVaadin;
import nth.reflect.ui.vaadin10.view.VaadinView;

public class VaadinViewContainer implements ViewContainer<VaadinView> {

	private final List<VaadinView> views;
	private View selectedView;

	public VaadinViewContainer(ReflectApplicationForVaadin reflectApplicationForVaadin) {
		views = new ArrayList<VaadinView>();
	}

	@Override
	public int getViewCount() {
		return views.size();
	}

	@Override
	public VaadinView getView(int index) {
		return views.get(index);
	}

	@Override
	public void setSelectedView(VaadinView newView) {
		if (!views.contains(newView)) {
			views.add(newView);
		}
		selectedView = newView;
		update();
	}

	@Override
	public View getSelectedView() {
		return selectedView;
	}

	@Override
	public void addView(VaadinView newView) {
		views.add(newView);
		setSelectedView(newView);
	}

	@Override
	public void removeView(VaadinView view) {
		views.remove(view);
		if (selectedView == view) {
			if (views.size() == 0) {
				selectedView = null;
			} else {
				selectedView = views.get(views.size() - 1);
			}
		}
		update();
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}

}
