package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class ManyToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		TypeInfo typeInfo = info.getPropertyInfo().getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		} else {
			Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getArrayOrCollectionTypeInfo();
			return arrayOrCollectionTypeInfo.get().isDomainClass();
		}
	}

}
