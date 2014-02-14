package nth.introspect.provider.language;

import java.net.URL;

import nth.introspect.Introspect;

public class ResourceBundleClassLoader extends ClassLoader {

	
	@Override
	protected URL findResource(String fileName) {
		try {
			String path = Introspect.getPathProvider().getConfigPath()+fileName;
			return new URL(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
