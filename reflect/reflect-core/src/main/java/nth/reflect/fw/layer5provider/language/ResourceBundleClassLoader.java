package nth.reflect.fw.layer5provider.language;

import java.net.URL;

import nth.reflect.fw.layer5provider.url.application.ApplicationUrl;

public class ResourceBundleClassLoader extends ClassLoader {

	@Override
	protected URL findResource(String fileName) {
		try {
			ApplicationUrl applicationUrl = new ApplicationUrl("", fileName);
			return applicationUrl.toInternalURL();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
