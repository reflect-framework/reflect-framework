package nth.reflect.fw.javafx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.propertypanel.PropertyPanelFactory;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.javafx.control.dialog.Dialog;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.control.style.StyleSheet;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.tab.form.FormTab;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.grid.GridTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.controller.DownloadStream;
import nth.reflect.fw.layer1userinterface.controller.UploadStream;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ApplicationClassInfo;

public class UserinterfaceControllerForJavaFX extends GraphicalUserinterfaceController<Tab, PropertyPanel> {

//	private static final TranslatableString ERROR_DIALOG_TITLE = new TranslatableString(
//			UserinterfaceControllerForJavaFX.class.getCanonicalName() + ".error.dialog.title", "Error");
//	private static final TranslatableString ERROR_OPEN_URI_MESSAGE = new TranslatableString(
//			UserinterfaceControllerForJavaFX.class.getCanonicalName() + ".error.open.uri.message",
//			"Error opening URI: %s");
//	private static final TranslatableString ERROR_SAVE_FIlE_MESSAGE = new TranslatableString(
//			UserinterfaceControllerForJavaFX.class.getCanonicalName() + ".error.file.save.message",
//			"Error saving file.");
	private static final TranslatableString INFO_DIALOG_TITLE = new TranslatableString(
			UserinterfaceControllerForJavaFX.class.getCanonicalName() + ".info.dialog.title", "Info");
	private MainWindow mainWindow;

	public UserinterfaceControllerForJavaFX(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
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
	public void showInfoMessage(TranslatableString message) {
		List<Item> items = new ArrayList();
		items.add(new DialogCloseItem(languageProvider));
		showDialog(null, INFO_DIALOG_TITLE, message, items);
	}

	@Override
	public void showDialog(DialogType dialogType, TranslatableString title, TranslatableString message,
			List<Item> items) {
		String translatedTitle = title.translate(languageProvider);
		String translatedMessage = message.translate(languageProvider);
		Dialog dialog = new Dialog(mainWindow, translatedTitle, translatedMessage, items);
		dialog.show(mainWindow);
	}

	@Override
	public void showProgressDialog(TranslatableString taskDescription, int currentValue, int maxValue) {
		// TODO Auto-generated method stub

	}

	@Override
	public void closeProgressDialog() {
		// TODO Auto-generated method stub

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
	public void downloadFile(DownloadStream downloadStream) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.setInitialFileName(downloadStream.getFile().getName());
		File directory = downloadStream.getFile().getParentFile();
		if (directory != null) {
			fileChooser.setInitialDirectory(directory);
		}
		ReflectApplicationForJavaFX application = userInterfaceContainer.get(ReflectApplicationForJavaFX.class);
		Stage stage = application.getPrimaryStage();
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
			// safe file
			try {
				InputStream inputStream = downloadStream.getInputStream();
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
		}

	}

	@Override
	public void launch() {
		ReflectApplicationForJavaFX application = userInterfaceContainer.get(ReflectApplicationForJavaFX.class);

		Stage primaryStage = application.getPrimaryStage();

		try {
			mainWindow = new MainWindow(userInterfaceContainer);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(mainWindow);
		new StyleSheet().addToScene(scene);
		// scene.getStylesheets().add(UserinterfaceControllerForJavaFX.class.getResource("default-style.css").toExternalForm());

		primaryStage.setScene(scene);

		ReflectionProvider reflectProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ApplicationClassInfo applicationClassInfo = reflectProvider.getApplicationClassInfo();
		initIcon(primaryStage, applicationClassInfo);
		String title = applicationClassInfo.getDisplayName();
		primaryStage.setTitle(title);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	private void initIcon(Stage primaryStage, ApplicationClassInfo applicationClassInfo) {
		URL iconUrl = applicationClassInfo.getIcon();
		if (iconUrl != null) {
			Image icon = new Image(iconUrl.toString());
			primaryStage.getIcons().add(icon);
		}
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo, UploadStream uploadStream) {
		FileChooser fileChooser = new FileChooser();
		String title = methodInfo.createTitle(uploadStream).translate(languageProvider);
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(uploadStream.getFileTypeDescription(),
				uploadStream.fileExtentionFilters()));
		ReflectApplicationForJavaFX application = userInterfaceContainer.get(ReflectApplicationForJavaFX.class);
		File selectedFile = fileChooser.showOpenDialog(application.getPrimaryStage());
		if (selectedFile != null) {
			uploadStream.setFile(selectedFile);
			processActionMethodExecution(methodOwner, methodInfo, uploadStream);
		}

	}

	/**
	 * JavaFX does not allow executing threads on the event thread, so we run it
	 * later.
	 */
	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		Platform.runLater(methodExecutionRunnable);
	}

	@Override
	public PropertyPanelFactory<PropertyPanel> getPropertyPanelFactory() {
		PropertyFieldProvider propertyFieldProvider = userInterfaceContainer.get(PropertyFieldProvider.class);
		return new nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanelFactory(propertyFieldProvider);
	}

}
