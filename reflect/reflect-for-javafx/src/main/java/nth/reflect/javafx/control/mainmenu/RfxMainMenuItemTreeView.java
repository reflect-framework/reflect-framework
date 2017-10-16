package nth.reflect.javafx.control.mainmenu;

import java.util.List;
import java.util.Optional;

import javafx.beans.binding.BooleanBinding;
import javafx.scene.control.TreeItem;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.reflect.javafx.RfxUtils;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.tabpane.RfxMenuAndContentPane;

public class RfxMainMenuItemTreeView extends RfxItemTreeView {

	private final BooleanBinding windowExtraWideBinding;

	public RfxMainMenuItemTreeView(UserInterfaceContainer userInterfaceContainer, BooleanBinding windowExtraWideBinding) {
		super(createRootItem(userInterfaceContainer));
		this.windowExtraWideBinding = windowExtraWideBinding;
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
		if (!windowExtraWideBinding.getValue()) {
			Optional<RfxMenuAndContentPane> result = RfxUtils.findParent(this,RfxMenuAndContentPane.class);
			if (result.isPresent()) {
				RfxMenuAndContentPane menuAndContentPane=result.get();
				menuAndContentPane.hideMenu();				
			}
		}
	}

}
