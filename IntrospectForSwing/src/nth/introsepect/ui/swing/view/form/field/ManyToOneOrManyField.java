package nth.introsepect.ui.swing.view.form.field;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

public class ManyToOneOrManyField extends JPanel implements Refreshable {

	private static final long serialVersionUID = -3143477177672443570L;
	private static final int TABLE_HEIGHT = 200;
	private PropertyValueModel propertyValueModel;
	private MethodTableModel tableModel;
	private JTable table;
	private PopupMenu menuPopUp;
	private MenuBar menuBar;
	private final FormView formView;
	private ReadOnlyValueModel selectedRowModel;

	public ManyToOneOrManyField(FormView formView,
			PropertyValueModel propertyValueModel) {
		this.formView = formView;
		this.propertyValueModel = propertyValueModel;

		setBackground(ColorUtil.getDark());

		setLayout(new BorderLayout());

		tableModel = new MethodTableModel(propertyValueModel);
		table = createTable(tableModel);
		JScrollPane tabelContainer = new JScrollPane(table);
		tabelContainer.getViewport().setBackground(ColorUtil.getLightColor());
		tabelContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE,
				TABLE_HEIGHT));// set height

		List<Item> menuItems = ItemFactory.createFormViewRelationalFieldItems(
				formView, getSelectedRowModel(),
				propertyValueModel.getPropertyInfo());
		menuPopUp = createPopUpMenu(menuItems);
		menuBar = createMenuBar(menuItems);
		add(menuBar, BorderLayout.NORTH);
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
				List<Item> menuItems = ItemFactory
						.createFormViewRelationalFieldItems(formView,
								getSelectedRowModel(),
								propertyValueModel.getPropertyInfo());
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

	public ReadOnlyValueModel getSelectedRowModel() {
		if (selectedRowModel == null) {
			selectedRowModel = new ReadOnlyValueModel() {

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
								"Table must be in single selection mode!!!");
						// TODO in future: support multiple selection
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
					return propertyValueModel.getPropertyInfo()
							.getPropertyType().getTypeOrGenericCollectionType();
				}

				@Override
				public boolean canGetValue() {
					// can only get a value when a row is selected
					return table.getSelectedRow() != -1;
				}

			};
		}
		return selectedRowModel;
	}

	@Override
	public void refresh() {
		// update table
		tableModel.refresh();
		// update menus
		List<Item> menuItems = ItemFactory.createFormViewRelationalFieldItems(
				formView, getSelectedRowModel(),
				propertyValueModel.getPropertyInfo());
		// Do not have to refresh the menu, because it is refreshed when opened,
		// so no need for menuPopUp.repopulate(menuItems);
		menuBar.repopulate(menuItems);
	}

}
