package nth.reflect.fw.ui.tab.form.propertypanel;

import java.util.List;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.generic.translatablestring.ReflectTranslatable;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.tab.form.FormTab;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

public abstract class  PropertyPanelFactory<PROPERTY_PANEL> {

	@ReflectTranslatable
	private static final String COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S = "%s could not find a %s for domain object property: %s";
	private final  List<PropertyFieldFactory> fieldFactories;

	public abstract List<PropertyFieldFactory> createFieldFactories();
	
	public PropertyPanelFactory() {
		fieldFactories=createFieldFactories();
	}

    public abstract PROPERTY_PANEL createPropertyPanel(FormTab formTab, PropertyValueModel propertyValueModel) ;
	
	public PropertyField createPropertyField(FormTab formTab, PropertyValueModel propertyValueModel) {
		for ( PropertyFieldFactory fieldFactory : fieldFactories) {
			if (fieldFactory.canCreateFor(propertyValueModel)) {
				PropertyField field = fieldFactory.create(formTab, propertyValueModel);
				return field;
			}
		}
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		throw new ReflectTranslatableException(userInterfaceContainer, COULD_NOT_FIND_A_S_FOR_DOMAIN_OBJECT_PROPERTY_S,
				this.getClass().getCanonicalName(), PropertyFieldFactory.class.getName(), propertyValueModel.getPropertyInfo().getCanonicalName());
	}

}
