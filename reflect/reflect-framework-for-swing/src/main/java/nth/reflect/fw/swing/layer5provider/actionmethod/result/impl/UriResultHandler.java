package nth.reflect.fw.swing.layer5provider.actionmethod.result.impl;

import java.awt.Desktop;
import java.net.URI;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUriException;

public class UriResultHandler extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler {

	@Override
	public void openUri(UserInterfaceContainer container, URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (Exception exception) {
			throw new CouldNotOpenUriException(uri, exception);
		}
	}

}
