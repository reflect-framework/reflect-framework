package nth.introsepect.ui.swing;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import nth.introsepect.ui.swing.dialog.toast.Toast;
import nth.introsepect.ui.swing.dialog.toast.Toast.Style;
import nth.introsepect.ui.swing.mainwindow.MainWindow;
import nth.introsepect.ui.swing.view.SwingView;
import nth.introsepect.ui.swing.view.form.FormView;
import nth.introsepect.ui.swing.view.table.TableView;
import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.userinterface.DialogType;
import nth.introspect.provider.userinterface.DownloadStream;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.item.Item.Action;
import nth.introspect.ui.AbstractUserinterfaceProvider;
import nth.introspect.ui.item.dialog.DialogCloseItem;
import nth.introspect.ui.item.dialog.DialogShowStackTraceItem;
import nth.introspect.util.ExceptionUtil;

public class SwingUserinterfaceProvider extends
		AbstractUserinterfaceProvider<SwingView> {

	private MainWindow mainWindow;

	public SwingUserinterfaceProvider(Object application) {
		mainWindow = new MainWindow(application);
	}

	@Override
	public void showProgressDialog(String taskDescription, int currentValue,
			int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

	}

	public MainWindow getMainWindow() {
		return mainWindow;
	}



	@Override
	public void showInfoMessage(String message) {
		Toast.makeText(mainWindow, message, Style.NORMAL).display();
	}

	@Override
	public void downloadFile(DownloadStream downloadStream) {
		JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(downloadStream.getFile());
		InputStream inputStream = downloadStream.getInputStream();
		int returnVal = chooser.showSaveDialog(mainWindow);
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
	public nth.introspect.provider.userinterface.view.ViewContainer getViewContainer() {
		return mainWindow.getViewContainer();
	}

	@Override
	public SwingView createFormView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object domainObject, boolean formIsReadonly) {
		return new FormView(serviceObject, methodInfo, methodParameterValue,
				domainObject, formIsReadonly);
	}

	@Override
	public SwingView createTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		return new TableView(serviceObject, methodInfo, methodParameterValue);
	}

	@Override
	public SwingView createTreeTableView(Object serviceObject,
			MethodInfo methodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			Introspect.getUserInterfaceProvider().showErrorDialog("Error",
					"Error browsing URI: " + uri.toString(), exception);
		}
	}

	@Override
	public void showDialog(DialogType dialogType, String title, String message,
			List<Item> items) {

		// get dialog type
		int messageType = 0;
		switch (dialogType) {
		case QUESTION:
			messageType = JOptionPane.QUESTION_MESSAGE;
			break;
		case ERROR:
			messageType = JOptionPane.ERROR_MESSAGE;
		}

		// get options, assuming that all items are enabled and visible
		Object[] options = new String[items.size()];
		for (int index = 0; index < items.size(); index++) {
			options[index] = items.get(index).getText();
		}
		Object defaultOption = options[items.size() - 1];

		// show dialog
		int selectedIndex = JOptionPane.showOptionDialog(mainWindow, message,
				title, JOptionPane.DEFAULT_OPTION, messageType, null, options,
				defaultOption);

		// execute selected item
		Item selectedItem = items.get(selectedIndex);
		Action action = selectedItem.getAction();
		if (action != null) {
			action.run();
		}

	}

}
