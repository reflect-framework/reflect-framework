package nth.introspect.ui.commandline;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.initializator.IntrospectInitializer;
import nth.introspect.provider.authorization.AuthorizationProvider;
import nth.introspect.provider.authorization.DefaultAuthorizationProvider;
import nth.introspect.provider.domain.DefaultDomainProvider;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.info.DefaultInfoProvider;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.language.DefaultLanguageProvider;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.path.DefaultPathProvider;
import nth.introspect.provider.path.PathProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.validation.ValidationProvider;

public class CommandLineIntrospectInitializer implements IntrospectInitializer {

	/**
	 * <b>This class is used to initialize an Introspect CommandLine Application.<br>
	 * Introspect is a light weight, easy to extend application framework<br>
	 * <br>
	 * Steps to create a Introspect CommandLine Application:<br>
	 * </b> - Create a java Application with help of Eclipse<br>
	 * - Include the required Introspect jars or projects into the java build path<br>
	 * - Initialize the application in the main method of your main class (see below)<br>
	 * - Develop and debug your application<br>
	 * - Build as a runnable jar (Eclipse > File > Export > Runnable jar)<br>
	 * <br>
	 * <b>Initialize the application in the main method of your main class:</b><br>
	 * public static void main(String[] arguments) {<br>
	 * &nbsp;&nbsp;{@literal List<Class<?>>} serviceClasses = new Array{@literal List<Class<?>>}();<br>
	 * &nbsp;&nbsp;serviceClasses.add(...); <br>
	 * &nbsp;&nbsp;{@literal //}initialize introspect framework<br>
	 * &nbsp;&nbsp;CommandLineIntrospectInitializer initializer=new CommandLineIntrospectInitializer(serviceClasses, arguments);<br>
	 * &nbsp;&nbsp;Introspect.init(initializer);<br>
	 * }
	 * 
	 * @author Nils ten Hoeve
	 */

	private String[] arguments;
	private final Object application;
	private final List<Class<?>> serviceClasses;

	public CommandLineIntrospectInitializer(Object application, String[] arguments) {
		this.application = application;
		this.serviceClasses = new ArrayList<Class<?>>();
		this.arguments = arguments;
	}

	@Override
	public DomainProvider createDomainProvider() {
		return new DefaultDomainProvider(serviceClasses);
	}

	@Override
	public AuthorizationProvider createAuthorizationProvider() {
		return new DefaultAuthorizationProvider();
	}

	@Override
	public ValidationProvider createValidationProvider() {
		return null;
	}

	@Override
	public LanguageProvider createLanguageProvider() {
		return new DefaultLanguageProvider();
	}

	@Override
	public InfoProvider createInfoProvider() {
		return new DefaultInfoProvider(application);
	}

	@Override
	public PathProvider createPathProvider() {
		try {
			return new DefaultPathProvider();
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public UserInterfaceProvider<?> createUserInterfaceProvider() {
		return new CommandLineUserInterfaceProvider(arguments);
	}

	@Override
	public void addServiceClass(Class<?> serviceObject) {
		serviceClasses.add(serviceObject);
	}

}
