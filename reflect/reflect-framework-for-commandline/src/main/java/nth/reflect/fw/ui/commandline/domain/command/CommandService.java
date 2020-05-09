package nth.reflect.fw.ui.commandline.domain.command;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer2service.ServiceContainer;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ServiceClassInfo;

public class CommandService {

	public static Command findCommand(List<Command> commands, String[] arguments) {
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

	public static List<Command> getCommands(UserInterfaceContainer userInterfaceContainer) {
		ReflectionProvider reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ServiceContainer serviceContainer = userInterfaceContainer.get(ServiceContainer.class);
		List<Object> serviceObjects = serviceContainer.getServiceObjects();

		if (serviceObjects.size() == 0) {
			throw new ReflectApplicationHasNoServiceObjectsException();
		}

		boolean shortCommandName = serviceObjects.size() == 1;

		List<Command> commands = new ArrayList<Command>();

		for (Object serviceObject : serviceObjects) {
			Class<? extends Object> serviceClass = serviceObject.getClass();
			ServiceClassInfo serviceClassInfo = reflectionProvider.getServiceClassInfo(serviceClass);
			List<ActionMethodInfo> actionMethodInfos = serviceClassInfo.getActionMethodInfosSorted();

			for (ActionMethodInfo actionMethodInfo : actionMethodInfos) {
				Command command = new Command(reflectionProvider, serviceObject, actionMethodInfo, shortCommandName);
				commands.add(command);
			}
		}

		if (commands.size() == 0) {
			throw new ServiceObjectsHaveNoActionMethodsException();
		}

		return commands;
	}

}
