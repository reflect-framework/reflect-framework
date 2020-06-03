package nth.reflect.fw.gui.component.table.info.dataprovider;

import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class FellowshipPropertyValueModel extends PropertyValueModel {
	private static final FormMode FORM_MODE_EDIT = FormMode.EDIT;

	public FellowshipPropertyValueModel(UserInterfaceContainer userInterfaceContainer, String methodName) {
		super(createDomainValueModel(userInterfaceContainer), createPropertyInfo(userInterfaceContainer));
	}

	private static PropertyInfo createPropertyInfo(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(Person.class);
		PropertyInfo propertyInfo = domainClassInfo.getPropertyInfo(Person.CHILDREN_PROPERTY_NAME);
		return propertyInfo;
	}

	private static BufferedDomainValueModel createDomainValueModel(UserInterfaceContainer userInterfaceContainer) {
		return new BufferedDomainValueModel(userInterfaceContainer, FellowshipService.FRODO, FORM_MODE_EDIT);
	}

}
