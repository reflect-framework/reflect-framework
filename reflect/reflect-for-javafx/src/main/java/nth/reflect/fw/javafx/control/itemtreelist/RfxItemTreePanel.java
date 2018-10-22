package nth.reflect.fw.javafx.control.itemtreelist;

import java.util.Collection;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import nth.reflect.fw.javafx.control.style.RfxStyleSelector;
import nth.reflect.fw.javafx.control.style.RfxStyleSheet;
import nth.reflect.fw.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.HierarchicalItem;
import nth.reflect.fw.ui.item.method.MethodItem;
import nth.reflect.fw.ui.item.method.MethodOwnerItem;

/**
 * TODO implement {@link RfxVerticalFlingScroller}<br>
 * TODO remove blue (focus?) border e.g.
 * https://stackoverflow.com/questions/37524467/remove-all-focus-borders-from-javafx
 * 
 * @author nilsth
 *
 */
public class RfxItemTreePanel extends TreeView {
	private static final String ENTER = "\r";
	private static final String SPACE = " ";

	public RfxItemTreePanel(TreeItem<Item> rootItem) {
		super();
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxItemTreePanel.class));
		setRoot(rootItem);
		setEditable(false);
		setShowRoot(false);
		setCellFactory(createCellFactory());
		setOnKeyTyped(createKeyHandler());
		setOnMouseClicked(createMouseHandler());
	}

	public RfxItemTreePanel(Collection<Item> items, LanguageProvider languageProvider) {
		this(createRootItem(items, languageProvider));
	}

	private static TreeItem<Item> createRootItem(Collection<Item> items, LanguageProvider languageProvider) {
		TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
		rootNode.setExpanded(true);
		for (Item item : items) {
			addItem(rootNode, item);
		}
		return rootNode;
	}
	
	private static void addItem(TreeItem<Item> parent, Item itemToAdd) {
		TreeItem<Item> treeItem = new TreeItem<>(itemToAdd);
		parent.getChildren().add(treeItem);
		if (itemToAdd instanceof HierarchicalItem) {
			HierarchicalItem hierarchicalItem = (HierarchicalItem) itemToAdd;
			for (Item child:hierarchicalItem.getChildren()) {
				addItem(treeItem,child);
			}
		}
	}

	private Callback<TreeView<Item>, TreeCell<Item>> createCellFactory() {
		return new Callback<TreeView<Item>, TreeCell<Item>>() {
			@Override
			public TreeCell<Item> call(TreeView<Item> item) {
				return new RfxItemTreeCell();
			}
		};
	}

	private EventHandler<? super MouseEvent> createMouseHandler() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (MouseButton.PRIMARY.equals(event.getButton()) && event.getClickCount() == 1) {
					RfxItemTreePanel itemTreePanel = (RfxItemTreePanel) event.getSource();
					@SuppressWarnings("unchecked")
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreePanel.getFocusModel()
							.getFocusedItem();
					onItemAction(treeItem);
				}
			}
		};
	}

	private EventHandler<? super KeyEvent> createKeyHandler() {
		return new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				String character = event.getCharacter();
				if (SPACE.equals(character) || ENTER.equals(character)) {
					RfxItemTreePanel itemTreePanel = (RfxItemTreePanel) event.getSource();
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreePanel.getFocusModel()
							.getFocusedItem();
					onItemAction(treeItem);
				}
			}
		};
	}

	public void onItemAction(TreeItem<Item> treeItem) {
		Item item = treeItem.getValue();
		if (item instanceof MethodOwnerItem) {
			toggleIsExpanded(treeItem);
		} else if (item instanceof MethodItem) {
			executeAction(treeItem.getValue().getAction());
		} else if (item instanceof Item) {
			executeAction(treeItem.getValue().getAction());
		}	
		getSelectionModel().select(treeItem);
	}

	private void executeAction(Action action) {
		if (action != null) {
			action.run();
		}
	}

	protected void toggleIsExpanded(TreeItem<Item> treeItem) {
		boolean expanded = treeItem.isExpanded();
		treeItem.setExpanded(!expanded);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxItemTreePanel.class)).getProperties()
				.setPadding(0).setBackgroundInsets(0).setBackground("transparent");
	}

}
