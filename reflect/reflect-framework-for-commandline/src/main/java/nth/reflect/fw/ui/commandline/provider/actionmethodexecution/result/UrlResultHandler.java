package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;

import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUrlException;

public class UrlResultHandler extends nth.reflect.fw.layer5provider.actionmethod.result.handler.UrlResultHandler {

	@Override
	public void openUrl(URL url) {
		try {
			URI uri = url.toURI();
			Desktop.getDesktop().browse(uri);
		} catch (Exception exception) {
			throw new CouldNotOpenUrlException(url, exception);
		}
	}

}
