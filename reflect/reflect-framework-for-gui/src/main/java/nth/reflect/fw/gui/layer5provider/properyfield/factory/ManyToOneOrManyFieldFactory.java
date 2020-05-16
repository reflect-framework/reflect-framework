package nth.reflect.fw.gui.layer5provider.properyfield.factory;

import java.util.Optional;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class ManyToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(FormTab formTab, PropertyValueModel propertyValueModel) {
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		TypeInfo typeInfo = propertyInfo.getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		} else {
			Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getGenericTypeInfo();
			return arrayOrCollectionTypeInfo.get().isDomainClass();
		}
	}

}
