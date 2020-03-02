package nth.reflect.fw.gui.component.tab.form.propertypanel;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactoryInfo;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.layer5provider.language.translatable.Translatable;

public class PropertyFieldNotFoundException extends ReflectTranslatableException {

	private static final long serialVersionUID = 4720848439949333460L;
	@Translatable
	private static final String MESSAGE = "%s: Could not find a %s for domain object property: %s of type %s";

	public PropertyFieldNotFoundException(PropertyFieldFactoryInfo info) {
		super(MESSAGE, PropertyFieldProvider.class.getSimpleName(), PropertyFieldFactory.class.getSimpleName(),
				info.getPropertyInfo(), info.getPropertyInfo().getTypeInfo().getType().getCanonicalName());
	}

}
