package nth.reflect.fw.ui.component.tab.form.propertypanel;

import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.component.tab.form.valuemodel.PropertyValueModel;

/**
 * <p>
 * Implementations of the {@link PropertyFieldFactory} interface are used by the the
 * {@link PropertyPanelFactory} to create a {@link PropertyField} for a
 * {@link PropertyPanel} for a {@link FormTab}.
 * </p>
 * 
 * <p>
 * Implementations of the {@link PropertyFieldFactory} interface therefore need to be
 * registered to the {@link PropertyPanelFactory}. This way you can add your own
 * {@link PropertyField} and {@link PropertyFieldFactory} implementations
 * </p>
* @author nilsth
 *
 */
public interface PropertyFieldFactory {

	public PropertyField create(FormTab formTab, PropertyValueModel propertyValueModel);

	public boolean canCreateFor(PropertyValueModel propertyValueModel);

}
