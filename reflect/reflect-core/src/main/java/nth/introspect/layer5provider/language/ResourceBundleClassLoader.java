package nth.introspect.layer5provider.language;

import java.net.URL;

import nth.introspect.layer5provider.url.application.ApplicationUrl;

public class ResourceBundleClassLoader extends ClassLoader {

	
//	private URI configPath;
//
//	public ResourceBundleClassLoader(PathProvider pathProvider) {
//		configPath=pathProvider.getConfigPath();
//	}
	
	@Override
	protected URL findResource(String fileName) {
		try {
			ApplicationUrl applicationUrl=new ApplicationUrl("",fileName);
//			URL url = configPath.resolve(fileName).toURL();
			return applicationUrl.toInternalURL();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
