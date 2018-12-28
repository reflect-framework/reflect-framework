package nth.reflect.fw.javafx.control.verticalflingscroller;

import com.jfoenix.controls.JFXListView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

//From: http://stackoverflow.com/questions/26537548/javafx-listview-with-touch-events-for-scrolling-up-and-down

//Other solutions:
//http://stackoverflow.com/questions/16807189/javafx-scrollpane-speed-up-swiping-effect-on-touch-device
//https://tomsondev.bestsolution.at/2012/02/12/phone-like-scrollable-listview-with-javafx/
//https://www.google.nl/search?q=flinglist+javafx&oq=flinglist+javafx&aqs=chrome.0.69i59.4869j0j4&sourceid=chrome&ie=UTF-8#q=flingpane+javafx&*
//http://stackoverflow.com/questions/22680658/javafx-scrollview-swipe-effect
public class VerticalFlingScrollerDemo extends Application {

	int counter = 0 ;
	
	@Override
	public void start(Stage stage) throws Exception {

		JFXListView<Label> list = new JFXListView<Label>();
		for(int i = 0 ; i < 30 ; i++) list.getItems().add(new Label("Item " + i));
		list.getStyleClass().add("mylistview");
		new VerticalFlingScroller(list);

		
		final Scene scene = new Scene(list, 600, 600, Color.WHITE);
		stage.setTitle("JFX ListView Demo ");
//		scene.getStylesheets().add(VerticalFlingScrollerDemo.class.getResource("/resources/css/jfoenix-components.css").toExternalForm());
		stage.setScene(scene);
		stage.show();
	}
	
	public static void main(String[] args) { launch(args); }

}