package nth.introspect.ui.commandline.domain.command;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.Introspect;
import nth.introspect.container.DependencyInjectionContainer;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer2service.ServiceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class CommandService {

	private static Map<Class<?>, String> types;

	public static Command findCommand(List<Command> commands, String[] arguments)
			throws IntrospectCommandLineException {
		if (arguments.length < 1) {
			return null;
		}
		String argument = arguments[0].trim().toLowerCase();
		for (Command command : commands) {
			String commandName = command.getName().trim().toLowerCase();
			if (argument.equals(commandName)) {
				return command;
			}
		}
		return null;
	}

	public static List<Command> getCommands(UserInterfaceContainer userInterfaceContainer)
			throws IntrospectCommandLineException {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();

		if (serviceObjects.size() == 0) {
			throw new IntrospectCommandLineException("No service objects.");
		}

		boolean shortCommandName = serviceObjects.size() == 1;

		List<Command> commands = new ArrayList<Command>();

		for (Object serviceObject : serviceObjects) {
			Class<? extends Object> serviceClass = serviceObject.getClass();

			List<ActionMethodInfo> actionMethodInfos = reflectionProvider
					.getMethodInfos(serviceClass);

			for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
				Command command = new Command(reflectionProvider, serviceObject, actionMethodInfo,
						shortCommandName);
				commands.add(command);
			}
		}

		if (commands.size() == 0) {
			throw new IntrospectCommandLineException(
					"No service objects with public visible methods.");
		}

		return commands;
	}

	public static Map<Class<?>, String> getSupportedParameterPropertyTypes() {
		if (types == null) {
			types = new HashMap<Class<?>, String>();
			types.put(Boolean.class, Boolean.FALSE + ".." + Boolean.TRUE);
			types.put(Byte.class, Byte.MIN_VALUE + ".." + Byte.MAX_VALUE);
			types.put(Short.class, Short.MIN_VALUE + ".." + Short.MAX_VALUE);
			types.put(Integer.class, Integer.MIN_VALUE + ".."
					+ Integer.MAX_VALUE);
			types.put(Long.class, Long.MIN_VALUE + ".." + Long.MAX_VALUE);
			types.put(Float.class, Float.MIN_VALUE + ".." + Float.MAX_VALUE);
			types.put(Double.class, Double.MIN_VALUE + ".." + Double.MAX_VALUE);
			types.put(Character.class, Character.class.getName());
			types.put(String.class, "Text");
			types.put(File.class, "Path");
			types.put(URI.class, "Uri");
			types.put(URL.class, "Url");
			types.put(Date.class, "Date and or Time");
			// TODO enum!
		}
		return types;
	}

}
