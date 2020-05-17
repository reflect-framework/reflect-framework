package nth.reflect.fw.vaadin.layer5provider.actionmethodexecution;

import java.net.URI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;

import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUriException;

public class UriResultHandler extends nth.reflect.fw.layer5provider.actionmethod.result.handler.UriResultHandler {

	@Override
	public void openUri(URI uri) {
		try {
			String url = uri.toURL().toString();

			Page page = UI.getCurrent().getPage();

			String javaScript = "window.open( '" + url + "')";
			page.executeJs(javaScript);
		} catch (Exception exception) {
			throw new CouldNotOpenUriException(uri, exception);
		}
	}

}
