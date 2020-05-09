package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.gui.component.table.info.TableInfoForFormTabProperty;
import nth.reflect.fw.gui.component.table.info.column.TableInfoException;

public abstract class TableFieldFactory implements PropertyFieldFactory {

	/**
	 * @return true if {@link TableInfoForFormTabProperty} can be created (if not:
	 *         property type is not supported as table)
	 */
	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		FormTab formTab = info.getFormTab();
		PropertyValueModel propertyValueModel = info.getPropertyValueModel();
		try {
			new TableInfoForFormTabProperty(formTab, propertyValueModel);
			return true;
		} catch (TableInfoException e) {
			return false;
		}
	}

}
