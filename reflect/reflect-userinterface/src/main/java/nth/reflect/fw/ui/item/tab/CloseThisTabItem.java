package nth.reflect.fw.ui.item.tab;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.KeyStroke;

import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer1userinterface.view.ViewContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.style.fontawesome.FontAwesomeUrl;

public class CloseThisTabItem extends Item {

	private static final String CLOSE_THIS_TAB = "Close this tab";

	public CloseThisTabItem(LanguageProvider languageProvider,
			final ViewContainer<View> viewContainer, final View tabToClose) {
		super(languageProvider);
		setText(CLOSE_THIS_TAB);
		setDescription(CLOSE_THIS_TAB);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.CTRL_MASK));
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
