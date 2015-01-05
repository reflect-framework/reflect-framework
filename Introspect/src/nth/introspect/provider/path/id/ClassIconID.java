package nth.introspect.provider.path.id;

import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.path.PathProvider;


public class ClassIconID extends PathID {

	private static final String SERVICE = "service";
	private static final String PNG_EXTENTION = ".png";
	private URI iconURI;
	
	public ClassIconID(PathProvider pathProvider, Class<?> declaringClass) {
		super(declaringClass.getCanonicalName());
		String className = declaringClass.getName();
		iconURI =getIconURI(declaringClass, className);
		
		if (!pathProvider.uriExists(iconURI) && className.toLowerCase().endsWith(SERVICE)) {
			String	simpleClassName  = className .substring(0, className.toLowerCase().lastIndexOf(SERVICE));
			iconURI =getIconURI(declaringClass, simpleClassName);
		}
	}

	private URI getIconURI(Class<?> introspectedClass, String name ) {
		try {
			return introspectedClass.getResource(name+PNG_EXTENTION).toURI();
		} catch (Throwable  e) {
			return null;
		}

	}

	@Override
	public URI getPath() {
		return iconURI;
	}

}
