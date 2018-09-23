package nth.reflect.fw.ui.view.form.propertypanel;

import nth.reflect.fw.ui.valuemodel.PropertyValueModel;
import nth.reflect.fw.ui.view.FormView;

/**
 * <p>
 * Implementations of the {@link PropertyFieldFactory} interface are used by the the
 * {@link PropertyPanelFactory} to create a {@link PropertyField} for a
 * {@link PropertyPanel} for a {@link FormView}.
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

	public PropertyField create(FormView formView, PropertyValueModel propertyValueModel);

	public boolean canCreateFor(PropertyValueModel propertyValueModel);

}
