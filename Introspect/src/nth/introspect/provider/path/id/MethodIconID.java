package nth.introspect.provider.path.id;

import java.lang.reflect.Method;
import java.net.URI;

import nth.introspect.Introspect;
import nth.introspect.provider.path.PathProvider;

public class MethodIconID extends PathID {

	private static final String SERVICE = "service";
	private static final String PNG_EXTENTION = ".png";
	private URI iconURI;

	public MethodIconID(PathProvider pathProvider, Method method) {
		super(method.getDeclaringClass().getCanonicalName() + "." + method.getName());

		Class<?> declaringClass = method.getDeclaringClass();
		String className = declaringClass.getSimpleName();
		String simpleClassName = className;
		if (className.toLowerCase().endsWith(SERVICE)) {
			simpleClassName = className.substring(0, className.toLowerCase().lastIndexOf(SERVICE));
		}
		String methodName = method.getName();

		iconURI = getIconURI(declaringClass, className + "_" + methodName);

		if (!pathProvider.uriExists(iconURI)) {
			iconURI = getIconURI(declaringClass, simpleClassName + "_" + methodName);
		}

		if (!pathProvider.uriExists(iconURI)) {
			iconURI = getIconURI(declaringClass, methodName);
		}

		if (!pathProvider.uriExists(iconURI)) {
			iconURI = getIconURI(declaringClass, className );
		}

		if (!pathProvider.uriExists(iconURI)) {
			iconURI = getIconURI(declaringClass, simpleClassName);
		}
	}

	private URI getIconURI(Class<?> introspectedClass, String name) {
		try {
			name=name+PNG_EXTENTION;
			return introspectedClass.getResource(name ).toURI();
		} catch (Throwable e) {
			return null;
		}

	}

	@Override
	public URI getPath() {
		return iconURI;
	}

}
