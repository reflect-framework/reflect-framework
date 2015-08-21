package nth.introspect.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.URISyntaxException;

import javax.swing.KeyStroke;

import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.behavior.icon.IconUriClassResource;
import nth.introspect.ui.images.IntrospectImage;

public class CancelItem extends Item {

	private static final String CANCEL = "Cancel";

	public CancelItem(LanguageProvider languageProvider, final ViewContainer<View> viewContainer,  final View tabToClose ) {
		super(languageProvider);
		setText(CANCEL);
		setDescription(CANCEL);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
		try {
			setIconURI(new IconUriClassResource(IntrospectImage.BUTTON_ROUND_CANCEL).getAbsoluteURI());
		} catch (URISyntaxException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.removeView(tabToClose);
			}
		});
	}
}
