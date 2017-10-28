package nth.reflect.javafx.control.window.mainmenu;

import java.util.List;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.TreeItem;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxMainMenuItemTreeView extends RfxItemTreeView {

	private final BooleanBinding windowExtraWideBinding;
	private final BooleanProperty mainMenuVisibleProperty;

	public RfxMainMenuItemTreeView(UserInterfaceContainer userInterfaceContainer) {
		super(createRootItem(userInterfaceContainer));
		RfxWindow rfxWindow = userInterfaceContainer.get(RfxWindow.class);
		this.windowExtraWideBinding = rfxWindow.getExtraWideBinding();
		this.mainMenuVisibleProperty=rfxWindow.getMainMenuVisibleProperty();
	}
	
	
	private static TreeItem<Item> createRootItem(UserInterfaceContainer userInterfaceContainer) {
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);

		List<MethodOwnerItem> serviceObjectItems = ItemFactory
				.createMainMenuItems(userInterfaceContainer);

		for (Item serviceObjectItem : serviceObjectItems) {
			TreeItem<Item> serviceObjectNode = new TreeItem<>(serviceObjectItem);
			rootNode.getChildren().add(serviceObjectNode);
			for (Item methodItem : ((MethodOwnerItem) serviceObjectItem).getChildren()) {
				TreeItem<Item> serviceObjectMethodNode = new TreeItem<>(methodItem);
				serviceObjectNode.getChildren().add(serviceObjectMethodNode);
			}
		}

		if (canExpandAllServiceItems(rootNode)) {
			expandAllServiceItems(rootNode);
		}

		return rootNode;
	}

	private static boolean canExpandAllServiceItems(TreeItem<Item> rootNode) {
		int nrOfVisibleItems = 0;
		for (TreeItem<Item> serviceObjectNode : rootNode.getChildren()) {
			if (serviceObjectNode.getValue().isVisible()) {
				nrOfVisibleItems++;
				for (TreeItem<Item> serviceObjectMethodNode : serviceObjectNode.getChildren()) {
					if (serviceObjectMethodNode.getValue().isVisible()) {
						nrOfVisibleItems++;
					}
				}
			}
		}
		return nrOfVisibleItems < 15;
	}

	private static void expandAllServiceItems(TreeItem<Item> rootNode) {
		for (TreeItem<Item> serviceObjectNode : rootNode.getChildren()) {
			if (serviceObjectNode.getValue().isVisible()) {
				serviceObjectNode.setExpanded(true);
			}
		}
	}

	
	@Override
	protected void onAction(Item item) {
		super.onAction(item);
		if (isNarrowWindow()) {
			hideMainMenu();				
		}
	}

	private void hideMainMenu() {
		mainMenuVisibleProperty.set(false);
	}

	private boolean isNarrowWindow() {
		return !windowExtraWideBinding.getValue();
	}

}
