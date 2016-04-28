package nth.introspect.ui.swing;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.item.Item.Action;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.DisplaySize;
import nth.introspect.ui.swing.dialog.toast.Toast;
import nth.introspect.ui.swing.dialog.toast.Toast.Style;
import nth.introspect.ui.swing.mainwindow.MainWindow;
import nth.introspect.ui.swing.view.SwingView;
import nth.introspect.ui.swing.view.form.FormView;
import nth.introspect.ui.swing.view.table.TableView;
import nth.introspect.ui.view.FormMode;

public class UserinterfaceControllerForSwing extends
		GraphicalUserinterfaceController<SwingView> {

	private MainWindow mainWindow;

	public UserinterfaceControllerForSwing(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
	}

	@Override
	public void launch() {
		mainWindow = new MainWindow(userInterfaceContainer);
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

	/**
	 * TODO add abstract method editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) to {@link GraphicalUserinterfaceController}
	 */
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) {
		final JFileChooser fc = new JFileChooser();
		String title=TitleUtil.createTitle(reflectionProvider, methodInfo, uploadStream, true);
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(uploadStream.getFileTypeDescription(), uploadStream.getFileExtentions());
		fc.setFileFilter(filter);
		int result = fc.showOpenDialog(getMainWindow());
		if (result == JFileChooser.APPROVE_OPTION) {
		    File selectedFile = fc.getSelectedFile();
		    uploadStream.setFile(selectedFile);
		    processActionMethodExecution(methodOwner, methodInfo, uploadStream);
		}
	};
	
	
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
	public nth.introspect.layer1userinterface.view.ViewContainer<?> getViewContainer() {
		return mainWindow.getViewContainer();
	}

	@Override
	public SwingView createFormView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		return new FormView(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue,
				domainObject, formMode);
	}

	@Override
	public SwingView createTableView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		return new TableView(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
	}

	@Override
	public SwingView createTreeTableView(Object serviceObject,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			showErrorDialog("Error",
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
		if (selectedIndex != -1) {
			Item selectedItem = items.get(selectedIndex);
			Action action = selectedItem.getAction();
			if (action != null) {
				action.run();
			}
		}

	}

	
}
