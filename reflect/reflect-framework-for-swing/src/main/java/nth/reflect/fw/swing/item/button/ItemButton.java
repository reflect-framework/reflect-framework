package nth.reflect.fw.swing.item.button;

import java.awt.Dimension;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.swing.style.SwingStyleConstant;

public class ItemButton extends ItemIconButton {

	private static final long serialVersionUID = 978201731374527080L;

	public ItemButton(final Item item) {
		super(item);
		setText(item.getText());
		setPreferredSize(new Dimension(getPreferredSize().width, SwingStyleConstant.TEXT_BUTTON_HEIGHT));
	}
	
	
}
