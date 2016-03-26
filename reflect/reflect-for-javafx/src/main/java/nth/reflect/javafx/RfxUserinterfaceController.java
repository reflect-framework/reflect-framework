package nth.reflect.javafx;

import java.net.URI;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.DisplaySize;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.basic.Font;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxUserinterfaceController extends GraphicalUserinterfaceController<RfxView> {

	private RfxWindow mainWindow;

	public RfxUserinterfaceController(UserInterfaceContainer userInterfaceContainer) {
		super(userInterfaceContainer);
	}

	@Override
	public RfxView createFormView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object domainObject, FormMode formMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RfxView createTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
			Object methodParameterValue, Object methodReturnValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RfxView createTreeTableView(Object serviceObject, ActionMethodInfo actionMethodInfo,
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
		// TODO Auto-generated method stub

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DisplaySize getDisplaySize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getDisplayWidthInInches() {
		// TODO Auto-generated method stub
		return 0;
	}

	private Label createLabel(Font font) {
		Label label = new Label(font.getName());
		label.setFont(RfxFontFactory.create(font));
		return label;
	}

	@Override
	public void launch() {
		ReflectApplicationForJavaFX application = userInterfaceContainer
				.get(ReflectApplicationForJavaFX.class);
		Stage primaryStage = application.getPrimaryStage();
		
		MaterialStyle materialStyle=getMaterialStyle();
		
		

		
		VBox content = new VBox();
		content.getChildren().add(createLabel(MaterialFont.getDisplay4(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getDisplay3(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getDisplay2(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getDisplay1(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getHeadLine(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getTitle(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getSubHeading(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getBody2(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getBody1(DisplayScale.DENSE)));
		content.getChildren().add(createLabel(MaterialFont.getButton(DisplayScale.DENSE)));

		 RfxList menu = new RfxList(materialStyle);
		ObservableList<String> items =FXCollections.observableArrayList (
			    "Single", "Double", "Suite", "Family App");
		menu.setItems(items);
		
		BorderPane contentWithMenu=new BorderPane();
		contentWithMenu.setCenter(content);
		contentWithMenu.setLeft(menu);
		
		mainWindow = new RfxWindow(primaryStage, contentWithMenu, userInterfaceContainer, materialStyle);

		Scene scene = new Scene(mainWindow);
		new RfxStyleSheet(materialStyle).addToScene(scene);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
