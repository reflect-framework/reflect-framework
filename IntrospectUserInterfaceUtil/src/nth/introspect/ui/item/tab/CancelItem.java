package nth.introspect.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;

public class CancelItem extends Item {

	private static final String CANCEL = "Cancel";

	public CancelItem( final ViewContainer<View> viewContainer,  final View tabToClose ) {
		setText(CANCEL);
		setDescription(CANCEL);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.BUTTON_ROUND_CANCEL));
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.removeView(tabToClose);
			}
		});
	}
}
