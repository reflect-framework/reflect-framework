package nth.reflect.fw.layer5provider.reflection;

import nth.reflect.fw.container.ConstructionInjection;
import nth.reflect.fw.junit.layer5provider.reflection.info.classinfo.ApplicationClassInfoTest;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * The {@link ReflectionProvider} provides information on objects (
 * {@link ApplicationClassInfoTest}, {@link ServiceClassInfo},
 * {@link DomainClassInfo}), properties ({@link PropertyInfo}) of
 * {@link ActionMethod} ({@link ActionMethodInfo}) using
 * <a href="https://en.wikipedia.org/wiki/Reflection_(computer_programming)"
 * >reflection</a>. The {@link UserInterfaceController} uses this information to
 * know how the user interface should look like and behave.
 * 
 * You can use the {@link ReflectionProvider} in your code when you need meta
 * information (See {@link ConstructionInjection})
 * 
 * TODO Code example
 * 
 * @author nilsth
 *
 */
public interface ReflectionProvider extends Provider {

	ApplicationClassInfo getApplicationClassInfo();

	ServiceClassInfo getServiceClassInfo(Class<?> serviceClass);

	DomainClassInfo getDomainClassInfo(Class<?> domainClass);

}
