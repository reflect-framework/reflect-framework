package nth.reflect.fw.swing;

import java.lang.reflect.Constructor;
import java.util.List;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.layer5provider.properyfield.PropertyFieldProvider;
import nth.reflect.fw.gui.layer5provider.properyfield.factory.PropertyFieldFactory;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerProvider;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandlerProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterFactories;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.UrlStreamHandlers;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.version.DefaultVersionProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;
import nth.reflect.fw.swing.layer5provider.actionmethod.execution.ActionMethodExecutionProvider;
import nth.reflect.fw.swing.layer5provider.actionmethod.prehandler.ActionMethodPreHandlerClasses;
import nth.reflect.fw.swing.layer5provider.actionmethod.result.ActionMethodResultHandlerClasses;
import nth.reflect.fw.swing.layer5provider.properyfield.PropertyFieldFactoryClasses;

/**
 * {@link ReflectApplicationForSwing} is an implementation of the
 * {@link ReflectFramework} for desktop computers such as ...
 * 
 * <h3>How to download a ReflectForSwing demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new ReflectForSwing project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */

//TODO see https://github.com/atarw/material-ui-swing
public abstract class ReflectApplicationForSwing implements GraphicalUserInterfaceApplication {

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForSwing.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends VersionProvider> getVersionProviderClass() {
		return DefaultVersionProvider.class;
	}

	@Override
	public Class<? extends LanguageProvider> getLanguageProviderClass() {
		return DefaultLanguageProvider.class;
	}

	@Override
	public Class<? extends AuthorizationProvider> getAuthorizationProviderClass() {
		return DefaultAuthorizationProvider.class;
	}

	@Override
	public Class<? extends ValidationProvider> getValidationProviderClass() {
		return DefaultValidationProvider.class;
	}

	@Override
	public Class<? extends NotificationProvider> getNotificationProviderClass() {
		return DefaultNotificationProvider.class;
	}

	@Override
	public Class<? extends UrlProvider> getUrlProviderClass() {
		return UrlProvider.class;
	}

	@Override
	public List<Class<? extends ReflectUrlStreamHandler>> getUrlStreamHandlerClasses() {
		return new UrlStreamHandlers();
	}

	@Override
	public Class<? extends StringConverterProvider> getStringConverterProviderClass() {
		return StringConverterProvider.class;
	}

	@Override
	public List<Class<? extends StringConverterFactory>> getStringConverterFactoryClasses() {
		return new StringConverterFactories();
	}

	@Override
	public Class<? extends PropertyFieldProvider> getPropertyFieldProviderClass() {
		return PropertyFieldProvider.class;
	}

	@Override
	public List<Class<? extends PropertyFieldFactory>> getPropertyFieldFactoryClasses() {
		return new PropertyFieldFactoryClasses();
	}

	@Override
	public Class<? extends ActionMethodExecutionProvider> getActionMethodExecutionProviderClass() {
		return ActionMethodExecutionProvider.class;
	}

	@Override
	public List<Class<? extends ActionMethodPreHandler>> getActionMethodPreHandlerClasses() {
		return new ActionMethodPreHandlerClasses();
	}

	@Override
	public Class<? extends ActionMethodPreHandlerProvider> getActionMethodPreHandlerProviderClass() {
		return ActionMethodPreHandlerProvider.class;
	}

	@Override
	public Class<? extends ActionMethodResultHandlerProvider> getActionMethodResultHandlerProviderClass() {
		return ActionMethodResultHandlerProvider.class;
	}

	@Override
	public List<Class<? extends ActionMethodResultHandler>> getActionMethodResultHandlerClasses() {
		return new ActionMethodResultHandlerClasses();
	}

	/**
	 * Launch a standalone application. This method is typically called from the
	 * main method(). It must not be called more than once or an exception will be
	 * thrown. This is equivalent to launch(TheClass.class, args) where TheClass is
	 * the immediately enclosing class of the method that called launch. It must be
	 * a subclass of Application or a RuntimeException will be thrown.
	 *
	 * Typical usage is:
	 * <ul>
	 * 
	 * <pre>
	 * public static void main(String[] args) {
	 * 	launch(args);
	 * }
	 * </pre>
	 * </ul>
	 *
	 * @param args the command line arguments passed to the application. An
	 *             application may get these parameters using the
	 *             {@link #getParameters()} method.
	 *
	 * @throws IllegalStateException if this method is called more than once.
	 */
	public static void launch() {
		// Figure out the right class to call
		StackTraceElement[] cause = Thread.currentThread().getStackTrace();

		boolean foundThisMethod = false;
		String callingClassName = null;
		for (StackTraceElement se : cause) {
			// Skip entries until we get to the entry for this class
			String className = se.getClassName();
			String methodName = se.getMethodName();
			if (foundThisMethod) {
				callingClassName = className;
				break;
			} else if (ReflectApplicationForSwing.class.getName().equals(className) && "launch".equals(methodName)) {

				foundThisMethod = true;
			}
		}

		if (callingClassName == null) {
			throw new NoApplicationClassException();
		}

		try {
			Class applicationClass = Class
					.forName(callingClassName, true, Thread.currentThread().getContextClassLoader());
			if (ReflectApplicationForSwing.class.isAssignableFrom(applicationClass)) {
				Class<? extends ReflectApplicationForSwing> appClass = applicationClass;
				Constructor<? extends ReflectApplicationForSwing> constructor = (Constructor<? extends ReflectApplicationForSwing>) appClass
						.getConstructors()[0];
				ReflectApplicationForSwing app = constructor.newInstance();
				ReflectFramework.launch(app);
			} else {
				throw new NoApplicationSubClassException(applicationClass);
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

}
