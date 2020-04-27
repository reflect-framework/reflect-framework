package nth.reflect.fw.ui.swing;

import java.lang.reflect.Constructor;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.gui.GraphicalUserInterfaceApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandlerFactory;
import nth.reflect.fw.layer5provider.authorization.AuthorizationProvider;
import nth.reflect.fw.layer5provider.authorization.DefaultAuthorizationProvider;
import nth.reflect.fw.layer5provider.language.DefaultLanguageProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.notification.DefaultNotificationProvider;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.DefaultReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.stringconverter.DefaultStringConverters;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.layer5provider.url.application.ApplicationUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.classresource.ClassResourceUrlStreamHandler;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrlStreamHandler;
import nth.reflect.fw.layer5provider.validation.DefaultValidationProvider;
import nth.reflect.fw.layer5provider.validation.ValidationProvider;
import nth.reflect.fw.layer5provider.version.DefaultVersionProvider;
import nth.reflect.fw.layer5provider.version.VersionProvider;
import nth.reflect.fw.ui.swing.layer5provider.actionmethodexecution.ActionMethodResultProviderFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.CheckBoxFieldFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.ComboBoxFieldFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.DateTimeFieldFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.ManyToOneOrManyFieldFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.OneToOneOrManyFieldFactory;
import nth.reflect.fw.ui.swing.tab.form.proppanel.field.TextFieldFactory;

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
	public UrlProvider getUrlProvider() {
		return new UrlProvider(new ClassResourceUrlStreamHandler(), new ApplicationUrlStreamHandler(this),
				new FontIconUrlStreamHandler());
	}

	@Override
	public StringConverterProvider getStringConverterProvider() {
		return new StringConverterProvider(DefaultStringConverters.getAll());
	}

	@Override
	public PropertyFieldProvider getPropertyFieldProvider() {
		return new PropertyFieldProvider(new PropertyFieldFactory[] { new TextFieldFactory(),
				new CheckBoxFieldFactory(), new DateTimeFieldFactory(), new ComboBoxFieldFactory(),
				new ManyToOneOrManyFieldFactory(), new OneToOneOrManyFieldFactory() });
	}

	@Override
	public ActionMethodExecutionProvider getActionMethodExecutionProvider() {
		ActionMethodResultHandlerFactory actionMethodResultHandlerFactory = new ActionMethodResultProviderFactory(this);
		return new ActionMethodExecutionProvider(actionMethodResultHandlerFactory);
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
