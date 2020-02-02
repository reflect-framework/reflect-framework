package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

public abstract class CheckBoxFieldFactory implements PropertyFieldFactory {

	@Override
	public boolean canCreate(PropertyFieldFactoryInfo info) {
		Class<?> propertyType = info.getPropertyInfo().getTypeInfo().getType();
		boolean propertyIsBooleanType =propertyType== boolean.class || propertyType==Boolean.class;
		return propertyIsBooleanType;
	}

}
