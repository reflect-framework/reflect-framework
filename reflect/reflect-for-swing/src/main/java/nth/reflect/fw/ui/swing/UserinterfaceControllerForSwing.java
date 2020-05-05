package nth.reflect.fw.ui.swing;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.item.Item.Action;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.stream.UploadStream;
import nth.reflect.fw.ui.swing.dialog.toast.Toast;
import nth.reflect.fw.ui.swing.dialog.toast.Toast.Style;
import nth.reflect.fw.ui.swing.mainwindow.MainWindow;
import nth.reflect.fw.ui.swing.tab.Tab;
import nth.reflect.fw.ui.swing.tab.form.FormTab;
import nth.reflect.fw.ui.swing.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.ui.swing.tab.form.proppanel.PropertyPanelFactory;

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
	public void showMessage(TranslatableString message) {
		String translatedMessage = message.getTranslation(languageProvider);
		Toast.makeText(mainWindow, translatedMessage, Style.NORMAL).display();
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) {
		final JFileChooser fc = new JFileChooser();
		String title = methodInfo.getTitle(uploadStream).getTranslation(languageProvider);
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
	public Tab createFormTab(Object serviceObject, ActionMethodInfo actionMethodInfo, Object methodParameterValue,
			Object domainObject, FormMode formMode) {
		return new FormTab(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue, domainObject,
				formMode);
	}

	@Override
	public void showDialog(TranslatableString title, TranslatableString message, List<Item> items) {

		// get options, assuming that all items are enabled and visible
		Object[] options = new String[items.size()];
		for (int index = 0; index < items.size(); index++) {
			options[index] = items.get(index).getText();
		}
		Object defaultOption = options[items.size() - 1];

		// show dialog
		String translatedMessage = message.getTranslation(languageProvider);
		String translatedTitle = title.getTranslation(languageProvider);
		int selectedIndex = JOptionPane
				.showOptionDialog(mainWindow, translatedMessage, translatedTitle, JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, defaultOption);

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
