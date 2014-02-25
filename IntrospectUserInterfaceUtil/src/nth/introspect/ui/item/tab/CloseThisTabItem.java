package nth.introspect.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;

public class CloseThisTabItem extends Item {

	private static final String CLOSE_THIS_TAB = "Close this tab";

	public CloseThisTabItem( final View tabToClose ) {
		
		setText(CLOSE_THIS_TAB);
		setDescription(CLOSE_THIS_TAB);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.TABS_CLOSE_THIS));
		setAction(new Action() {
			@Override
			public void run() {
				ViewContainer<View> viewContainer = Introspect.getUserInterfaceProvider().getViewContainer();
				viewContainer.removeView(tabToClose);
			}
		});
	}
}
