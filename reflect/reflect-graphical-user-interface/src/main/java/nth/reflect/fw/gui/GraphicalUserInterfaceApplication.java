package nth.reflect.fw.gui;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.style.ReflectColors;

public interface GraphicalUserInterfaceApplication extends ReflectApplication {

	public ReflectColors getColors() ;
	
	public PropertyFieldFactory[] getPropertyFieldFactories();

}
