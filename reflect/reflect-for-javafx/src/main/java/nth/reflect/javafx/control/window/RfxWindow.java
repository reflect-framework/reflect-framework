
package nth.reflect.javafx.control.window;

import java.net.MalformedURLException;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.DisplayScale;
import nth.introspect.ui.style.MaterialFont;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.style.RfxFontFactory;
import nth.reflect.javafx.control.tabpane.RfxTabBarPane;

public class RfxWindow extends BorderPane implements RfxControl {

	private static final int MENU_WIDTH = 0;
//	private StackPane contentPlaceHolder = new StackPane();
//	private RfxApplicationToolbar applicationToolbar;

	public RfxWindow(  UserInterfaceContainer userInterfaceContainer, Color primaryColor, Color accentColor, ContentColor contentColor) throws MalformedURLException {
		super();
		Font font = RfxFontFactory.create(MaterialFont.getFontAwesome());
		
		RfxUtil.init(this,primaryColor, accentColor, contentColor );
		
		//primaryStage.initStyle(StageStyle.TRANSPARENT);
		setMinWidth(300);
		setMinHeight(500);
//		setPickOnBounds(false);
		//TODO initStyleProperties()
		//TODO initControlls()

//		applicationToolbar = new RfxApplicationToolbar( userInterfaceContainer);

		
		RfxTabBarPane tabPane = createTabPane( userInterfaceContainer);
		
		
//		contentPlaceHolder.setMinSize(0, 0);
//		contentPlaceHolder.getChildren().add(tabPane);
//		tabPane.setMinSize(0, 0);
//		VBox.setVgrow(contentPlaceHolder, Priority.ALWAYS);
		//contentPlaceHolder.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,				BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 3, 3, 3))));

		setCenter(tabPane);;


	}


	public boolean isWide(){
		boolean wide = getWidth()<=(MENU_WIDTH*3);
		return wide;
	}

	
	private RfxTabBarPane createTabPane( UserInterfaceContainer userInterfaceContainer) {
		
	RfxTabBarPane tabPane = new RfxTabBarPane(userInterfaceContainer);
	//tabPane.setPrefSize(300, 200);
//	Tab tab = new Tab();
//	tab.setText("Products");
//	tab.setContent(new Label("Content"));
//	tabPane.getTabs().add(tab);
//	
//	
//	tabPane.setPrefSize(300, 200);
//	tab = new Tab();
//	tab.setText("Shopping Cart");
//	tab.setContent(new Label("Content"));
//	tabPane.getTabs().add(tab);
//	
//	
//	tabPane.setPrefSize(300, 200);
//	tab = new Tab();
//	tab.setText("Deliveries");
//	tab.setContent(createContent());
//	tabPane.getTabs().add(tab);
//	
	
	return tabPane;
	}
	
	
	
}