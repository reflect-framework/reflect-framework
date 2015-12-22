package nth.introspect.layer5provider.reflection;

import nth.introspect.container.ConstructionInjection;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.Provider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

/**
 * The {@link ReflectionProvider} provides information on objects (
 * {@link ClassInfo}), properties ({@link PropertyInfo}) of {@link ActionMethod}
 * ({@link ActionMethodInfo}) using <a
 * href="https://en.wikipedia.org/wiki/Reflection_(computer_programming)"
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

	ClassInfo getClassInfo(Class<?> objectClass);

}
