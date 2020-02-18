package nth.reflect.fw.ui.swing.tab.grid;

import javax.swing.table.TableModel;

public interface DomainTableModel extends TableModel {
	public Object getDomainValue(int rowIndex);
}
