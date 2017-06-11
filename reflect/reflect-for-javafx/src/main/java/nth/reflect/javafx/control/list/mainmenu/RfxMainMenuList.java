package nth.reflect.javafx.control.list.mainmenu;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.ui.item.ItemFactory;
import nth.introspect.ui.item.method.MethodOwnerItem;
import nth.reflect.javafx.control.RfxControl;
import nth.reflect.javafx.control.list.RfxList;

public class RfxMainMenuList extends RfxList<Item> implements RfxControl{

	public RfxMainMenuList(UserInterfaceContainer userInterfaceContainer) {
		super();
		setCellFactory(new RfxMainMenuListCellFactory());
		List<MethodOwnerItem> serviceObjectItems = ItemFactory
				.createMainMenuItems(userInterfaceContainer);

		ObservableList<Item> items = FXCollections
				.observableArrayList();
		
		for (Item serviceObjectItem: serviceObjectItems) {
			items.add(serviceObjectItem);
			for (Item methodItem: ((MethodOwnerItem)serviceObjectItem).getChildren()) {
				items.add(methodItem);
			}
		}
		
		//TODO expandServiceItemsAtStartUp(items);
		
		setItems(items);
		setOnMouseClicked(this::onMouseClick);
	}
	
	public void onMouseClick(MouseEvent event) {
		Item item = getSelectionModel().getSelectedItem();
		item.getAction().run();
	}
	
	

}
