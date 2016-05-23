package nth.introspect.ui.swing.view.container;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.ui.swing.icon.IconFactory;
import nth.introspect.ui.swing.style.SwingStyleConstant;
import nth.introspect.ui.swing.view.SwingView;

@SuppressWarnings("serial")
public class SwingViewContainer extends JTabbedPane implements ViewContainer<View> {
	private final UserInterfaceContainer userInterfaceContainer;

	public SwingViewContainer(UserInterfaceContainer userInterfaceContainer) {
		this.userInterfaceContainer = userInterfaceContainer;
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
					//update tab MaterialAppBarTitle, description and MaterialAppBarIcon
					setTitleAt(index, view.getViewTitle());
					setToolTipTextAt(index, view.getViewDescription());
					setIconAt(index, IconFactory.create(view.getViewIconURL()));
				}
			}
		};
	}

	@Override
	public void insertTab(String title, Icon icon, Component component, String description, int index) {
		super.insertTab(title, icon, component, description, index);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		setTabComponentAt(index, new TabHeader(this,  languageProvider, component, title, description, icon));
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
		URL iconURL = view.getViewIconURL();
		ImageIcon icon = IconFactory.create(iconURL, SwingStyleConstant.ICON_SIZE);
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
