package nth.introspect.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.userinterface.item.Item;
import nth.introspect.layer5provider.userinterface.view.View;
import nth.introspect.layer5provider.userinterface.view.ViewContainer;
import nth.introspect.ui.images.IntrospectImage;

public class CancelItem extends Item {

	private static final String CANCEL = "Cancel";

	public CancelItem(PathProvider pathProvider, LanguageProvider languageProvider, final ViewContainer<View> viewContainer,  final View tabToClose ) {
		super(languageProvider);
		setText(CANCEL);
		setDescription(CANCEL);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		setIconURI(pathProvider.getImagePath(IntrospectImage.BUTTON_ROUND_CANCEL));
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.removeView(tabToClose);
			}
		});
	}
}
