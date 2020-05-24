package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.component.table.info.TableInfoForFormTabProperty;
import nth.reflect.fw.gui.component.table.info.column.TableInfoException;
import nth.reflect.fw.gui.component.table.info.dataprovider.DataProviderFactory;

/**
 * {@link TableField}
 * <p>
 * {@link DataProviderFactory}
 * 
 * @author nilsth
 *
 */
public abstract class TableFieldFactory implements PropertyFieldFactory {

	/**
	 * @return true if {@link TableInfoForFormTabProperty} can be created (if not:
	 *         property type is not supported as table)
	 */
	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		try {
			new TableInfoForFormTabProperty(formTab, propertyValueModel);
			return true;
		} catch (TableInfoException e) {
			return false;
		}
	}

}
