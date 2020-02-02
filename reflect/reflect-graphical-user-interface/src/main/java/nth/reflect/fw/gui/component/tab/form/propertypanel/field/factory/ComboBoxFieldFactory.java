package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public abstract class ComboBoxFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		PropertyInfo propertyInfo = info.getPropertyInfo();
		boolean isEnum = propertyInfo.getTypeInfo().isEnum();
		boolean hasOptionsAnnotation = propertyInfo.hasOptions();
		return isEnum || hasOptionsAnnotation;
	}

}
