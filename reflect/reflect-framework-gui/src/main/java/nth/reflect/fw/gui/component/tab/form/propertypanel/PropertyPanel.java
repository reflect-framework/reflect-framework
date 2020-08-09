package nth.reflect.fw.gui.component.tab.form.propertypanel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.List;

import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * A {@link FormTab} has one or more {@link PropertyPanel}s. Each
 * {@link PropertyPanel} represents a {@link DomainObjectProperty} and contains:
 * <ul>
 * <li>A {@link PropertyLabel}</li>
 * <li>A {@link PropertyField}</li>
 * <li>Zero or more {@link PropertyIconButton}s</li>
 * <li>Zero or one {@link PropertyValidationLabel}</li>
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

	default void onRefresh() {

		PropertyValueModel propertyValueModel = getPropertyValueModel();
		Object domainObject = propertyValueModel.getDomainObject();
		PropertyInfo propertyInfo = propertyValueModel.getPropertyInfo();
		Boolean visible = propertyInfo.isVisibleInForm(domainObject);
		Boolean enabled = propertyInfo.isEnabled(domainObject) && propertyValueModel.canSetValue();
		// TODO include FormMode??

		setVisible(visible);

		if (visible) {
			setEnabled(enabled);

			String description = propertyInfo.getDescription().getTranslation();
			setDescription(description);

			PropertyLabel propertyLabel = getPropertyLabel();
			String displayName = propertyInfo.getDisplayName().getTranslation();
			propertyLabel.setText(displayName);
			propertyLabel.setDescription(description);

			PropertyField propertyField = getPropertyField();
			propertyField.setEnabled(enabled);

			Object propertyValue = propertyInfo.getValue(domainObject);
			propertyField.setValueFromDomainProperty(propertyValue);

			List<PropertyIconButton> propertyIconButtons = getPropertyIconButtons();
			for (PropertyIconButton propertyIconButton : propertyIconButtons) {
				propertyIconButton.onRefresh();
			}

			PropertyValidationLabel validationLabel = getPropertyValidationLabel();
			validationLabel.clearMessage();
			// TODO validate at a later time
		}
	}

	/**
	 * The implementation will need to set the background depending if the property
	 * can be edited:
	 * 
	 * @param enabled = true: background must be
	 *                {@link ReflectColorName#CONTENT#BACKGROUND_20()}<br>
	 *                = false: background must be
	 *                {@link ReflectColorName#CONTENT#BACKGROUND()}
	 */
	public void setEnabled(Boolean enabled);

	public LABEL getPropertyLabel();

	public FIELD getPropertyField();

	public VALIDATION_MSG getPropertyValidationLabel();

	public PropertyValueModel getPropertyValueModel();

	public void setVisible(Boolean visible);

	public void setDescription(String description);

	public List<PropertyIconButton> getPropertyIconButtons();

}
