package nth.reflect.fw.ui.swing.tab.grid;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import nth.reflect.fw.gui.component.table.info.TableInfo;
import nth.reflect.fw.gui.component.table.info.column.ColumnInfo;
import nth.reflect.fw.layer1userinterface.controller.Refreshable;

public class MethodTableModel extends AbstractTableModel implements DomainTableModel, Refreshable {

	private static final long serialVersionUID = 605374068245011236L;
	private final TableInfo tableInfo;
	private List<Object> values;

	public MethodTableModel(TableInfo tableInfo) {
		this.tableInfo = tableInfo;
		refresh();
	}

	@Override
	public int getColumnCount() {
		return tableInfo.getColumnInfos().size();
	}

	@Override
	public int getRowCount() {
		return values.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object value = values.get(rowIndex);
		ColumnInfo columnInfo = tableInfo.getColumnInfos().get(columnIndex);
		String cellValue = columnInfo.toString(value);
		return cellValue;
	}

	@Override
	public String getColumnName(int column) {
		ColumnInfo columnInfo = tableInfo.getColumnInfos().get(column);
		if (columnInfo.hasName()) {
			return columnInfo.getName();
		} else {
			return "";
		}
	}

	@Override
	public Object getDomainValue(int rowIndex) {// TODO rename to value?
		return values.get(rowIndex);
	}

	@Override
	public void refresh() {
		try {
			values = tableInfo.getValueList();
			fireTableDataChanged();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public int getRow(Object domainObject) {
		return values.indexOf(domainObject);
	}

}
