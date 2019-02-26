package nth.reflect.fw.javafx.control.table;

import java.util.Collection;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.text.FontWeight;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.style.MaterialFont;
import nth.reflect.fw.gui.style.ReflectColorName;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreeCell;
import nth.reflect.fw.javafx.control.itemtreelist.ItemTreePanel;
import nth.reflect.fw.javafx.control.popup.PopupWindow;
import nth.reflect.fw.javafx.control.style.StyleSelector;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

public class Table extends TableView<Object> {

	// ROW_HEIGHT: Material design says 48 but we use same height as menu items
	private static final int ROW_HEIGHT = ItemTreeCell.ITEM_HEIGHT;
	private static final int ROW_FONT_SIZE = 14;
	private static final int HEADER_FONT_SIZE = 13;
	private final PopupWindow popupMenu;
	private TableInfo tableInfo;

	public Table() {
		// TODO new VerticalFlingScroller(this);
		addStyleClass();
		popupMenu = new PopupWindow();
		setOnMouseClicked(this::onMouseClicked);
	}

	/**
	 * Constructor for creating a table that shows a {@link ActionMethod} result
	 * 
	 * @param reflectionProvider
	 * @param languageProvider
	 * @param methodOwner
	 * @param actionMethodInfo
	 * @param methodParameterValue
	 * @param tableView
	 */
	public Table(TableInfo tableInfo) {
		this();
		this.tableInfo = tableInfo;
		setItems(tableInfo.getObservableList());
		initColumns(tableInfo.getTableColumns());
	}

	public void updateData() {
		setItems(tableInfo.getObservableList());
	}

	public Table(nth.reflect.fw.gui.component.tab.grid.GridTab gridTab) {
		this(new TableInfoForTableTab(gridTab));
	}

	public Table(FormTab formTab, PropertyValueModel propertyValueModel) {
		this(new TableInfoForFormTabProperty(formTab, propertyValueModel));
	}

	private void initColumns(List<TableColumn<Object, ?>> tableColumns) {
		if (tableColumns.size() == 1) {
			hideHeader();
		}
		getColumns().addAll(tableColumns);
		ColumnAutoSizer.autoFitTable(this);
	}

	// /**
	// * TODO replace with {@link JFXPopup}
	// *
	// * @return
	// */
	// private ContextMenu createTestContextMenu() {
	//
	// final ContextMenu contextMenu = new ContextMenu();
	// contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
	// public void handle(WindowEvent e) {
	// System.out.println("showing");
	// }
	// });
	// contextMenu.setOnShown(new EventHandler<WindowEvent>() {
	// public void handle(WindowEvent e) {
	// System.out.println("shown");
	// }
	// });
	//
	// MenuItem item1 = new MenuItem("About");
	// item1.setOnAction(new EventHandler<ActionEvent>() {
	// public void handle(ActionEvent e) {
	// System.out.println("About");
	// }
	// });
	// MenuItem item2 = new MenuItem("Preferences");
	// item2.setOnAction(new EventHandler<ActionEvent>() {
	// public void handle(ActionEvent e) {
	// System.out.println("Preferences");
	// }
	// });
	// contextMenu.getItems().addAll(item1, item2);
	// return contextMenu;
	// }

	public void onMouseClicked(javafx.scene.input.MouseEvent event) {
		// TableViewSelectionModel selectionModel = getSelectionModel();
		// ObservableList selectedCells = selectionModel.getSelectedCells();
		// TablePosition tablePosition = (TablePosition) selectedCells.get(0);
		// TableColumn tableColumn = tablePosition.getTableColumn();
		// TableRow<?> row = getRow(tablePosition.getRow());
		onRowClicked(event.getScreenX(), event.getScreenY());
	}

	public void onRowClicked(double x, double y) {
		// TODO add handler for keyboard for escape, enter and space (see main
		// menu) that calls this method

		// double y = row.getBoundsInParent().getMinY();
		// double height = getHeight();
		// boolean showBelowNode = (y>(height/2));
		//
		// PopupVPosition vPosition = showBelowNode ?PopupVPosition.TOP:
		// PopupVPosition.BOTTOM;
		// AnchorLocation anchorLocation=showBelowNode
		// ?AnchorLocation.CONTENT_BOTTOM_LEFT: AnchorLocation.CONTENT_TOP_LEFT;
		// System.out.println(showBelowNode+":"+anchorLocation);

		// double x = row.getBoundsInParent().getMinX();
		// double y = row.getBoundsInParent().getMinY();

		showRowPopupMenu(x, y);
		// JFXPopup rowMenu = createRowMenu(row);
		// rowMenu.setAnchorLocation(anchorLocation);
		// rowMenu.show(row, vPosition, PopupHPosition.RIGHT);
	}

	private void showRowPopupMenu(double x, double y) {
		if (tableInfo != null) {
			popupMenu.getContent().clear();
			popupMenu.getContent().add(createRowMenu());
			popupMenu.show(this, x, y);
		}
	}

	private ItemTreePanel createRowMenu() {
		Object selectedObject = getSelectionModel().getSelectedItem();
		Collection<Item> serviceObjectItems = tableInfo.getRowMenuItems(selectedObject);
		ItemTreePanel rowMenuContent = new ItemTreePanel(serviceObjectItems, tableInfo.getLanguageProvider(),
				popupMenu);
		return rowMenuContent;
	}

