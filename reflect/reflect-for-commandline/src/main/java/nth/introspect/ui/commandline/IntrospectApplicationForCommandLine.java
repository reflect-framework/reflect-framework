package nth.introspect.ui.commandline;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.introspect.IntrospectApplication;
import nth.introspect.IntrospectFramework;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.about.DefaultAboutProvider;
import nth.introspect.layer5provider.authorization.AuthorizationProvider;
import nth.introspect.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.layer5provider.language.DefaultLanguageProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.notification.DefaultNotificationProvider;
import nth.introspect.layer5provider.notification.NotificationProvider;
import nth.introspect.layer5provider.path.DefaultPathProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.DefaultReflectionProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.introspect.layer5provider.validation.DefaultValidationProvider;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * TODO
 * 
 * <h3>How to download a IntrospectForCommandLine demo project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * <h3>How to create a new IntrospectCommandLine project</h3>
 * <p>
 * TODO
 * </p>
 * 
 * @author nilsth
 *
 */
public abstract class IntrospectApplicationForCommandLine implements IntrospectApplication {

	private  String[] commandLineArguments;

	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserInterfaceControllerForCommandLine.class;
	}

	@Override
	public Class<? extends ReflectionProvider> getReflectionProviderClass() {
		return DefaultReflectionProvider.class;
	}

	@Override
	public Class<? extends AboutProvider> getAboutProviderClass() {
		return DefaultAboutProvider.class;
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
	public List<Class<? extends UrlProvider>> getUrlProviderClasses() {
		return Arrays.asList(ClassResourceUrlProvider.class);
	}
	
	public String[] getCommandLineArguments() {
		return commandLineArguments;
	}

	/**
	 * Launch a standalone application. This method is typically called from the
	 * main method(). It must not be called more than once or an exception will
	 * be thrown. This is equivalent to launch(TheClass.class, args) where
	 * TheClass is the immediately enclosing class of the method that called
	 * launch. It must be a subclass of Application or a RuntimeException will
	 * be thrown.
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
	 * @param args
	 *            the command line arguments passed to the application. An
	 *            application may get these parameters using the
	 *            {@link #getParameters()} method.
	 *
	 * @throws IllegalStateException
	 *             if this method is called more than once.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void launch(String... args) {
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
			} else if (IntrospectApplicationForCommandLine.class.getName().equals(className)
					&& "launch".equals(methodName)) {

				foundThisMethod = true;
			}
		}

		if (callingClassName == null) {
			throw new RuntimeException("Error: unable to determine Application class");
		}

		try {
			Class theClass = Class.forName(callingClassName, true,
					Thread.currentThread().getContextClassLoader());
			if (IntrospectApplicationForCommandLine.class.isAssignableFrom(theClass)) {
				Class<? extends IntrospectApplicationForCommandLine> appClass = theClass;
				Constructor<? extends IntrospectApplicationForCommandLine> constructor = (Constructor<? extends IntrospectApplicationForCommandLine>) appClass
						.getConstructors()[0];
				IntrospectApplicationForCommandLine app = constructor.newInstance();
				app.setCommandlineArguments(args);
				IntrospectFramework.launch(app);
			} else {
				throw new RuntimeException("Error: " + theClass + " is not a subclass of "
						+ IntrospectApplicationForCommandLine.class.getCanonicalName());
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	private void setCommandlineArguments(String[] commandLineArguments) {
		this.commandLineArguments=commandLineArguments;
	}

}
