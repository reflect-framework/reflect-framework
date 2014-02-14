package nth.introsepect.ui.swing.item.button;

import nth.introspect.ui.item.Item;

public class ItemButton extends ItemIconButton {

	private static final long serialVersionUID = 978201731374527080L;

	public ItemButton(final Item item) {
		super(item);
		setText(item.getText());
	}
	
	
}
