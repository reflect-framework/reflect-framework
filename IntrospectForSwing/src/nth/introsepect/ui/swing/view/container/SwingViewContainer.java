package nth.introsepect.ui.swing.view.container;

import java.awt.Component;
import java.net.URI;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nth.introsepect.ui.swing.icon.IconFactory;
import nth.introsepect.ui.swing.style.SwingStyleConstant;
import nth.introsepect.ui.swing.view.SwingView;
import nth.introspect.provider.userinterface.view.ViewContainer;

@SuppressWarnings("serial")
public class SwingViewContainer extends JTabbedPane implements ViewContainer<SwingView> {
	public SwingViewContainer() {
		setTabLayoutPolicy(WRAP_TAB_LAYOUT);
		addChangeListener(createTabChangeListener());
	}

	private ChangeListener createTabChangeListener() {
		return new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int index = getSelectedIndex();
				if (index > -1) {
					SwingView view = getView(index);
					//update tab contents
					view.onViewActivate();
					//update tab title, description and icon
					setTitleAt(index, view.getViewTitle());
					setToolTipTextAt(index, view.getViewDescription());
					setIconAt(index, IconFactory.create(view.getViewIconURI()));
				}
			}
		};
	}

	@Override
	public void insertTab(String title, Icon icon, Component component, String description, int index) {
		super.insertTab(title, icon, component, description, index);
		setTabComponentAt(index, new TabHeader(this, component, title, description, icon));
		setSelectedIndex(index);
	}

	// TODO add popupmenu with TabContainerItemFactory

	@Override
	public SwingView getView(int index) {
		return (SwingView) getComponentAt(index);

	}

	@Override
	public void selectView(SwingView view) {
		view.onViewActivate();
		setSelectedComponent((Component) view);
	}

	@Override
	public void addView(SwingView view) {
		String title = view.getViewTitle();
		String description = view.getViewDescription();
		URI iconURI = view.getViewIconURI();
		ImageIcon icon = IconFactory.create(iconURI, SwingStyleConstant.ICON_SIZE);
		view.onViewActivate();
		insertTab(title, icon, (Component) view, description, getViewCount());
	}

	@Override
	public void removeView(SwingView view) {
		remove((Component) view);
	}

	@Override
	public int getViewCount() {
		return getTabCount();
	}
}
