package nth.reflect.fw.ui.swing;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.about.DefaultAboutProvider;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrlProvider;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlProvider;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrlProvider;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;

/**
 * {@link ReflecttApplicationForSwing} is an implementation of the {@link ReflectFramework} for desktop computers such as ...  
 * 
 *  <h3>How to download a ReflectForSwing demo project</h3>
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
public abstract class ReflecttApplicationForSwing implements
ReflectApplication {

	
	@Override
	public Class<? extends UserInterfaceController> getUserInterfaceControllerClass() {
		return UserinterfaceControllerForSwing.class;
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
		return Arrays.asList(ClassResourceUrlProvider.class, ApplicationUrlProvider.class, FontIconUrlProvider.class);
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
			} else if (ReflecttApplicationForSwing.class.getName().equals(className)
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
			if (ReflecttApplicationForSwing.class.isAssignableFrom(theClass)) {
				Class<? extends ReflecttApplicationForSwing> appClass = theClass;
				Constructor<? extends ReflecttApplicationForSwing> constructor = (Constructor<? extends ReflecttApplicationForSwing>) appClass.getConstructors()[0];
				ReflecttApplicationForSwing app = constructor.newInstance();
				ReflectFramework.launch(app);
			} else {
				throw new RuntimeException("Error: " + theClass + " is not a subclass of "
						+ ReflecttApplicationForSwing.class.getCanonicalName());
			}
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	
}
