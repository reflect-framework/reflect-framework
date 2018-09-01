package nth.reflect.fw.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.KeyStroke;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewController;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

public class CancelItem extends Item {

	private static final String CANCEL = "Cancel";

	public CancelItem(LanguageProvider languageProvider, final ViewController<View> viewContainer,  final View tabToClose ) {
		super(languageProvider);
		setText(CANCEL);
		setDescription(CANCEL);
		try {
			setIconURL(new URL(FontAwesomeUrl.CLOSE ));
		} catch (MalformedURLException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				viewContainer.removeView(tabToClose);
			}
		});
	}
}
