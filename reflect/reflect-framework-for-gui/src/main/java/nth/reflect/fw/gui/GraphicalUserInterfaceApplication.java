package nth.reflect.fw.gui;

import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.style.ColorProvider;

public interface GraphicalUserInterfaceApplication extends ReflectApplication {

	public ColorProvider getColorProvider();

	public Class<? extends PropertyFieldProvider> getPropertyFieldProviderClass();

	public List<Class<? extends PropertyFieldFactory>> getPropertyFieldFactoryClasses();

}
