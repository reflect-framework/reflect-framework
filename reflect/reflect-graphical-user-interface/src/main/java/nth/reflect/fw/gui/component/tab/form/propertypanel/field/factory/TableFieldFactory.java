package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class TableFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		TypeInfo typeInfo = info.getPropertyInfo().getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		} else {
			//TODO return true if there is a is a StringConverter that can convert the type
			Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getArrayOrCollectionTypeInfo();
			Class<?> type = arrayOrCollectionTypeInfo.get().getType();
			return String.class.isAssignableFrom(type) || PrimitiveType.isPrimative(type) || PrimitiveType.isWrapper(type);			
		}
	}

}
