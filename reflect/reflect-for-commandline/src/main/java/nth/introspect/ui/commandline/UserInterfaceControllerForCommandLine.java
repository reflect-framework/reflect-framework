package nth.introspect.ui.commandline;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;

import nth.introspect.generic.util.ExceptionUtil;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.notification.Task;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.commandline.domain.command.Command;
import nth.introspect.ui.commandline.domain.command.CommandService;
import nth.introspect.ui.commandline.domain.command.IntrospectCommandLineException;
import nth.introspect.ui.commandline.domain.command.Parameter;
import nth.introspect.ui.commandline.view.FormView;
import nth.introspect.ui.commandline.view.HelpView;
import nth.introspect.ui.commandline.view.TableView;
import nth.introspect.ui.view.FormMode;

public class UserInterfaceControllerForCommandLine extends UserInterfaceController {

	private final ProviderContainer providerContainer;

	public UserInterfaceControllerForCommandLine(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		this.providerContainer = userInterfaceContainer.get(ProviderContainer.class);
	}

	@Override
	public void launch() {
		try {
			IntrospectApplicationForCommandLine commandLineApplication = providerContainer
					.get(IntrospectApplicationForCommandLine.class);
			String[] arguments = commandLineApplication.getCommandLineArguments();

			List<Command> commands = CommandService.getCommands(userInterfaceContainer);

			if (isCommandFile(arguments)) {
				List<String[]> argumentsInFile = getArgumentsFromCommandFile(arguments);
				for (String[] argumentsPerLine : argumentsInFile) {
					executeCommand(argumentsPerLine, commands);
				}
			} else {
				executeCommand(arguments, commands);
			}
			// System.exit(0);
		} catch (Exception e) {
			showErrorDialog("Error", "Error executing a command", e);
		}

	}

	private List<String[]> getArgumentsFromCommandFile(String[] arguments) throws IOException {
		File commandFile = getCommandFile(arguments);
		FileReader commandFileReader = new FileReader(commandFile);
		BufferedReader br = new BufferedReader(commandFileReader);
		List<String[]> allArguments = new ArrayList<String[]>();

		String line = br.readLine();
		while (line != null) {
			String[] argumentsPerLine = parseArguments(line);
			allArguments.add(argumentsPerLine);
			line = br.readLine();
		}
		br.close();
		return allArguments;
	}

	private String[] parseArguments(String commandLine) {
		boolean betweenBrackets = false;
		commandLine = commandLine.trim() + " ";
		StringBuilder argument = new StringBuilder();
		ArrayList<String> arguments = new ArrayList<String>();
		for (char ch : commandLine.toCharArray()) {
			if (ch == '"' || ch == '\'') {
				betweenBrackets = !betweenBrackets;
			} else if (Character.isWhitespace(ch) && !betweenBrackets) {
				if (argument.length() > 0) {
					arguments.add(argument.toString());
					argument = new StringBuilder();
				}
			} else {
				argument.append(ch);
			}
		}
		return arguments.toArray(new String[arguments.size()]);
	}

	private void executeCommand(String[] arguments, List<Command> commands)
			throws IntrospectCommandLineException {
		Command command = CommandService.findCommand(commands, arguments);
		if (command == null) {
			HelpView helpView = new HelpView("Unknown command", commands);
			System.out.println(helpView.toString());
			System.exit(1);
		}

		List<Parameter> parameters = command.getParameters();
		boolean invalidNumberOfArguments = arguments.length != 1 + parameters.size();
		if (invalidNumberOfArguments) {
			HelpView helpView = new HelpView("Invalid number of arguments!", commands);
			System.out.println(helpView.toString());
		}

		Object methodParameterValue = command.createMethodParameter();

		for (Parameter parameter : parameters) {
			int parameterIndex = parameters.indexOf(parameter);
			String argument = arguments[parameterIndex + 1];
			parameter.parseArgument(methodParameterValue, argument);
		}

		Object serviceObject = command.getServiceObject();
		ActionMethodInfo actionMethodInfo = command.getMethodInfo();

		processActionMethod(serviceObject, actionMethodInfo, methodParameterValue);
	}

	/**
	 * Overriding this method for the
	 * {@link UserInterfaceControllerForCommandLine} because a command line
	 * application only supports
	 * {@link ExecutionModeType.EXECUTE_METHOD_DIRECTLY} only so we can call
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 */
	@Override
	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		try {
			processActionMethodExecution(methodOwner, methodInfo, methodParameter);
		} catch (Exception exception) {
			String title = languageProvider.getText("Error while executing an action");
			String messageFormat = languageProvider.getText("Action: %s");
			String actionMethod = TitleUtil.createTitle(reflectionProvider, methodInfo,
					methodParameter, false);
			String message = String.format(messageFormat, actionMethod);
			showErrorDialog(title, message, exception);
		}

	}

	private boolean isCommandFile(String[] arguments) {
		if (arguments.length == 1) {
			File commandFile = getCommandFile(arguments);
			return commandFile.exists();
		} else {
			return false;
		}
	}

	private File getCommandFile(String[] arguments) {
		return new File(arguments[0]);
	}

	public void downloadFile(DownloadStream downloadStream) {
	}

	@Override
	public void onTaskChange(Task task) {
	}

	@Override
	public void onRefresh() {
	}

	@Override
	public void onNewMessage(String title, String message) {
	}

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {
		StringBuilder txt = new StringBuilder();
		txt.append(title);
		txt.append("\n");
		txt.append(message);
		txt.append("\n");
		txt.append("Cause: ");
		txt.append(ExceptionUtil.getRootCause(throwable).getMessage());
		txt.append("\n");
		txt.append("Details: ");
		txt.append("\n");
		txt.append(ExceptionUtil.getRootCauseStackTrace(throwable));
		System.out.println(txt.toString());
	}

	@Override
	public void processActionMethodExecution(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		// TODO check if the method is enabled before the method is executed
		// (otherwise throw exception)
		// TODO validate the method parameter value before the method is
		// executed (if invalid: throw exception)

		Object methodReturnValue;
		try {
			methodReturnValue = methodInfo.invoke(methodOwner, methodParameter);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
		// show method result
		processActionMethodResult(methodOwner, methodInfo, methodParameter, methodReturnValue);
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter) {
		System.out.println("Succesfully executed");
	}

	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		FormView formView = new FormView(reflectionProvider, methodInfo, methodResult);
		System.out.println(formView.toString());
	}

	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, List<?> methodResult) {
		TableView tableView = new TableView(providerContainer.get(ReflectionProvider.class),
				methodInfo, (Collection<?>) methodResult);
		System.out.println(tableView.toString());
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			showErrorDialog("Error", "Error browsing URI: " + uri.toString(), exception);
		}
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return
	 * type {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, DownloadStream methodResult) {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(methodResult.getFile());
		InputStream inputStream = methodResult.getInputStream();
		int returnVal = chooser.showSaveDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			// safe file
			try {
				OutputStream out = new FileOutputStream(file);
				byte buf[] = new byte[1024];
				int len;
				while ((len = inputStream.read(buf)) > 0)
					out.write(buf, 0, len);
				out.close();
				inputStream.close();

			} catch (Exception e) {
				showErrorDialog("Error saving file", "Failed to save file.", e);
			}
			// open file
			try {
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				showErrorDialog("Error opening file", "Failed to open file.", e);
			}
		}
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, String methodResult) {
		System.out.println(methodResult);

	}

	public void editActionMethodParameter(Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object actionMethodParameterValue) {
		// Do nothing: The user interface controller will create the
		// domainObject from the command line arguments
	}
}
