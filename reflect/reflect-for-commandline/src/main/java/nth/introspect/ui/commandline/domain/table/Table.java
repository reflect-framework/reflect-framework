package nth.introspect.ui.commandline.domain.table;

import java.util.ArrayList;
import java.util.List;

public class Table {
	private List<Row> rows = new ArrayList<Row>();
	public static final int INDENT = 3;
	public static final int MAX_WIDTH_IN_COLS = 79;
	
	public Row addRow() {
		Row row=new Row();
		rows.add(row);
		return row;
	}

	@Override
	public String toString() {
		StringBuffer table=new StringBuffer();
		for (Row row : rows) {
			table.append(row.toString());
		}
		return table.toString();
	}
	
	
}
