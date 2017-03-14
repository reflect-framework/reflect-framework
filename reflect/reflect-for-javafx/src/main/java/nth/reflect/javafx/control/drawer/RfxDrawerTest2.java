package nth.reflect.javafx.control.drawer;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RfxDrawerTest2 extends Application {

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		  SplitPane root = new SplitPane();

	        final Pane paneFixed = new StackPane();
	        paneFixed.getChildren().add(new Text("fixed"));

	        SplitPane.setResizableWithParent(paneFixed, Boolean.FALSE);

	        Pane paneFree = new StackPane();
	        paneFree.getChildren().add(new Text("free"));

	        root.getItems().addAll(paneFree, paneFixed);
root.setStyle(".split-pane > .split-pane-divider {   -fx-background-insets: 0, 0 1 0 1; } ");
	        stage.setScene(new Scene(root, 300, 200));
	        stage.show();
	}

}
