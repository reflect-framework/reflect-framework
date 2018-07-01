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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeCell;
import nth.reflect.javafx.control.style.RfxStyleProperties;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.table.RfxTable;

public class RfxTableView extends BorderPane implements nth.introspect.ui.view.TableView {

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
		return actionMethodInfo.getIconURL(methodOwner);
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
