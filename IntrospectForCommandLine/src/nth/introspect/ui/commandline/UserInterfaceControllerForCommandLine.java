package nth.introspect.ui.commandline;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import nth.introspect.IntrospectApplication;
import nth.introspect.generic.exception.MethodNotSupportedException;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.item.Item.Action;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;
import nth.introspect.layer5provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.commandline.domain.command.Command;
import nth.introspect.ui.commandline.domain.command.CommandService;
import nth.introspect.ui.commandline.domain.command.IntrospectCommandLineException;
import nth.introspect.ui.commandline.domain.command.Parameter;
import nth.introspect.ui.commandline.view.CommandLineView;
import nth.introspect.ui.commandline.view.CommandLineViewContainer;
import nth.introspect.ui.commandline.view.FormView;
import nth.introspect.ui.commandline.view.HelpView;
import nth.introspect.ui.commandline.view.TableView;
import nth.introspect.ui.view.FormMode;

public class UserInterfaceControllerForCommandLine extends
		GraphicalUserinterfaceController<CommandLineView> {

	private CommandLineViewContainer viewContainer;
	private final IntrospectApplication application;
	private final LanguageProvider languageProvider;
	private final PathProvider pathProvider;

	public UserInterfaceControllerForCommandLine(
			UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
		this.application = userInterfaceContainer.getApplication();
		this.languageProvider = userInterfaceContainer.getLanguageProvider();
		this.pathProvider = userInterfaceContainer.getPathProvider();
	}

	@Override
	public void showProgressDialog(String taskDescription, int currentValue,
			int maxValue) {
		// Not supported yet
	}

	@Override
	public void closeProgressDialog() {
		// Not supported yet
	}

	@Override
	public void start() {
		try {
			viewContainer = new CommandLineViewContainer();
			IntrospectApplicationForCommandLine commandLineApplication = (IntrospectApplicationForCommandLine) application;
			String[] arguments = commandLineApplication
					.getCommandLineArguments();

			List<Command> commands = CommandService
					.getCommands(getUserInterfaceContainer());

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

	private List<String[]> getArgumentsFromCommandFile(String[] arguments)
			throws IOException {
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
			getViewContainer().addView(helpView);
			System.exit(1);

		}

		List<Parameter> parameters = command.getParameters();
		boolean invalidNumberOfArguments = arguments.length != 1 + parameters
				.size();// number of arguments != command + parameters
		if (invalidNumberOfArguments) {
			HelpView helpView = new HelpView("Invalid number of arguments!",
					commands);
			getViewContainer().addView(helpView);
		}

		Object methodParameterValue = command.createMethodParameter();

		for (Parameter parameter : parameters) {
			int parameterIndex = parameters.indexOf(parameter);
			String argument = arguments[parameterIndex + 1];
			parameter.parseArgument(methodParameterValue, argument);
		}

		Object serviceObject = command.getServiceObject();
		MethodInfo methodInfo = command.getMethodInfo();
		// override form mode to execute method directly (because this is
		// the only mode supported for a command line interface)
		methodInfo.setFormMode(ExecutionModeType.EXECUTE_METHOD_DIRECTLY);

		startExecution(serviceObject, methodInfo, methodParameterValue);
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
		String fileName = arguments[0].replace(" ", "%20");
		URI rootPath = pathProvider.getRootPath();
		URI filePath = rootPath.resolve(fileName);
		return new File(filePath);
	}

	@Override
	public void showInfoMessage(String message) {
		// display message
		System.out.println(message);
	}

	@Override
	public void showDialog(DialogType dialogType, String title, String message,
			List<Item> items) {
		StringBuilder txt = new StringBuilder(dialogType.toString());
		txt.append(" - ");
		txt.append(title);
		txt.append("\n");
		txt.append(message);
		txt.append("\n");
		System.out.println(txt.toString());

		StringBuilder options = new StringBuilder(dialogType.toString());
		options.append(languageProvider.getText("Options: "));
		for (int index = 0; index < items.size(); index++) {
			options.append(index + 1);
			options.append("=");
			options.append(items.get(index).getText());
			if (index == items.size() - 1) {
				// last option
				options.append("? ");
			} else {
				options.append(", ");
			}

		}
		System.out.print(options.toString());

		Scanner reader = new Scanner(System.in);
		int input = -1;
		while (input < 1 || input > items.size()) {
			try {
				char c = reader.next().charAt(0);
				input = Integer.valueOf("" + c);
			} catch (Exception e) {
			}
			if (input < 1 || input > items.size()) {
				System.out.println(languageProvider.getText("Invalid input."));
				System.out.print(options.toString());
			}
		}

		Item selectedItem = items.get(input - 1);
		Action action = selectedItem.getAction();
		if (action != null) {
			action.run();
		}

	}

	@Override
	public CommandLineViewContainer getViewContainer() {
		return viewContainer;
	}

	@Override
	public void downloadFile(DownloadStream downloadStream) {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(downloadStream.getFile());
		InputStream inputStream = downloadStream.getInputStream();
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
	public CommandLineView createFormView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		return new FormView(
				getUserInterfaceContainer().getDomainInfoProvider(),
				methodInfo, domainObject);
	}

	@Override
	public CommandLineView createTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		return new TableView(getUserInterfaceContainer()
				.getDomainInfoProvider(), methodInfo,
				(Collection<?>) methodReturnValue);
	}

	@Override
	public CommandLineView createTreeTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		throw new MethodNotSupportedException();
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			showErrorDialog("Error", "Error browsing URI: " + uri.toString(),
					exception);
		}
	}

}
