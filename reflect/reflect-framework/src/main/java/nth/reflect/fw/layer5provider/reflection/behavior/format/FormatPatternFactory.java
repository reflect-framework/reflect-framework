package nth.reflect.fw.layer5provider.reflection.behavior.format;

import java.lang.reflect.Method;

public class FormatPatternFactory {
	public static String create(Method getterMethod) {
		Format format = getterMethod.getAnnotation(Format.class);
		if (format == null) {
			return null;
		} else {
			return format.pattern();
		}
	}
}
