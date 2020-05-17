package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

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
