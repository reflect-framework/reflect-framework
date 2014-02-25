package nth.introspect.ui.commandline;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Collection;
import java.util.List;

import javax.swing.JFileChooser;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.format.MethodNotSupportedException;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.ui.AbstractUserinterfaceProvider;
import nth.introspect.ui.commandline.domain.command.Command;
import nth.introspect.ui.commandline.domain.command.CommandService;
import nth.introspect.ui.commandline.domain.command.Parameter;
import nth.introspect.ui.commandline.view.CommandLineView;
import nth.introspect.ui.commandline.view.CommandLineViewContainer;
import nth.introspect.ui.commandline.view.FormView;
import nth.introspect.ui.commandline.view.HelpView;
import nth.introspect.ui.commandline.view.TableView;
import nth.introspect.util.ExceptionUtil;

public class CommandLineUserInterfaceProvider extends AbstractUserinterfaceProvider<CommandLineView> {

	private final CommandLineViewContainer viewContainer;

	public CommandLineUserInterfaceProvider(String[] arguments) {
		viewContainer = new CommandLineViewContainer();
		startExecution(arguments);
	}

	@Override
	public void showProgressDialog(String taskDescription, int currentValue, int maxValue) {
		// Not supported yet
	}

	@Override
	public void closeProgressDialog() {
		// Not supported yet
	}

	private void startExecution(String[] arguments) {
		try {

			List<Command> commands = CommandService.getCommands();

			Command command = CommandService.findCommand(commands, arguments);
			if (command == null) {
				HelpView helpView = new HelpView("Unknown command", commands);
				getViewContainer().addView(helpView);

			}

			List<Parameter> parameters = command.getParameters();
			boolean invalidNumberOfArguments = arguments.length != 1 + parameters.size();// number of arguments != command + parameters
			if (invalidNumberOfArguments) {
				HelpView helpView = new HelpView("Invalid number of arguments!", commands);
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
			//override form mode to execute method directly (because this is the only mode supported for a command line interface)
			methodInfo.setFormMode(FormModeType.executeMethodDirectly);

			startExecution(serviceObject, methodInfo, methodParameterValue);
			
			//System.exit(0);
		} catch (Exception e) {
			showErrorDialog("Error", "Error executing a command", e);
		}

	}

	@Override
	public void showErrorDialog(String title, String message, Throwable throwable) {
		StringBuffer text = new StringBuffer(message);
		// add stack trace
		if (throwable != null) {
			text.append("\n\n");
			text.append(ExceptionUtil.getRootCauseStackTrace(throwable));
		}

		// display error
		System.out.println(title + ": " + text);
	}

	@Override
	public void showInfoMessage(String message) {
		// display message
		System.out.println(message);
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
	public CommandLineView createFormView(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue, Object domainObject, FormModeType executionMode) {
		return new FormView(methodInfo, domainObject);
	}

	@Override
	public CommandLineView createTableView(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue, Object methodReturnValue) {
		return new TableView(methodInfo, (Collection<?>) methodReturnValue);
	}

	@Override
	public CommandLineView createTreeTableView(Object serviceObject, MethodInfo methodInfo, Object methodParameterValue, Object methodReturnValue) {
		throw new MethodNotSupportedException();
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			Introspect.getUserInterfaceProvider().showErrorDialog("Error", "Error browsing URI: " + uri.toString(), exception);
		}
	}


}
