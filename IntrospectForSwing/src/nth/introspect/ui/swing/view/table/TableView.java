package nth.introspect.ui.swing.view.table;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.swing.item.menubar.MenuBar;
import nth.introspect.ui.swing.item.popupmenu.PopupMenu;
import nth.introspect.ui.swing.view.SwingView;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class TableView extends SwingView implements
		nth.introspect.ui.view.TableView {

	private static final String ON_ROW_CLICK = "onRowClick";
	private static final long serialVersionUID = 6381153012201315532L;
	private final Object methodOwner;
	private final MethodInfo methodInfo;
	private final Object methodParameterValue;
	private JTable table;
	private MethodTableModel tableModel;
	private MenuBar menuBar;
	private PopupMenu menuPopUp;
	private ReadOnlyValueModel allRowsModel;
	private ReadOnlyValueModel selectedRowsModel;
	private final UserInterfaceContainer userInterfaceContainer;
	private final DomainInfoProvider domainInfoProvider;

	public TableView(UserInterfaceContainer userInterfaceContainer, Object methodOwner, MethodInfo methodInfo,
			Object methodParameterValue) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;

		setLayout(new BorderLayout());

		domainInfoProvider= userInterfaceContainer.getDomainInfoProvider();
		tableModel = new MethodTableModel(domainInfoProvider, getAllRowsModel());
		table = createTable(tableModel);
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.getViewport().setBackground(table.getBackground());
//		tableContainer.setFocusable(false); TODO test
//		tableContainer.addMouseListener TODO test		

		List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		menuPopUp = createPopUpMenu(menuItems);
		menuBar = createMenuBar(menuItems);
		add(menuBar, BorderLayout.NORTH);
		add(tableContainer, BorderLayout.CENTER);
	}

	private JTable createTable(final MethodTableModel tableModel) {
		final JTable table = new JTable();
		table.setModel(tableModel);
		table.setRowHeight(22);// Row height a bit higher than default: 1.5 * 16

		table.addMouseListener(new MouseListener() {

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
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ON_ROW_CLICK);
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), ON_ROW_CLICK);
		table.getActionMap().put(ON_ROW_CLICK, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Rectangle positionInTable = table.getCellRect(selectedRow, 0,
						true);
				Point positionInWindow = table.getLocation(positionInTable
						.getLocation());
				onTableRowSelect(positionInTable.x + 20, positionInTable.y);

			}
		});
		return table;
	}

	protected void onTableRowSelect(int x, int y) {
		List<Item> menuItems = ItemFactory
				.createTableViewRowItems(TableView.this);
		menuPopUp.repopulate(menuItems);
		menuPopUp.show(table, x, y);
	}

	public PopupMenu createPopUpMenu(final List<Item> menuItems) {
		return new PopupMenu(menuItems);
	}

	private MenuBar createMenuBar(List<Item> menuItems) {
		return new MenuBar(menuItems);
	}

	@Override
	public String getViewTitle() {
		return TitleUtil.createTitle(domainInfoProvider, methodInfo, methodParameterValue, true);
	}

	@Override
	public String getViewDescription() {
		return TitleUtil.createTitle(domainInfoProvider, methodInfo, methodParameterValue, false);
	}

	@Override
	public URI getViewIconURI() {
		return methodInfo.getIconURI(methodOwner);
	}

	@Override
	public void onViewActivate() {
		// get selected domain object
		Object selectedDomainObject = null;
		int selectedRow = table.getSelectedRow();
		if (selectedRow != -1) {
			selectedDomainObject = tableModel.getDomainValue(selectedRow);
		}

		// update table
		tableModel.refresh();
		// update menus
		List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		menuPopUp.repopulate(menuItems);
		menuBar.repopulate(menuItems);
		// set focus (preferably on the same domain object)
		selectedRow = tableModel.getRow(selectedDomainObject);
		if (selectedRow == -1) {
			selectedRow = 0;
		}
		table.changeSelection(selectedRow, 0, false, false);
		table.requestFocus();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel == null) {
			selectedRowsModel = new ReadOnlyValueModel() {

				@Override
				public Object getValue() {
					int[] selectedRows = table.getSelectedRows();
					switch (selectedRows.length) {
					case 0:
						// nothing selected
						return null;
					case 1:
						// single row selected
						return tableModel.getDomainValue(selectedRows[0]);
					default:
						throw new RuntimeException(
								"Table must be in single selection mode!!!");// TODO
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
					return methodInfo.getReturnType()
							.getTypeOrGenericCollectionType();
				}

				@Override
				public boolean canGetValue() {
					return table.getSelectedRow() != -1;// can only get a value
														// when a row is
														// selected
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
						return methodInfo.invoke(methodOwner,
								methodParameterValue);
					} catch (Exception e) {
						UserInterfaceProvider<?> userInterfaceProvider = getuserInterfaceContainer().getUserInterfaceProvider();
						userInterfaceProvider.showErrorDialog(getViewTitle(),
								"Error getting table values.", e);
						return null;
					}
				}

				@Override
				public Class<?> getValueType() {
					return methodInfo.getReturnType()
							.getTypeOrGenericCollectionType();
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
	public MethodInfo getMethodInfo() {
		return methodInfo;
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

}
