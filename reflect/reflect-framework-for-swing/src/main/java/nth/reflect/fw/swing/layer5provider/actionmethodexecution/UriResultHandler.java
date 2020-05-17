package nth.reflect.fw.swing.layer5provider.actionmethodexecution;

import java.awt.Desktop;
import java.net.URI;

import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUriException;

public class UriResultHandler extends nth.reflect.fw.layer5provider.actionmethod.result.handler.UriResultHandler {

	@Override
	public void openUri(URI uri) {
		try {
			Desktop.getDesktop().browse(uri);
		} catch (Exception exception) {
			throw new CouldNotOpenUriException(uri, exception);
		}
	}

}
