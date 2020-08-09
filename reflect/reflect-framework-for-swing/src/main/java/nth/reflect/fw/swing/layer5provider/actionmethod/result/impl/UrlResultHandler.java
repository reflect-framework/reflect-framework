package nth.reflect.fw.swing.layer5provider.actionmethod.result.impl;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUrlException;

public class UrlResultHandler extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler {

	@Override
	public void openUrl(UserInterfaceContainer container, URL url) {
		try {
			URI uri = url.toURI();
			Desktop.getDesktop().browse(uri);
		} catch (Exception exception) {
			throw new CouldNotOpenUrlException(url, exception);
		}
	}

}
