package nth.introsepect.ui.swing.view.table;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import nth.introsepect.ui.swing.item.menubar.MenuBar;
import nth.introsepect.ui.swing.item.popupmenu.PopupMenu;
import nth.introsepect.ui.swing.view.SwingView;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.ui.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.util.TitleUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class TableView extends SwingView implements nth.introspect.provider.userinterface.view.TableView {

	private static final long serialVersionUID = 6381153012201315532L;
	private final Object serviceObject;
	private final MethodInfo methodInfo;
	private final Object methodParameterValue;
	private JTable table;
	private MethodTableModel tableModel;
	private MenuBar menuBar;
	private PopupMenu menuPopUp;
	private ReadOnlyValueModel allRowsModel;
	private ReadOnlyValueModel selectedRowsModel;

	public TableView(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue) {
		this.serviceObject = serviceObject;
		this.methodInfo = methodInfo;
		this.methodParameterValue = methodParameterValue;

		setLayout(new BorderLayout());

		tableModel = new MethodTableModel(getAllRowsModel());
		table = createTable(tableModel);
		JScrollPane tabelContainer = new JScrollPane(table);
		tabelContainer.getViewport().setBackground(table.getBackground());

		
		List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		menuPopUp = createPopUpMenu(menuItems);menuBar=createMenuBar(menuItems);
		add(createMenuBar(menuItems), BorderLayout.NORTH);
		add(tabelContainer, BorderLayout.CENTER);
	}

	private JTable createTable(final MethodTableModel tableModel) {
		final JTable table = new JTable();
		table.setModel(tableModel);
		table.setRowHeight(22);// Row height a bit higher than default: 1.5 * 16
		
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
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
				List<Item> menuItems = ItemFactory.createTableViewRowItems(TableView.this);
				menuPopUp.repopulate(menuItems);
				menuPopUp.show(table, e.getX(), e.getY());
			}
			
			//FIXME: add keyboard listeners to open popupmenu when the user selects a row with space or enter
		});
		return table;
	}

	public PopupMenu createPopUpMenu(final List<Item> menuItems) {
		return new PopupMenu(menuItems);
	}
	
	private MenuBar createMenuBar(List<Item> menuItems) {
		return new MenuBar(menuItems);
	}

	@Override
	public String getViewTitle() {
		return TitleUtil.createTitle(methodInfo, methodParameterValue, true);
	}

	@Override
	public String getViewDescription() {
		return TitleUtil.createTitle(methodInfo, methodParameterValue, false);
	}

	@Override
	public URI getViewIconURI() {
		return methodInfo.getIconURI(serviceObject);
	}


	@Override
	public void onViewActivate() {
		//update table
		tableModel.refresh();
		//update menus
		List<Item> menuItems = ItemFactory.createTableViewRowItems(this);
		menuPopUp.repopulate(menuItems);
		menuBar.repopulate(menuItems);
		//set focus
		table.requestFocus();
	}

	@Override
	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowsModel==null) {
			selectedRowsModel= new ReadOnlyValueModel() {

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
						throw new RuntimeException("Table must be in single selection mode!!!");//TODO in future: support multi selection to!
//						// multiple rows selected
//						List<Object> selectedDomainObjects = new ArrayList<Object>();
//						for (int selectedRow : table.getSelectedRows()) {
//							Object domainObject = tableModel.getDomainValue(selectedRow);
//							selectedDomainObjects.add(domainObject);
//						}
//						return selectedDomainObjects;
					}
				}

				@Override
				public Class<?> getValueType() {
					return methodInfo.getReturnType().getTypeOrGenericCollectionType();
				}

				@Override
				public boolean canGetValue() {
					return table.getSelectedRow() != -1;// can only get a value when a row is selected
				}

			};
		}
		return selectedRowsModel;
	}

	@Override
	public ReadOnlyValueModel getAllRowsModel() {
		if (allRowsModel==null) {
			
		
		allRowsModel= new ReadOnlyValueModel() {

			@Override
			public Object getValue() {
				try {
					return methodInfo.invoke(serviceObject, methodParameterValue);
				} catch (Exception e) {
					UserInterfaceProvider<?> userInterfacePort = Introspect.getUserInterfaceProvider();
					userInterfacePort.showErrorDialog(getViewTitle(), "Error getting table values.", e);
					return null;
				}
			}

			@Override
			public Class<?> getValueType() {
				return methodInfo.getReturnType().getTypeOrGenericCollectionType();
			}

			@Override
			public boolean canGetValue() {
				return true;// TODO only true when method does not return null or a empty collction?
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
	public Object getServiceObject() {
		return serviceObject;
	}

	

}