	/**
	 * @param row
	 *            Index of the row
	 * @return the corresponding row
	 */
	public TableRow<?> getRow(int row) {
		List<Node> current = getChildrenUnmodifiable();
		while (current.size() == 1) {
			current = ((Parent) current.get(0)).getChildrenUnmodifiable();
		}

		current = ((Parent) current.get(1)).getChildrenUnmodifiable();
		while (!(current.get(0) instanceof TableRow)) {
			current = ((Parent) current.get(0)).getChildrenUnmodifiable();
		}

		Node node = current.get(row);
		if (node instanceof TableRow) {
			return (TableRow<?>) node;
		} else {
			throw new RuntimeException("Expected Group with only TableRows as children");
		}
	}

	// private void showRowMenuItems() {
	// LanguageProvider languageProvider =
	// userInterfaceContainer.get(LanguageProvider.class);
	//
	// TreeItem<Item> rootNode = new TreeItem<>(new Item(languageProvider));
	// rootNode.setExpanded(true);
	// ItemTreePanel itemTreeView = new ItemTreePanel(rootNode);
	//
	// for (Item rowMenuItem : rowMenuItems) {
	// TreeItem<Item> rowMenuNode = new TreeItem<>(rowMenuItem);
	// rootNode.getChildren().add(rowMenuNode);
	// };
	//
	// JFXPopup popup = new JFXPopup();
	// popup.setPopupContent(itemTreeView);
	// popup.setAnchorLocation(AnchorLocation.CONTENT_TOP_RIGHT);
	// popup.show(getSe);
	// }

	private void hideHeader() {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				// See
				// https://stackoverflow.com/questions/27118872/how-to-hide-tableview-column-header-in-javafx-8
				Pane header = (Pane) lookup("TableHeaderRow");
				if (header != null) {
					header.setVisible(false);
					setLayoutY(-header.getHeight());
					autosize();
				}
			}
		});
	}

	protected void addStyleClass() {
		getStyleClass().add(StyleSheet.createStyleClassName(Table.class));
	}

	public static void appendStyleGroups(StyleSheet styleSheet) {
		appendStyleGroups(styleSheet, Table.class, ReflectColorName.CONTENT.BACKGROUND(),
				ReflectColorName.CONTENT.BACKGROUND_12());
	}

	public static void appendStyleGroups(StyleSheet styleSheet, Class<? extends Table> componentClass,
			String backGroundColor, String backGroundHighLighted) {
		styleSheet.addStyleGroup(StyleSelector.createFor(componentClass)).getProperties()
				.setFont(MaterialFont.getRobotoRegular(ROW_FONT_SIZE))
				// remove focus border
				.setBackground(backGroundColor);
		styleSheet.addStyleGroup(StyleSelector.createFor(componentClass).appendChild("column-header")).getProperties()
				.setBackground(backGroundColor)
				.setBorderColor(ReflectColorName.CONTENT.TRANSPARENT(), ReflectColorName.CONTENT.TRANSPARENT(),
						backGroundHighLighted, ReflectColorName.CONTENT.TRANSPARENT())
				.setSize(ROW_HEIGHT);
		styleSheet.addStyleGroup(StyleSelector.createFor(componentClass).appendChild("column-header-background"))
				.getProperties()
				// hide vertical line in header
				.setBackground(backGroundColor);
		styleSheet
				.addStyleGroup(StyleSelector.createFor(componentClass).appendChild("column-header-background")
						.appendChild("filler"))
				.getProperties().setBackground(backGroundColor).setBorderColor(ReflectColorName.CONTENT.TRANSPARENT(),
						ReflectColorName.CONTENT.TRANSPARENT(), backGroundHighLighted,
						ReflectColorName.CONTENT.TRANSPARENT());
		styleSheet
				.addStyleGroup(
						StyleSelector.createFor(componentClass).appendChild("column-header").appendChild(Label.class))
				.getProperties().setFont(MaterialFont.getRobotoMedium(HEADER_FONT_SIZE))
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND()).setFontWeight(FontWeight.NORMAL)
				.setProperty("-fx-alignment", "CENTER-LEFT");
		styleSheet.addStyleGroup(StyleSelector.createFor(".table-column")).getProperties()
				.setBorderColor(ReflectColorName.CONTENT.TRANSPARENT()).setProperty("-fx-alignment", "CENTER-LEFT");
		styleSheet.addStyleGroup(StyleSelector.createFor(".table-row-cell")).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND()).setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setBorderColor(ReflectColorName.CONTENT.TRANSPARENT(), ReflectColorName.CONTENT.TRANSPARENT(),
						backGroundHighLighted, ReflectColorName.CONTENT.TRANSPARENT())
				.setCellSize(ROW_HEIGHT);
		styleSheet.addStyleGroup(StyleSelector.createFor(".table-row-cell").appendFocused()).getProperties()
				.setBackground(ReflectColorName.CONTENT.BACKGROUND_12())
				.setTextFill(ReflectColorName.CONTENT.FOREGROUND())
				.setBorderColor(ReflectColorName.CONTENT.TRANSPARENT(), ReflectColorName.CONTENT.TRANSPARENT(),
						backGroundHighLighted, ReflectColorName.CONTENT.TRANSPARENT());
	}
}
