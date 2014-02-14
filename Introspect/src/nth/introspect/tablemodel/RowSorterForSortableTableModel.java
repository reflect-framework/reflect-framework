package nth.introspect.tablemodel;

import javax.swing.table.TableRowSorter;


/**
 * delegates sorting to the {@link SortableTableModel} by calling its sort method 
 */
public class RowSorterForSortableTableModel extends TableRowSorter<SortableTableModel> {

	private final SortableTableModel sortableTableModel;
	
	public RowSorterForSortableTableModel(SortableTableModel sortableTableModel) {
		super(sortableTableModel);
		this.sortableTableModel = sortableTableModel;
	}
	
	@Override
	public int convertRowIndexToModel(int index) {
		return index; //one on one relation ship, sorting is delegated to the sortableTableModel
	}

	@Override
	public int convertRowIndexToView(int index) {
		return index; //one on one relation ship, sorting is delegated to the sortableTableModel
	}

	
	@Override
	public int getModelRowCount() {
		return sortableTableModel.getRowCount();
	}

	@Override
	public int getViewRowCount() {
		return sortableTableModel.getRowCount();
	}

	@Override
	public void toggleSortOrder(int column) {
		super.toggleSortOrder(column);
		sortableTableModel.sort(getSortKeys());		
	}

}
