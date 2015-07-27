package nth.introspect.ui.swing.view.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URI;
import java.util.List;

import javax.swing.JComboBox;

import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.introspect.ui.swing.accordion.ItemAccordion;
import nth.introspect.ui.swing.view.SwingView;

public class MenuView extends SwingView {

	
	private static final long serialVersionUID = 2927307142836484506L;
	private JComboBox searchBox;
	private ItemAccordion itemAccordion;
	private final LanguageProvider languageProvider;
	private final URI menuIconUri ;

	public MenuView(UserInterfaceContainer userInterfaceContainer, PathProvider pathProvider) {
		List<MethodOwnerItem> menuItems = ItemFactory.createMenuViewItems(userInterfaceContainer);

		setLayout(new BorderLayout());

		itemAccordion=new ItemAccordion(menuItems);
		add(itemAccordion,BorderLayout.CENTER);
		menuIconUri = pathProvider.getImagePath(IntrospectImage.MENU_OPENED);
		languageProvider = userInterfaceContainer.getLanguageProvider();
		searchBox = createSearchBox(itemAccordion, menuItems, languageProvider);
		add(searchBox, BorderLayout.NORTH);
	}



	private JComboBox createSearchBox(ItemAccordion itemAccordion, List<MethodOwnerItem> menuItems, LanguageProvider languageProvider) {
		return new MenuSearchBox(itemAccordion, menuItems, languageProvider);
	}

	public Component getSearchField() {
		return searchBox;
	}

	@Override
	public String getViewTitle() {
		return languageProvider.getText("Menu");
	}

	@Override
	public String getViewDescription() {
		return languageProvider.getText("Menu");
	}

	@Override
	public URI getViewIconURI() {
		try {
			return menuIconUri;
		} catch (Exception e) {
			return null;
		}

	}



	@Override
	public void onViewActivate() {
		// set focus to first list item
		
	}
}
