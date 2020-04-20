package nth.reflect.fw.ui.commandline;

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
import java.util.List;

import javax.swing.JFileChooser;

import nth.reflect.fw.generic.util.ExceptionUtil;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.notification.Task;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.commandline.domain.command.Command;
import nth.reflect.fw.ui.commandline.domain.command.CommandService;
import nth.reflect.fw.ui.commandline.domain.command.Parameter;
import nth.reflect.fw.ui.commandline.view.HelpView;
import nth.reflect.fw.ui.commandline.view.TableView;

public class UserInterfaceControllerForCommandLine extends UserInterfaceController {

	private final ProviderContainer providerContainer;

	private static final TranslatableString ERROR_DIALOG_EXECUTING_A_COMMAND_MESSAGE = new TranslatableString(
			UserInterfaceController.class.getCanonicalName() + ".error.dialog.executing.command",
			"Error executing a command");

	public UserInterfaceControllerForCommandLine(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		this.providerContainer = userInterfaceContainer.get(ProviderContainer.class);
	}

	@Override
	public void launch() {
		try {
			ReflectApplicationForCommandLine commandLineApplication = providerContainer
					.get(ReflectApplicationForCommandLine.class);
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
			showError(ERROR_DIALOG_TITLE, ERROR_DIALOG_EXECUTING_A_COMMAND_MESSAGE, e);
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

	private void executeCommand(String[] arguments, List<Command> commands) {
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
	 * Overriding this method for the {@link UserInterfaceControllerForCommandLine}
	 * because a command line application only supports
	 * {@link ExecutionModeType.EXECUTE_METHOD_DIRECTLY} only so we can call
	 * {@link #processActionMethodExecution(Object, ActionMethodInfo, Object)}
	 */
	@Override
	public void processActionMethod(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
		try {
			processActionMethodExecution(methodOwner, methodInfo, methodParameter);
		} catch (Exception exception) {
			TranslatableString title = ERROR_DIALOG_TITLE;
			TranslatableString actionMethodTitle = methodInfo.createTitle(methodParameter);
			TranslatableString message = ERROR_SHOW_RESULT.withParameters(actionMethodTitle);
			showError(title, message, exception);
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
	public void showError(TranslatableString title, TranslatableString message, Throwable throwable) {
		StringBuilder txt = new StringBuilder();
		txt.append(title.getTranslation(languageProvider));
		txt.append("\n");
		txt.append(message.getTranslation(languageProvider));
		txt.append("\n");
		txt.append("Cause: ");
		txt.append(ExceptionUtil.getRootCause(throwable, languageProvider).getMessage());
		txt.append("\n");
		txt.append("Details: ");
		txt.append("\n");
		txt.append(ExceptionUtil.getRootCauseStackTrace(throwable, languageProvider));
		System.out.println(txt.toString());
	}

	@Override
	public void processActionMethodExecution(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter) {
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
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			List<?> methodResult) {
		TableView tableView = new TableView(providerContainer.get(ReflectionProvider.class), methodInfo, methodResult);
		System.out.println(tableView.toString());
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			TranslatableString title = ERROR_DIALOG_TITLE;
			TranslatableString message = ERROR_OPEN_URI.withParameters(uri.toString());
			showError(title, message, exception);
		}
	}

	/**
	 * Process method to show the result of an {@link ActionMethod} with return type
	 * {@link DownloadStream}. See
	 * {@link ActionMethodInfo#invokeShowResult(UserInterfaceController, Object, Object, Object)}
	 *
	 * @param methodOwner
	 * @param methodInfo
	 * @param methodParameter
	 */
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			DownloadStream methodResult) {
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
				showError(ERROR_DIALOG_TITLE, ERROR_SAVE_FILE, e);
			}
			// open file
			try {
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				showError(ERROR_DIALOG_TITLE, ERROR_OPEN_FILE, e);
			}
		}
	}

	@Override
	public void showActionMethodResult(Object methodOwner, ActionMethodInfo methodInfo, Object methodParameter,
			String methodResult) {
		System.out.println(methodResult);

	}

	public void editActionMethodParameter(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object actionMethodParameterValue) {
		// Do nothing: The user interface controller will create the
		// domainObject from the command line arguments
	}

	@Override
	public void showMessage(TranslatableString message) {
		String translation = message.getTranslation(languageProvider);
		System.out.println(translation);
	}
}
