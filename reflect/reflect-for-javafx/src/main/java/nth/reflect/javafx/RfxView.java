package nth.reflect.javafx;

import java.util.List;

import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.Node;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;

public abstract interface RfxView  extends View {

	
	public ObjectPropertyBase<List<Item>> getMenuItemsProperty() ;
	
	public Node getContent();

}
