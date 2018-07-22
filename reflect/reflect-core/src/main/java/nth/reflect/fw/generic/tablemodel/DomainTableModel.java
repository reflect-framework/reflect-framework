package nth.reflect.fw.generic.tablemodel;

import javax.swing.table.TableModel;

public interface DomainTableModel extends TableModel {
	public Object getDomainValue(int rowIndex);
}
