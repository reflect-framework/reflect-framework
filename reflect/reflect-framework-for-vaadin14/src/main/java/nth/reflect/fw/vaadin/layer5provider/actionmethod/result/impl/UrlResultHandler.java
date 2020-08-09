package nth.reflect.fw.vaadin.layer5provider.actionmethod.result.impl;

import java.net.URL;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.Page;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUrlException;

public class UrlResultHandler extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler {

	private static final String OPEN_IN_NEW_BROWSER_OR_TAB = "_blank";

	@Override
	public void openUrl(UserInterfaceContainer container, URL url) {
		try {
			Page page = UI.getCurrent().getPage();

			String javaScript = "window.open( '" + url.toString() + "' ," + OPEN_IN_NEW_BROWSER_OR_TAB + ")";
			page.executeJs(javaScript);
		} catch (Exception exception) {
			throw new CouldNotOpenUrlException(url, exception);
		}
	}

}
