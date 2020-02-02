package nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory;

import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.factory.DelegatingContractor;

/**
 * A Factory that returns one of the
 * {@link GraphicalUserInterfaceApplication#getPropertyFieldFactories()} that
 * can do the job. 
 * 
 * @author nilsth
 *
 */
public class DelegatingPropertyFieldFactory extends DelegatingContractor<PropertyField, PropertyFieldFactoryInfo> {

	public DelegatingPropertyFieldFactory(GraphicalUserInterfaceApplication application) {
		super(application.getPropertyFieldFactories());
	}
	
}
