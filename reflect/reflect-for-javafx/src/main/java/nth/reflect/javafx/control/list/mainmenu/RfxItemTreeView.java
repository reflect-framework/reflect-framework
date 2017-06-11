package nth.reflect.javafx.control.list.mainmenu;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.reflect.javafx.control.style.RfxStyleGroup;
import nth.reflect.javafx.control.style.RfxStyleSelector;
import nth.reflect.javafx.control.style.RfxStyleSheet;
/**
 * Test to replace   {@link RfxMainMenuList}
 * @author nilsth
 *
 */
public class RfxItemTreeView extends TreeView {
public RfxItemTreeView(UserInterfaceContainer userInterfaceContainer) {
	super();
	
	  TreeItem<Alert> rootItem = createRootItem();
	  setRoot(rootItem);
	
	
	 setShowRoot(false);
     setCellFactory(new Callback<TreeView<Alert>, TreeCell<Alert>>() {
         @Override
         public TreeCell<Alert> call(TreeView<Alert> p) {
             return new RfxItemTreeCell();
         }
     });
	
		
	
	
//	setOnMouseClicked(this::onMouseClick);
}


//TODO change Alert into Item
private TreeItem<Alert> createRootItem() {
	TreeItem<Alert> rootNode = new TreeItem<>(new Alert("dummy", "dummy"));
       rootNode.setExpanded(true);
       TreeItem<Alert> groupNode = new TreeItem<>(new Alert("group item", "group item"));
    
       groupNode.getChildren().addAll(new TreeItem<>(new Alert("sub item 1", "sub item 1")),
               new TreeItem<>(new Alert("sub item 2", "sub item 2")),
               new TreeItem<>(new Alert("sub item 3", "sub item 3")));

       rootNode.getChildren().addAll(new TreeItem<>(new Alert("item 1", "item 1")),
               groupNode,
               new TreeItem<>(new Alert("item 2", "item 2")),
               new TreeItem<>(new Alert("item 3", "item 3")));
       
   	//TODO expandServiceItemsAtStartUp(items);

       
       return rootNode;
}



public static class Alert {

    private final SimpleStringProperty name;
    private final SimpleStringProperty status;

    private Alert(String name, String department) {
        this.name = new SimpleStringProperty(name);
        this.status = new SimpleStringProperty(department);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String fName) {
        name.set(fName);
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String fName) {
        status.set(fName);
    }
}
}
