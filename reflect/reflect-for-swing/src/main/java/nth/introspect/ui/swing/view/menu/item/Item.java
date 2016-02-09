package nth.introspect.ui.swing.view.menu.item;

import java.net.URI;

public interface Item {

	public String getText();
	public String getDescription();
	public URI getIcon();
	public boolean isVisible();
	public boolean isEnabled();
	public void onAction();
}
