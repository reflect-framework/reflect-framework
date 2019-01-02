package nth.reflect.fw.ui.component.tab.form.propertypanel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;

import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.valuemodel.PropertyValueModel;

/**
 * A {@link FormTab} has one or more {@link PropertyPanel}s. Each
 * {@link PropertyPanel} represents a {@link DomainObjectProperty} and contains:
 * <ul>
 * <li>a {@link PropertyLabel}</li>
 * <li>a {@link PropertyField}</li>
 * <li>a {@link PropertyActionMethodMenu}</li>
 * <li>{@link PropertyValidationLabel} if any</li>
 * </ul>
 * 
 * <p>
 * The PropertyPanelFactory will create a PropertyPanel, by passing all
 * necessary information as {@link Constructor} {@link Parameter}s.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface PropertyPanel<LABEL extends PropertyLabel, FIELD extends PropertyField, VALIDATION_MSG extends PropertyValidationLabel> {

	default void updateFromPropertyValueModel() {

		PropertyValueModel propertyValueModel = getPropertyValueModel();
		Object domainObject = propertyValueModel.getDomainObject();
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Boolean visible = propertyInfo.isVisibleInForm(domainObject);

		setVisible(visible);

		if (visible) {
			// TODO properttPanel read-only: should have a different background
			// color and field should be disabled when it is read only (disabled
			// or when fromMode=readonly)
			// TODO properttPanel should have a different background color when
			// it is read only (disabled or when fromMode=readonly)

			String description = propertyInfo.getDescription();
			setDescription(description);

			PropertyLabel propertyLabel = getPropertyLabel();
			String displayName = propertyInfo.getDisplayName();
			propertyLabel.setText(displayName);
			propertyLabel.setDescription(description);

			boolean enabled = propertyInfo.isEnabled(domainObject);
			PropertyField propertyField = getPropertyField();
			propertyField.setEnabled(enabled);

			Object propertyValue = propertyInfo.getValue(domainObject);
			propertyField.setValueFromDomainProperty(propertyValue);

		}
	}

	public LABEL getPropertyLabel();

	public FIELD getPropertyField();

	public VALIDATION_MSG getPropertyValidationLabel();

	public PropertyValueModel getPropertyValueModel();

	public void setVisible(Boolean visible);

	public void setDescription(String description);

}
