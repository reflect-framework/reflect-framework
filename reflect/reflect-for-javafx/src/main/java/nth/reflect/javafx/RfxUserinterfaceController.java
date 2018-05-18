package nth.reflect.javafx;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.controller.UploadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.view.form.RfxFormView;
import nth.reflect.javafx.control.view.table.RfxTableView;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxUserinterfaceController extends GraphicalUserinterfaceController<View> {

	private RfxWindow mainWindow;

	public RfxUserinterfaceController(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
	}

	@Override
	public View createFormView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		return new RfxFormView(userInterfaceContainer, serviceObject, actionMethodInfo,
				methodParameterValue, domainObject, formMode);
	}

	@Override
	public View createTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		return new RfxTableView(userInterfaceContainer, serviceObject, actionMethodInfo,
				methodParameterValue);
	}

	@Override
	public View createTreeTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void showInfoMessage(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void showDialog(DialogType dialogType, String title, String message, List<Item> items) {
		JFXDialogLayout content = new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody(new Text(message));
		
		JFXDialog dialog = new JFXDialog(mainWindow, content, DialogTransition.CENTER, true);
		dialog.setLayoutX(mainWindow.getWidth()/2);
		dialog.setLayoutY(mainWindow.getHeight()/2);
		
		List<RfxPrimaryButton>actionButtons=new ArrayList<>();
		for (Item item : items) {
			RfxPrimaryButton button=new RfxPrimaryButton(item);
			button.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
				@Override
				public void handle(javafx.event.ActionEvent event) {
					dialog.close();
					item.getAction().run();
				}
			});
			actionButtons.add(button);
		}
		content.setActions(actionButtons);
		
		dialog.show(mainWindow);
	}

	@Override
	public void showProgressDialog(String taskDescription, int currentValue, int maxValue) {
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
			showErrorDialog("Error", "Error browsing URI: " + uri.toString(), exception);
		}
	}

	@Override
	public void downloadFile(DownloadStream downloadStream) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save File");
		fileChooser.setInitialFileName(downloadStream.getFile().getName());
		File directory = downloadStream.getFile().getParentFile();
		if (directory!=null) {
			fileChooser.setInitialDirectory(directory);
		}
		ReflectApplicationForJavaFX application = userInterfaceContainer
				.get(ReflectApplicationForJavaFX.class);
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
				showErrorDialog("Error saving file", "Failed to save file.", e);
			}
		}

	}

	@Override
	public RfxViewContainer getViewContainer() {
		return new RfxViewContainer(mainWindow);
	}

	@Override
	public void launch() {
		ReflectApplicationForJavaFX application = userInterfaceContainer
				.get(ReflectApplicationForJavaFX.class);

		Stage primaryStage = application.getPrimaryStage();

		try {
			mainWindow = new RfxWindow(userInterfaceContainer);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(mainWindow);
		new RfxStyleSheet().addToScene(scene);
		// scene.getStylesheets().add(RfxUserinterfaceController.class.getResource("default-style.css").toExternalForm());

		primaryStage.setScene(scene);

		Image icon = new Image("file:icon.png");// TODO!!!
		primaryStage.getIcons().add(icon);

		String title = getApplicationTitle(application);
		primaryStage.setTitle(title);
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	private String getApplicationTitle(ReflectApplicationForJavaFX application) {
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title = applicationInfo.getDisplayName();
		return title;
	}

	@Override
	public void editActionMethodParameter(Object methodOwner, ActionMethodInfo methodInfo,
			UploadStream uploadStream) {
		FileChooser fileChooser = new FileChooser();
		String title = TitleUtil.createTitle(reflectionProvider, methodInfo, uploadStream);
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
				uploadStream.getFileTypeDescription(), uploadStream.fileExtentionFilters()));
		ReflectApplicationForJavaFX application = userInterfaceContainer
				.get(ReflectApplicationForJavaFX.class);
		File selectedFile = fileChooser.showOpenDialog(application.getPrimaryStage());
		if (selectedFile != null) {
			uploadStream.setFile(selectedFile);
			processActionMethodExecution(methodOwner, methodInfo, uploadStream);
		}

	}

	/**
	 * JavaFX does not allow executing threads on the event thread, so we run it later.
	 */
	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		Platform.runLater(methodExecutionRunnable);
	}

	

}
