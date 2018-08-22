package nth.reflect.fw.ui.swing.view.menu.item;

import java.net.URL;


/**
 * @deprecated use {@link nth.reflect.fw.layer1userinterface.item.Item}
 * @author nilsth
 *
 */
public interface Item {

	public String getText();
	public String getDescription();
	public URL getIcon();
	public boolean isVisible();
	public boolean isEnabled();
	public void onAction();
}