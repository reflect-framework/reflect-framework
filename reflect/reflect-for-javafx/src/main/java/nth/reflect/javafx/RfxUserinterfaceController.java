package nth.reflect.javafx;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialog.DialogTransition;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeCell;
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
		return new RfxFormView(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue, domainObject, formMode);
	}

	@Override
	public View createTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		return new RfxTableView(userInterfaceContainer, serviceObject, actionMethodInfo, methodParameterValue);
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
		JFXDialogLayout content=new JFXDialogLayout();
		content.setHeading(new Text(title));
		content.setBody(new Text(message));
		//JFXDialog dialog=new JFXDialog(mainWindow,content,DialogTransition.CENTER,true);
		JFXDialog dialog=new JFXDialog();
		dialog.setContent(content);
		dialog.show();
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
		// TODO Auto-generated method stub

	}

	@Override
	public void downloadFile(DownloadStream downloadStream) {
		// TODO Auto-generated method stub

	}

	@Override
	public ViewContainer getViewContainer() {
		return new RfxViewContainer(mainWindow);
	}

	@Override
	public void launch() {
		ReflectApplicationForJavaFX application = userInterfaceContainer
				.get(ReflectApplicationForJavaFX.class);
		
		MaterialColors.init(application.getPrimaryColor(), application.getSecondaryColor(), application.getAccentColor(), application.getContentColor());
		
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

		primaryStage.show();
	}


	private String getApplicationTitle(ReflectApplicationForJavaFX application) {
		ReflectionProvider reflectionProvider = userInterfaceContainer
				.get(ReflectionProvider.class);
		ClassInfo applicationInfo = reflectionProvider.getClassInfo(application.getClass());
		String title = applicationInfo.getDisplayName();
		return title;
	}

}
