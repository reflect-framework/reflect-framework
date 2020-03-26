package nth.reflect.fw.ui.swing;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.swing.dialog.toast.Toast;
import nth.reflect.fw.ui.swing.dialog.toast.Toast.Style;
import nth.reflect.fw.ui.swing.mainwindow.MainWindow;
import nth.reflect.fw.ui.swing.tab.Tab;
import nth.reflect.fw.ui.swing.tab.form.FormTab;
import nth.reflect.fw.ui.swing.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.ui.swing.tab.form.proppanel.PropertyPanelFactory;
import nth.reflect.fw.ui.swing.tab.grid.GridTab;

public class UserinterfaceControllerForSwing extends GraphicalUserinterfaceController<Tab, PropertyPanel> {

	private MainWindow mainWindow;

	public UserinterfaceControllerForSwing(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
	}

	@Override
	public void launch() {
		try {
			mainWindow = new MainWindow(userInterfaceContainer);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue) {
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
	public void showInfoMessage(TranslatableString message) {
		String translatedMessage = message.getTranslation(languageProvider);
		Toast.makeText(mainWindow, translatedMessage, Style.NORMAL).display();
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) {
		final JFileChooser fc = new JFileChooser();
		String title = methodInfo.createTitle(uploadStream).getTranslation(languageProvider);
		fc.setDialogTitle(title);
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		FileNameExtensionFilter filter = new FileNameExtensionFilter(uploadStream.getFileTypeDescription(),
				uploadStream.fileExtentionFilters());
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
				showErrorDialog(ERROR_DIALOG_TITLE, ERROR_SAVE_FILE, e);
			}
			// open file
			try {
				Desktop.getDesktop().open(file);
			} catch (Exception e) {
				showErrorDialog(ERROR_DIALOG_TITLE, ERROR_OPEN_FILE, e);
			}
		}
	}

	@Override
	public Tab createFormTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		return new FormTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue, domainObject,
				formMode);
	}

	@Override
	public Tab createTableTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		return new GridTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
	}

	@Override
	public Tab createTreeTableTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object methodReturnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void openURI(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (IOException exception) {
			TranslatableString title = ERROR_DIALOG_TITLE;
			TranslatableString message = ERROR_OPEN_URI.withParameters(uri.toString());
			showErrorDialog(title, message, exception);
		}
	}

	@Override
	public void showDialog(DialogType dialogType, TranslatableString title, TranslatableString message,
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
		String translatedMessage = message.getTranslation(languageProvider);
		String translatedTitle = title.getTranslation(languageProvider);
		int selectedIndex = JOptionPane.showOptionDialog(mainWindow, translatedMessage, translatedTitle,
				JOptionPane.DEFAULT_OPTION, messageType, null, options, defaultOption);

		// execute selected item
		if (selectedIndex != -1) {
			Item selectedItem = items.get(selectedIndex);
			Action action = selectedItem.getAction();
			if (action != null) {
				action.run();
			}
		}

	}

	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		Thread methodExecutionThread = new Thread(methodExecutionRunnable);
		methodExecutionThread.start();
	}

	@Override
	public PropertyPanelFactory getPropertyPanelFactory() {
		PropertyFieldProvider propertyFieldProvider = userInterfaceContainer.get(PropertyFieldProvider.class);
		return new PropertyPanelFactory(propertyFieldProvider);
	}

}
