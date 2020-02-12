package nth.reflect.fw.gui;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.PropertyField;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.gui.style.ReflectColors;

public interface GraphicalUserInterfaceApplication extends ReflectApplication {

	public ReflectColors getColors() ;
	
	public PropertyFieldProvider getPropertyFieldService();
}
