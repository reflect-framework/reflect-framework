package nth.introspect.ui.swing.view.container;

import java.awt.Component;
import java.net.URI;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.swing.icon.IconFactory;
import nth.introspect.ui.swing.style.SwingStyleConstant;
import nth.introspect.ui.swing.view.SwingView;

@SuppressWarnings("serial")
public class SwingViewContainer extends JTabbedPane implements ViewContainer<View> {
	private final UserInterfaceContainer userInterfaceContainer;
	private final PathProvider pathProvider;

	public SwingViewContainer(UserInterfaceContainer userInterfaceContainer, PathProvider pathProvider) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.pathProvider = pathProvider;
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
		LanguageProvider languageProvider=userInterfaceContainer.getLanguageProvider();
		setTabComponentAt(index, new TabHeader(this,  pathProvider, languageProvider, component, title, description, icon));
		setSelectedIndex(index);
	}

	// TODO add popupmenu with TabContainerItemFactory

	@Override
	public SwingView getView(int index) {
		return (SwingView) getComponentAt(index);

	}

	@Override
	public void setSelectedView(View view) {
		view.onViewActivate();
		setSelectedComponent((Component) view);
	}

	@Override
	public void addView(View view) {
		String title = view.getViewTitle();
		String description = view.getViewDescription();
		URI iconURI = view.getViewIconURI();
		ImageIcon icon = IconFactory.create(iconURI, SwingStyleConstant.ICON_SIZE);
		view.onViewActivate();
		insertTab(title, icon, (Component) view, description, getViewCount());
	}

	@Override
	public void removeView(View view) {
		remove((Component) view);
	}

	@Override
	public int getViewCount() {
		return getTabCount();
	}

	@Override
	public View getSelectedView() {
		int index = getSelectedIndex();
		int tabCount=getTabCount();
		if (index>=0 && index>=tabCount) {
			setSelectedIndex(tabCount-1);
		}
		return (View) getSelectedComponent();
	}
}
