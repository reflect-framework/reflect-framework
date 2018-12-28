package nth.reflect.fw.ui.swing.tab.form.proppanel.field;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyField;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldStyle;
import nth.reflect.fw.ui.component.tab.form.propertypanel.PropertyFieldWidth;
import nth.reflect.fw.ui.item.method.menu.FormFieldMenuItems;
import nth.reflect.fw.ui.swing.item.popupmenu.PopupMenu;
import nth.reflect.fw.ui.swing.style.ColorUtil;
import nth.reflect.fw.ui.swing.tab.table.MethodTableModel;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public class ManyToOneOrManyField extends JPanel implements PropertyField {

	private static final int ROW_HEIGHT = 22;
	private static final long serialVersionUID = -3143477177672443570L;
	private static final String ON_ROW_CLICK = "onRowClick";
	private PropertyValueModel propertyValueModel;
	private MethodTableModel tableModel;
	private JTable table;
	private PopupMenu menuPopUp;
	// private MenuBar menuBar;
	private final FormTab formTab;
	private ReadOnlyValueModel selectedRowModel;

	public ManyToOneOrManyField(FormTab formTab, PropertyValueModel propertyValueModel) {
		this.formTab = formTab;
		this.propertyValueModel = propertyValueModel;

		setBackground(ColorUtil.getDark());

		setLayout(new BorderLayout());

		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		tableModel = new MethodTableModel(reflectionProvider, languageProvider, propertyValueModel);
		table = createTable(tableModel);
		JScrollPane tabelContainer = createTableContainer();

		Collection<Item> menuItems = new FormFieldMenuItems(formTab, getSelectedRowModel(),
				propertyValueModel.getPropertyInfo());
		menuPopUp = createPopUpMenu(menuItems);
		// menuBar = createMenuBar(menuItems);
		// add(menuBar, BorderLayout.NORTH);
		add(tabelContainer, BorderLayout.CENTER);
	}

	private JScrollPane createTableContainer() {
		JScrollPane tableContainer = new JScrollPane(table);
		tableContainer.getViewport().setBackground(ColorUtil.getLightColor());
		// set preferred HEIGHT (higher than most fields)
		tableContainer.setPreferredSize(new Dimension(Integer.MAX_VALUE, PropertyFieldStyle.getMaxHeight()));
		tableContainer.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				showPopupMenu(e.getX(), e.getY() - 30);
			}
		});
		return tableContainer;
	}

	@SuppressWarnings("serial")
	private JTable createTable(final MethodTableModel tableModel) {
		final JTable table = new JTable();
		table.setModel(tableModel);
		table.setRowHeight(ROW_HEIGHT);// Row HEIGHT a bit higher than default:
										// 1.5 * 16
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
				showPopupMenu(e.getX(), e.getY());
			}
		});
		// register space and enter keys to open the context menu. Note that we
		// do not use the key listener because we want to override the default
		// enter key behavior (go to next row)
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ON_ROW_CLICK);
		table.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
				.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), ON_ROW_CLICK);
		table.getActionMap().put(ON_ROW_CLICK, new AbstractAction() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Rectangle positionInTable = table.getCellRect(selectedRow, 0, true);
				showPopupMenu(positionInTable.x + 20, positionInTable.y);
			}
		});
		return table;
	}

	public PopupMenu createPopUpMenu(final Collection<Item> menuItems) {
		return new PopupMenu(menuItems);
	}

	// private MenuBar createMenuBar(List<Item> menuItems) {
	// return new MenuBar(menuItems);
	// }

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
						throw new RuntimeException("Table must be in single selection mode!!!");
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
					return propertyValueModel.getPropertyInfo().getPropertyType().getTypeOrGenericCollectionType();
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

	private void showPopupMenu(int x, int y) {
		Collection<Item> menuItems = new FormFieldMenuItems(formTab, getSelectedRowModel(),
				propertyValueModel.getPropertyInfo());
		menuPopUp.repopulate(menuItems);
		menuPopUp.show(table, x, y);
	}

	@Override
	public PropertyFieldWidth getPropertyFieldWidth() {
		return PropertyFieldWidth.FULL;
	}

	@Override
	public void setValueFromDomainProperty(Object propertyValue) {
		// update table
		tableModel.refresh();
		// update menus

		// List<Item> menuItems =
		// ItemFactory.createFormViewRelationalFieldItems(
		// formTab, getSelectedRowModel(),
		// propertyValueModel.getPropertyInfo());
		//
		// Do not have to refresh the menu, because it is refreshed when opened,
		// so no need for menuPopUp.repopulate(menuItems);
		// menuBar.repopulate(menuItems);

	}

}
