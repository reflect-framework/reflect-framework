package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

public abstract class OneToOneOrManyFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		return info.getPropertyInfo().getTypeInfo().isDomainClass();
	}

}
