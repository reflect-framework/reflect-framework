package nth.reflect.fw.gui;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.gui.style.ColorProvider;

public interface GraphicalUserInterfaceApplication extends ReflectApplication {

	public ColorProvider getColorProvider() ;
	
	public PropertyFieldProvider getPropertyFieldProvider();
}
