package nth.reflect.javafx.control.itemtreelist;

import javafx.event.EventHandler;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.item.Item.Action;
import nth.introspect.ui.item.method.MethodItem;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;

/**
 * Test to replace {@link RfxMainMenuList}
 * 
 * TODO implement {@link RfxVerticalFlingScroller}<br>
 * TODO remove blue (focus?) border e.g.
 * https://stackoverflow.com/questions/37524467/remove-all-focus-borders-from-javafx
 * 
 * @author nilsth
 *
 */
public class RfxItemTreeView extends TreeView {
	private static final String ENTER = "\r";
	private static final String SPACE = " ";

	public RfxItemTreeView(TreeItem<Item> rootItem) {
		super();
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxItemTreeView.class));
		setRoot(rootItem);
		setEditable(false);
		setShowRoot(false);
		setCellFactory(createCellFactory());
		setOnKeyTyped(createKeyHandler());
		setOnMouseClicked(createMouseHandler());
	}

	private Callback<TreeView<Item>, TreeCell<Item>> createCellFactory() {
		return new Callback<TreeView<Item>, TreeCell<Item>>() {
			@Override
			public TreeCell<Item> call(TreeView<Item> p) {
				return new RfxItemTreeCell();
			}
		};
	}

	private EventHandler<? super MouseEvent> createMouseHandler() {
		return new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (MouseButton.PRIMARY.equals(event.getButton()) && event.getClickCount() == 1) {
					RfxItemTreeView itemTreeView = (RfxItemTreeView) event.getSource();
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreeView.getFocusModel()
							.getFocusedItem();
					Item item = treeItem.getValue();
					if (item instanceof MethodItem) {
						onAction(treeItem.getValue());
					}

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
					RfxItemTreeView itemTreeView = (RfxItemTreeView) event.getSource();
					TreeItem<Item> treeItem = (TreeItem<Item>) itemTreeView.getFocusModel()
							.getFocusedItem();
					Item item = treeItem.getValue();
					if (item instanceof MethodOwnerItem) {
						toggleIsExpanded(treeItem);
						getSelectionModel().select(treeItem);
					} else if (item instanceof MethodItem) {
						onAction(treeItem.getValue());
						getSelectionModel().select(treeItem);
					}
				}
			}
		};
	}

	protected void onAction(Item item) {

		Action action = item.getAction();
		if (action != null) {
			action.run();
		}
	}

	protected void toggleIsExpanded(TreeItem<Item> treeItem) {
		boolean expanded = treeItem.isExpanded();
		treeItem.setExpanded(!expanded);
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxItemTreeView.class)).getProperties()
				.setPadding(0).setBackgroundInsets(0).setBackground("transparent");
	}

}
