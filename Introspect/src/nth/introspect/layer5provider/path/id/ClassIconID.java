package nth.introspect.layer5provider.path.id;

import java.net.URI;
import java.net.URL;

import nth.introspect.layer5provider.path.PathProvider;


public class ClassIconID extends PathID {

	private static final String SERVICE = "service";
	private static final String PNG_EXTENTION = ".png";
	private URI iconURI;
	
	public ClassIconID(PathProvider pathProvider, Class<?> declaringClass) {
		super(declaringClass.getCanonicalName());
		String className = declaringClass.getSimpleName();
		iconURI =getIconURI(declaringClass, className);
		
		if (!pathProvider.uriExists(iconURI) && className.toLowerCase().endsWith(SERVICE)) {
			String	simpleClassName  = className .substring(0, className.toLowerCase().lastIndexOf(SERVICE));
			iconURI =getIconURI(declaringClass, simpleClassName);
		}
	}

	private URI getIconURI(Class<?> introspectedClass, String name ) {
		try {
			String fileName=name+PNG_EXTENTION;
			URL resource = introspectedClass.getResource(fileName);
			return resource.toURI();
		} catch (Throwable  e) {
			return null;
		}

	}

	@Override
	public URI getPath() {
		return iconURI;
	}

}
