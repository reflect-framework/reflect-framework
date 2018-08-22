package nth.reflect.fw.ui.swing.view.table;

import javax.swing.table.TableModel;

public interface DomainTableModel extends TableModel {
	public Object getDomainValue(int rowIndex);
}
