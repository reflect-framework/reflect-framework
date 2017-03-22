package nth.reflect.javafx.control.tabpane;

import java.util.List;

import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.StringPropertyBase;
import javafx.scene.Node;
import nth.introspect.layer1userinterface.item.Item;

public interface RfxTab  {
	
	public StringPropertyBase getNameProperty() ;
	public ObjectPropertyBase<List<Item>> getMenuItemsProperty() ;
	public Node getContent();
	
	

}
