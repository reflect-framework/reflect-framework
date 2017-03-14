package nth.reflect.javafx;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;

import org.omg.CORBA.ParameterModeHelper;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import nth.introspect.IntrospectApplication;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.DownloadStream;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.MaterialStyle;
import nth.introspect.ui.style.basic.Font;
import nth.introspect.ui.view.FormMode;
import nth.reflect.javafx.control.list.mainmenu.RfxMainMenuList;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.style.RfxStyleSheet;
import nth.reflect.javafx.control.tabpane.RfxTabPane;
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
		

		
		RfxTabPane content = createContent2(materialStyle);
		

//		
//		RfxMainMenuList menu=new RfxMainMenuList(userInterfaceContainer);
//		
//		BorderPane contentWithMenu=new BorderPane();
//		contentWithMenu.setCenter(content);
//		contentWithMenu.setLeft(menu);
		
		try {
			mainWindow = new RfxWindow( content, userInterfaceContainer, materialStyle);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(mainWindow);
		new RfxStyleSheet().addToScene(scene);
		//scene.getStylesheets().add(RfxUserinterfaceController.class.getResource("default-style.css").toExternalForm());
		
		primaryStage.setScene(scene);

		Image icon = new Image("file:icon.png");//TODO!!!
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

	private VBox createContent() {
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
		return content;
	}
	
	private RfxTabPane createContent2(MaterialStyle materialStyle) {
		
	RfxTabPane tabPane = new RfxTabPane(userInterfaceContainer);
	tabPane.setPrefSize(300, 200);
	Tab tab = new Tab();
	tab.setText("Products");
	tab.setContent(new Label("Content"));
	tabPane.getTabs().add(tab);
	
	
	tabPane.setPrefSize(300, 200);
	tab = new Tab();
	tab.setText("Shopping Cart");
	tab.setContent(new Label("Content"));
	tabPane.getTabs().add(tab);
	
	
	tabPane.setPrefSize(300, 200);
	tab = new Tab();
	tab.setText("Deliveries");
	tab.setContent(createContent());
	tabPane.getTabs().add(tab);
	
	
	return tabPane;
	}
}
