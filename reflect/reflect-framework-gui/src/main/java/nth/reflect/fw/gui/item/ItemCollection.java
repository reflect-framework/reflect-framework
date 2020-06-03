package nth.reflect.fw.gui.item;

import java.util.Collection;

import nth.reflect.fw.gui.util.collection.UnmodifiableCollection;
import nth.reflect.fw.layer1userinterface.item.Item;

public abstract class ItemCollection extends UnmodifiableCollection<Item> {

	private static final long serialVersionUID = -56928534255692906L;

	protected ItemCollection(Collection<? extends Item> collection) {
		super(collection);
	}

	public boolean hasVisibleItems() {
		return stream().anyMatch(item -> item.isVisible());
	}

}
