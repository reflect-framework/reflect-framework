package nth.introsepect.ui.swing.view.menu;

import java.awt.BorderLayout;
import java.awt.Component;
import java.net.URI;
import java.util.List;

import javax.swing.JComboBox;

import nth.introsepect.ui.swing.accordion.ItemAccordion;
import nth.introsepect.ui.swing.view.SwingView;
import nth.introspect.Introspect;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.ui.images.IntrospectImage;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;

public class MenuView extends SwingView {

	private static final long serialVersionUID = 2927307142836484506L;

	private JComboBox searchBox;

	private ItemAccordion itemAccordion;

	public MenuView() {
		List<MethodOwnerItem> menuItems = ItemFactory.createMenuViewItems();

		setLayout(new BorderLayout());

		itemAccordion=new ItemAccordion(menuItems);
		add(itemAccordion,BorderLayout.CENTER);
		
		searchBox = createSearchBox(itemAccordion, menuItems);
		add(searchBox, BorderLayout.NORTH);
	}



	private JComboBox createSearchBox(ItemAccordion itemAccordion, List<MethodOwnerItem> menuItems) {
		return new MenuSearchBox(itemAccordion, menuItems);
	}

	public Component getSearchField() {
		return searchBox;
	}

	@Override
	public String getViewTitle() {
		LanguageProvider languagePort = Introspect.getLanguageProvider();
		return languagePort.getText("Menu");
	}

	@Override
	public String getViewDescription() {
		LanguageProvider languagePort = Introspect.getLanguageProvider();
		return languagePort.getText("Menu");
	}

	@Override
	public URI getViewIconURI() {
		try {
			return Introspect.getPathProvider().getImagePath(IntrospectImage.MENU_SHOW);
		} catch (Exception e) {
			return null;
		}

	}



	@Override
	public void onViewActivate() {
		// set focus to first list item
		
	}
}
