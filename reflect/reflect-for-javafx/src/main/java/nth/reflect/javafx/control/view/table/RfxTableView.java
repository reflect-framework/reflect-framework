package nth.reflect.javafx.control.view.table;

import java.net.URL;
import java.text.Format;
import java.util.List;

import javax.swing.GroupLayout.Alignment;

import com.sun.javafx.collections.ObservableListWrapper;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SetProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.FontWeight;
import javafx.util.Callback;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.generic.util.TypeUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.format.impl.JavaFormatFactory;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.introspect.ui.style.MaterialFont;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.fonticon.RfxFontIcon;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;

public class RfxTableView extends TableView<Object> implements nth.introspect.ui.view.TableView {

	private static final int ROW_HEIGHT = 48;
	private static final int ROW_FONT_SIZE = 14;
	private static final int HEADER_FONT_SIZE = 13;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private ReadOnlyValueModel allRowsModel;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final ReflectionProvider reflectionProvider;

	public RfxTableView(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		addStyleClass();

		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		initTable(reflectionProvider, languageProvider, methodOwner, actionMethodInfo,
				methodParameterValue);

		List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		// menuPopUp = createPopUpMenu(menuItems);
		// menuBar = createMenuBar(menuItems);
		// add(menuBar, BorderLayout.NORTH);
		// add(tableContainer, BorderLayout.CENTER);
	}

	private void initTable(ReflectionProvider reflectionProvider, LanguageProvider languageProvider,
			Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameterValue) {

		Class<?> objectClass = actionMethodInfo.getGenericReturnType();
		// Class<?> objectClass = valueModel.getValueType();
		if (TypeUtil.isJavaType(objectClass) || TypeUtil.isEnum(objectClass)) {
			TableColumn<Object, String> propertyColumn = new TableColumn(
					languageProvider.getText("Values"));
			propertyColumn.setCellValueFactory(
					createCellValueFactoryForJavaTypeOrEnum(languageProvider, objectClass));
			// TODO setItems
		} else {
			setItems(createObservableList(methodOwner, actionMethodInfo, methodParameterValue));

			ClassInfo classInfo = reflectionProvider.getClassInfo(objectClass);
			List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAnsVisibleInTable();
			for (PropertyInfo propertyInfo : propertyInfos) {
				TableColumn propertyColumn = new TableColumn(propertyInfo.getDisplayName());
				propertyColumn.setMinWidth(100);
				propertyColumn.setCellValueFactory(
						new PropertyValueFactory<>(propertyInfo.getSimpleName()));
				getColumns().add(propertyColumn);
			}
		}

		ColumnAutoSizer.autoFitTable(this);

	}

	private Callback<CellDataFeatures<Object, String>, ObservableValue<String>> createCellValueFactoryForJavaTypeOrEnum(
			LanguageProvider languageProvider, Class<?> objectClass) {
		JavaFormatFactory formatFactory = new JavaFormatFactory(languageProvider);
		Format format = formatFactory.create(objectClass);
		return new Callback<CellDataFeatures<Object, String>, ObservableValue<String>>() {

			@Override
			public ObservableValue<String> call(CellDataFeatures<Object, String> param) {
				String value = format.format(param);
				return new ReadOnlyObjectWrapper<String>(value);
			}
		};
	}

	private ObservableList<Object> createObservableList(Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		try {
			Object result = actionMethodInfo.invoke(methodOwner, methodParameterValue);
			List<Object> list = (List<Object>) result;
			// TODO create a createObservableList for all types, and that can be
			// updated when needed
			return new ObservableListWrapper<Object>(list);
		} catch (Exception e) {
			UserInterfaceController userInterfaceController = getuserInterfaceContainer()
					.get(UserInterfaceController.class);
			userInterfaceController.showErrorDialog(getViewTitle(), "Error getting table values.",
					e);
			return null;
		}
	}

	// private JTable createTable(final MethodTableModel tableModel) {
	// final JTable table = new JTable();
	// table.setModel(tableModel);
	// table.setRowHeight(22);// Row HEIGHT a bit higher than default: 1.5 * 16
	//
	// table.addMouseListener(new MouseListener() {
	//
	// @Override
	// public void mouseReleased(MouseEvent e) {
	// onTableRowSelect(e.getX(), e.getY());
	// }
	//
	// @Override
	// public void mousePressed(MouseEvent e) {
	// }
	//
	// @Override
	// public void mouseExited(MouseEvent e) {
	// }
	//
	// @Override
	// public void mouseEntered(MouseEvent e) {
	// }
	//
	// @Override
	// public void mouseClicked(MouseEvent e) {
	// }
	//
	// });
	//
	// // register space and enter keys to open the context menu. Note that we
	// // do not use the key listener because we want to override the default
	// // enter key behavior (go to next row)
	// table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
	// KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ON_ROW_CLICK);
	// table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
	// KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), ON_ROW_CLICK);
	// table.getActionMap().put(ON_ROW_CLICK, new AbstractAction() {
	//
	// private static final long serialVersionUID = -7373360094398512228L;
	//
	// @Override
	// public void actionPerformed(ActionEvent e) {
	// int selectedRow = table.getSelectedRow();
	// Rectangle positionInTable = table.getCellRect(selectedRow, 0,
	// true);
	// onTableRowSelect(positionInTable.x + 20, positionInTable.y);
	//
	// }
	// });
	// return table;
	// }

