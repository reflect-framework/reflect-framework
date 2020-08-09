package nth.reflect.fw.javafx.layer5provider.actionmethod.result.impl;

import java.net.URI;

import javafx.application.Application;
import javafx.application.HostServices;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.url.exception.CouldNotOpenUriException;

public class UriResultHandler extends nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl.UriResultHandler {

	@Override
	public void openUri(UserInterfaceContainer container, URI uri) {
		try {
			Application application = container.get(Application.class);
			HostServices hostServices = application.getHostServices();
			hostServices.showDocument(uri.toString());
		} catch (Exception exception) {
			throw new CouldNotOpenUriException(uri, exception);
		}
	}

}
