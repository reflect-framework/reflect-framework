package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanel;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.factory.Contractor;

/**
 * <p>
 * Implementations of the {@link PropertyFieldFactory} interface are used by the
 * the {@link PropertyPanelFactory} to create a {@link PropertyField} for a
 * {@link PropertyPanel} for a {@link FormTab}.
 * </p>
 * 
 * <p>
 * Implementations of the {@link PropertyFieldFactory} interface therefore need
 * to be registered to the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldFactories()} method.
 * This allows you to extend and add your own {@link PropertyField} and
 * {@link PropertyFieldFactory} implementations.
 * </p>
 * 
 * @author nilsth
 *
 */
public interface PropertyFieldFactory extends Contractor<PropertyField, PropertyFieldFactoryInfo> {

}
