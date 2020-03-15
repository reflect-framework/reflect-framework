package nth.reflect.fw.gui.component.table.info.column;

import nth.reflect.fw.gui.component.table.info.TableInfo;

/**
 * Provides column information for {@link TableInfo}
 * 
 * @author nilsth
 *
 */
public interface ColumnInfo {

	public String toString(Object rowValue);

	public String getName();

	public boolean hasName();
}
