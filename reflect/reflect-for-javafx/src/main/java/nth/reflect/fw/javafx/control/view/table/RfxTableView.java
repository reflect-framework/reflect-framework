package nth.reflect.fw.javafx.control.view.table;

import java.net.URL;
import java.util.List;

import javafx.scene.layout.BorderPane;
import nth.reflect.fw.generic.util.TitleUtil;
import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.javafx.control.table.RfxTable;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.item.ItemFactory;

public class RfxTableView extends BorderPane implements nth.reflect.fw.ui.view.TableView {

	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private ReadOnlyValueModel allRowsModel;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final ReflectionProvider reflectionProvider;
	private final RfxTable table;

	public RfxTableView(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);

		table = new RfxTable(this);
		setCenter(table);
		
		
		// menuPopUp = createPopUpMenu(menuItems);
		// menuBar = createMenuBar(menuItems);
		// add(menuBar, BorderLayout.NORTH);
		// add(tableContainer, BorderLayout.CENTER);

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
		List<Item> menuItems = ItemFactory.createTableViewRowMenuItems(RfxTableView.this);
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
		return actionMethodInfo.getFontIconUrl(methodOwner);
	}

	@Override
	public void onViewActivate() {
		// TODO refresh table
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
					return table.getSelectionModel().getSelectedItem();
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getGenericReturnType();
				}

				@Override
				public boolean canGetValue() {
					return !table.getSelectionModel().isEmpty();
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
						UserInterfaceController userInterfaceController = getUserInterfaceContainer()
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
								// null or a empty collection?
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
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}

}
