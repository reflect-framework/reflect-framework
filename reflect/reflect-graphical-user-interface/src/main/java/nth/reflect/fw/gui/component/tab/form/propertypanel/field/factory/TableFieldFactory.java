package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class TableFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		TypeInfo typeInfo = info.getPropertyInfo().getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		}
		//TODO return true if there is a is a StringConverter that can convert the type
		Class<?> genericType = typeInfo.getGenericType();
		if (String.class.isAssignableFrom(genericType)) {
			return true;			
		}
		ReflectApplication reflectApplication=info.getUserInterfaceContainer().get(ReflectApplication.class);
		boolean isJavaType = new TypeInfo(reflectApplication, genericType, genericType).isJavaVariableType();
		return isJavaType;
	}

}
