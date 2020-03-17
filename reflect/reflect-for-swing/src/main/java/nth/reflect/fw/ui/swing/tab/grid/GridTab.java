package nth.reflect.fw.ui.swing.tab.grid;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.gui.component.tab.grid.GridTabMenuItems;
import nth.reflect.fw.gui.component.table.info.TableInfo;
import nth.reflect.fw.gui.component.table.info.TableInfoForGridTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.swing.item.menubar.MenuBar;
import nth.reflect.fw.ui.swing.item.popupmenu.PopupMenu;
import nth.reflect.fw.ui.swing.tab.Tab;

public class GridTab extends Tab implements nth.reflect.fw.gui.component.tab.grid.GridTab {

	private static final String ON_ROW_CLICK = "onRowClick";
	private static final long serialVersionUID = 6381153012201315532L;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final JTable grid;
	private final MethodTableModel tableModel;
	private final MenuBar menuBar;
	private final PopupMenu menuPopUp;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final LanguageProvider languageProvider;

	public GridTab(UserInterfaceContainer userInterfaceContainer, Object methodOwner, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;

		setLayout(new BorderLayout());

		TableInfo tableInfo = new TableInfoForGridTab(this);
		tableModel = new MethodTableModel(tableInfo);
		grid = createGrid(tableModel);
		JScrollPane tableContainer = new JScrollPane(grid);
		tableContainer.getViewport().setBackground(grid.getBackground());
		// tableContainer.setFocusable(false); TODO test
		// tableContainer.addMouseListener TODO test

		Collection<Item> menuItems = new GridTabMenuItems(this);
		menuPopUp = createPopUpMenu(menuItems);
		menuBar = createMenuBar(menuItems);
		add(menuBar, BorderLayout.NORTH);
		add(tableContainer, BorderLayout.CENTER);
	}

	private JTable createGrid(final MethodTableModel tableModel) {
		final JTable grid = new JTable();
		grid.setModel(tableModel);
		grid.setRowHeight(22);// Row HEIGHT a bit higher than default: 1.5 * 16

		grid.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				onTableRowSelect(e.getX(), e.getY());
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

		});

		// register space and enter keys to open the context menu. Note that we
		// do not use the key listener because we want to override the default
		// enter key behavior (go to next row)
		grid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ON_ROW_CLICK);
		grid.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), ON_ROW_CLICK);
		grid.getActionMap().put(ON_ROW_CLICK, new AbstractAction() {

			private static final long serialVersionUID = -7373360094398512228L;

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = grid.getSelectedRow();
				Rectangle positionInTable = grid.getCellRect(selectedRow, 0, true);
				onTableRowSelect(positionInTable.x + 20, positionInTable.y);

			}
		});
		return grid;
	}

	protected void onTableRowSelect(int x, int y) {
		Collection<Item> menuItems = new GridTabMenuItems(GridTab.this);
		menuPopUp.repopulate(menuItems);
		menuPopUp.show(grid, x, y);
	}

	public PopupMenu createPopUpMenu(final Collection<Item> menuItems) {
		return new PopupMenu(menuItems);
	}

	private MenuBar createMenuBar(Collection<Item> menuItems) {
		return new MenuBar(menuItems);
	}

	@Override
	public String getDisplayName() {
		return actionMethodInfo.createTitle(methodParameterValue).translate(languageProvider);
	}

	@Override
	public String getDescription() {
		return actionMethodInfo.createTitle(methodParameterValue).translate(languageProvider);
	}

	@Override
	public void onRefresh() {
		// get selected domain object
		Object selectedDomainObject = null;
		int selectedRow = grid.getSelectedRow();
		if (selectedRow != -1) {
			selectedDomainObject = tableModel.getDomainValue(selectedRow);
		}

		// update table
		tableModel.refresh();
		// update menus
		Collection<Item> menuItems = new GridTabMenuItems(this);
		menuPopUp.repopulate(menuItems);
		menuBar.repopulate(menuItems);
		// set focus (preferably on the same domain object)
		selectedRow = tableModel.getRow(selectedDomainObject);
		if (selectedRow == -1) {
			selectedRow = 0;
		}
		grid.changeSelection(selectedRow, 0, false, false);
		grid.requestFocus();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					int[] selectedRows = grid.getSelectedRows();
					switch (selectedRows.length) {
					case 0:
						// nothing selected
						return null;
					case 1:
						// single row selected
						return tableModel.getDomainValue(selectedRows[0]);
					default:
						throw new RuntimeException("Table must be in single selection mode!!!");// TODO
																								// in
																								// future:
																								// support
																								// multi
																								// selection
																								// to!
					// // multiple rows selected
					// List<Object> selectedDomainObjects = new
					// ArrayList<Object>();
					// for (int selectedRow : table.getSelectedRows()) {
					// Object domainObject =
					// tableModel.getDomainValue(selectedRow);
					// selectedDomainObjects.add(domainObject);
					// }
					// return selectedDomainObjects;
					}
				}

				@Override
				public Class<?> getValueType() {
					return actionMethodInfo.getReturnTypeInfo().getType();
				}

				@Override
				public boolean canGetValue() {
					return grid.getSelectedRow() != -1;// can only get a value
														// when a row is
														// selected
				}

			};
		}
		return selectedRowsModel;
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
