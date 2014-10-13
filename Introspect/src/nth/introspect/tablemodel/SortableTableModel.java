package nth.introspect.tablemodel;

import java.util.List;

import javax.swing.RowSorter.SortKey;
import javax.swing.table.TableModel;

public interface SortableTableModel extends TableModel {
	/**
	 * This method is called by {@link RowSorterForSortableTableModel} when a table needs to be sorted, because a user clicked a column header
	 */
	public void sort(List<? extends SortKey> sortKeys);
}
