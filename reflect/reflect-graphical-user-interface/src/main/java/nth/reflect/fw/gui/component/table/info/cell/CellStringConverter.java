package nth.reflect.fw.gui.component.table.info.cell;

import nth.reflect.fw.generic.util.StringConverter;

/**
 * Interface for CallValueFactories for in a {@link Table}
 * @deprecated replace with {@link StringConverter}
 * @author nilsth
 *
 */
public interface CellStringConverter {

	public String getValue(Object obj);
}
