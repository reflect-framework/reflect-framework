package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class ManyToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		TypeInfo typeInfo = info.getPropertyInfo().getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		}
		Class<?> genericType = typeInfo.getGenericType();
		ReflectApplication reflectApplication = info.getUserInterfaceContainer().get(ReflectApplication.class);
		boolean isDomainObject = new TypeInfo(reflectApplication, genericType, genericType).isDomainClass();
		return isDomainObject;
	}

}
