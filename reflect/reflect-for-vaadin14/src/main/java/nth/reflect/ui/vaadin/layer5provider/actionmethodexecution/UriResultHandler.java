package nth.reflect.ui.vaadin.layer5provider.actionmethodexecution;

import java.net.URI;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;

import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUriException;

public class UriResultHandler extends nth.reflect.fw.layer5provider.actionmethodexecution.result.UriResultHandler {

	private static final String OPEN_IN_NEW_BROWSER_OR_TAB = "_blank";

	@Override
	public void openUri(URI uri) {
		try {
			String url = uri.toURL().toString();

			Page page = UI.getCurrent().getPage();

			String javaScript = "window.open( '" + url + "' ," + OPEN_IN_NEW_BROWSER_OR_TAB + ")";
			page.executeJs(javaScript);
		} catch (Exception exception) {
			throw new CouldNotOpenUriException(uri, exception);
		}
	}

}
