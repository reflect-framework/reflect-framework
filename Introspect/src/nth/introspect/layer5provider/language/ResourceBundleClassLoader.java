package nth.introspect.layer5provider.language;

import java.net.URI;
import java.net.URL;

import nth.introspect.layer5provider.path.PathProvider;

public class ResourceBundleClassLoader extends ClassLoader {

	
	private URI configPath;

	public ResourceBundleClassLoader(PathProvider pathProvider) {
		configPath=pathProvider.getConfigPath();
	}
	
	@Override
	protected URL findResource(String fileName) {
		try {
			String path = configPath+fileName;
			return new URL(path);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
