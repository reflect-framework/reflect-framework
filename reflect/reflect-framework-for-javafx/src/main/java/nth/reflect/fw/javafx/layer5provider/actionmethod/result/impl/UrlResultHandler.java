package nth.reflect.fw.javafx.layer5provider.actionmethod.result.impl;

import java.net.URI;
import java.net.URL;

import javafx.application.Application;
import javafx.application.HostServices;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUrlException;

public class UrlResultHandler extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UrlResultHandler {

	@Override
	public void openUrl(UserInterfaceContainer container, URL url) {
		try {
			Application application = container.get(Application.class);
			HostServices hostServices = application.getHostServices();
			URI uri = url.toURI();
			hostServices.showDocument(uri.toString());
		} catch (Exception exception) {
			throw new CouldNotOpenUrlException(url, exception);
		}
	}

}
