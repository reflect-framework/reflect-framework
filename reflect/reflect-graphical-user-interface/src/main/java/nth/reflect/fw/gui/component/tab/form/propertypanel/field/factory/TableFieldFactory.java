package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import java.util.Optional;

import nth.reflect.fw.generic.util.PrimitiveType;
import nth.reflect.fw.gui.component.table.info.TableInfoForFormTabProperty;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public abstract class TableFieldFactory implements PropertyFieldFactory {

	/**
	 * TODO @return true if {@link TableInfoForFormTabProperty} can be created (if not:
	 * property type is not supported as table)
	 */
	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		TypeInfo typeInfo = info.getPropertyInfo().getTypeInfo();
		if (!typeInfo.isArrayOrCollection()) {
			return false;
		} else {
			Optional<TypeInfo> arrayOrCollectionTypeInfo = typeInfo.getGenericTypeInfo();
			Class<?> type = arrayOrCollectionTypeInfo.get().getType();
			return String.class.isAssignableFrom(type) || PrimitiveType.isPrimitive(type)
					|| PrimitiveType.isWrapper(type);
		}
	}

}