	protected void onTableRowSelect(int x, int y) {
		List<Item> menuItems = ItemFactory.createTableViewRowItems(RfxTableView.this);
		// menuPopUp.repopulate(menuItems);
		// menuPopUp.show(table, x, y);
	}

	// public PopupMenu createPopUpMenu(final List<Item> menuItems) {
	// return new PopupMenu(menuItems);
	// }

	// private MenuBar createMenuBar(List<Item> menuItems) {
	// return new MenuBar(menuItems);
	// }

	@Override
	public String getViewTitle() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue);
	}

	@Override
	public String getViewDescription() {
		return TitleUtil.createTitle(reflectionProvider, actionMethodInfo, methodParameterValue);
	}

	@Override
	public URL getViewIconURL() {
		return actionMethodInfo.getIconURL(methodOwner);
	}

	@Override
	public void onViewActivate() {
		// // get selected domain object
		// Object selectedDomainObject = null;
		// int selectedRow = table.getSelectedRow();
		// if (selectedRow != -1) {
		// selectedDomainObject = tableModel.getDomainValue(selectedRow);
		// }
		//
		// // update table
		// tableModel.refresh();
		// // update menus
		// List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		// menuPopUp.repopulate(menuItems);
		// menuBar.repopulate(menuItems);
		// // set focus (preferably on the same domain object)
		// selectedRow = tableModel.getRow(selectedDomainObject);
		// if (selectedRow == -1) {
		// selectedRow = 0;
		// }
		// table.changeSelection(selectedRow, 0, false, false);
		// table.requestFocus();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					return getSelectionModel().getSelectedItem();
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getGenericReturnType();
				}

				@Override
				public boolean canGetValue() {
					return !getSelectionModel().isEmpty();
				}

			};
		}
		return selectedRowsModel;
	}

	@Override
	public ReadOnlyValueModel getAllRowsModel() {
		if (allRowsModel == null) {

			allRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					try {
						return actionMethodInfo.invoke(methodOwner, methodParameterValue);
					} catch (Exception e) {
						UserInterfaceController userInterfaceController = getuserInterfaceContainer()
								.get(UserInterfaceController.class);
						userInterfaceController.showErrorDialog(getViewTitle(),
								"Error getting table values.", e);
						return null;
					}
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getGenericReturnType();
				}

				@Override
				public boolean canGetValue() {
					return true;// TODO only true when method does not return
								// null or a empty collction?
				}
			};
		}
		return allRowsModel;

	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodOwner() {
		return methodOwner;
	}

	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
	}

	@Override
	public UserInterfaceContainer getuserInterfaceContainer() {
		return userInterfaceContainer;
	}

	protected void addStyleClass() {
		getStyleClass().add(RfxStyleSheet.createStyleClassName(RfxTableView.class));
	}

	public static void appendStyleGroups(RfxStyleSheet styleSheet) {
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(RfxTableView.class)).getProperties()
				.setFont(MaterialFont.getRobotoRegular(ROW_FONT_SIZE))
				// remove focus border
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND());
//	TODO does not work	styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-view .column-header-background"))
//			.getProperties().setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND());

		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-view .column-header"))
				.getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
		.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
				MaterialColorSetCssName.CONTENT.TRANSPARENT(),
				MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
				MaterialColorSetCssName.CONTENT.TRANSPARENT());
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-view .column-header .label"))
				.getProperties().setFont(MaterialFont.getRobotoMedium(HEADER_FONT_SIZE))
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND2())
				.setFontWeight(FontWeight.NORMAL);
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-column")).getProperties()
				.setBorderColor("transparent").setProperty("-fx-alignment", "CENTER-LEFT");
		// TODO RfxTableView.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-row-cell")).getProperties()
				.setBackground(MaterialColorSetCssName.CONTENT.BACKGROUND())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT())
				.setCellSize(ROW_HEIGHT);
		// TODO RfxTableView.class in style selector
		styleSheet.addStyleGroup(RfxStyleSelector.createFor(".table-row-cell").appendFocused())
				.getProperties().setBackground(MaterialColorSetCssName.CONTENT.FOREGROUND3())
				.setTextFill(MaterialColorSetCssName.CONTENT.FOREGROUND1())
				.setBorderColor(MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT(),
						MaterialColorSetCssName.CONTENT.BACKGROUND_HIGHLIGHTED(),
						MaterialColorSetCssName.CONTENT.TRANSPARENT())
				.setProperty("-fx-table-cell-border-color", "red red red red");
	}

	private static int HEADER_FONT_SIZE() {
		// TODO Auto-generated method stub
		return 0;
	}
}
