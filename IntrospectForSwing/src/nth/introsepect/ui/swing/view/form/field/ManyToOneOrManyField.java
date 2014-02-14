package nth.introsepect.ui.swing.view.form.field;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import nth.introsepect.ui.swing.item.menubar.MenuBar;
import nth.introsepect.ui.swing.item.popupmenu.PopupMenu;
import nth.introsepect.ui.swing.style.ColorUtil;
import nth.introsepect.ui.swing.view.table.MethodTableModel;
import nth.introspect.provider.userinterface.Refreshable;
import nth.introspect.provider.userinterface.view.FormView;
import nth.introspect.ui.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.valuemodel.PropertyValueModel;
import nth.introspect.valuemodel.ReadOnlyValueModel;


public class ManyToOneOrManyField extends JPanel  implements Refreshable{

	private static final long serialVersionUID = -3143477177672443570L;
	private static final int TABLE_HEIGHT = 200;
	private PropertyValueModel propertyValueModel;
	private ReadOnlyValueModel methodValueModel;
	private ReadOnlyValueModel selectedRowsValueModel;
	private MethodTableModel tableModel;
	private JTable table;
	private PopupMenu menuPopUp;
	private MenuBar menuBar;
	private final FormView formView;

	public ManyToOneOrManyField(FormView formView, PropertyValueModel propertyValueModel) {
		this.formView = formView;
		this.propertyValueModel = propertyValueModel;
		
		setBackground(ColorUtil.getDark());
		
		setLayout(new BorderLayout());

		methodValueModel = createMethodReturnValueModel();
		selectedRowsValueModel = createSelectedRowsValueModel();

		
		tableModel = new MethodTableModel(methodValueModel);
		table = createTable(tableModel);
		JScrollPane tabelContainer = new JScrollPane(table);
		tabelContainer.getViewport().setBackground(ColorUtil.getLightColor());
		tabelContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE,TABLE_HEIGHT));//set height
		
//		List<Item> menuItems = TableViewItemFactory.createMenuItems(methodInfo, serviceObject, tableModel, methodInfo, methodParameterValue, selectedRowsValueModel, methodValueModel);
		List<Item> menuItems=new ArrayList<Item>();//TODO  
		menuPopUp = createPopUpMenu(menuItems);
		menuBar=createMenuBar(menuItems);
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
				//List<Item> menuItems = TableViewItemFactory.createMenuItems(methodInfo, serviceObject, tableModel, methodInfo, methodParameterValue, selectedRowsValueModel, methodValueModel);
				List<Item> menuItems =new ArrayList<Item>();
				menuPopUp.repopulate(menuItems);
				menuPopUp.show(table, e.getX(), e.getY());
			}
		});
		return table;
	}

	public PopupMenu createPopUpMenu(final List<Item> menuItems) {
		return new PopupMenu(menuItems);
	}
	
	private MenuBar createMenuBar(List<Item> menuItems) {
		return new MenuBar(menuItems);
	}


	private ReadOnlyValueModel createMethodReturnValueModel() {
		return new ReadOnlyValueModel() {

			@Override
			public Object getValue() {
				return propertyValueModel.getValue();
			}

			@Override
			public Class<?> getValueType() {
				return propertyValueModel.getPropertyInfo().getPropertyType().getTypeOrGenericCollectionType();
			}

			@Override
			public boolean canGetValue() {
				return true;// TODO only true when property does not return null or a empty collction?
			}
		};
	}

	private ReadOnlyValueModel createSelectedRowsValueModel() {
		return new ReadOnlyValueModel() {

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
					// multiple rows selected
					List<Object> selectedDomainObjects = new ArrayList<Object>();
					for (int selectedRow : table.getSelectedRows()) {
						Object domainObject = tableModel.getDomainValue(selectedRow);
						selectedDomainObjects.add(domainObject);
					}
					return selectedDomainObjects;
				}
			}

			@Override
			public Class<?> getValueType() {
				return propertyValueModel.getPropertyInfo().getPropertyType().getTypeOrGenericCollectionType();
			}

			@Override
			public boolean canGetValue() {
				return table.getSelectedRow() != -1;// can only get a value when a row is selected
			}

		};
	}

	@Override
	public void refresh() {
		//update table
		tableModel.refresh();
		//update menus
		List<Item> menuItems = ItemFactory.createFormViewRelationalFieldItems(formView, propertyValueModel);
		//List<Item> menuItems =new ArrayList<Item>();//TODO
		menuPopUp.repopulate(menuItems);
		menuBar.repopulate(menuItems);
	}


}
