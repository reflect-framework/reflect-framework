package nth.introspect.generic.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated use {@link ClassList}
 * @author nilsth
 *
 */
public class ClassListFactory {

	public static List<Class<?>> create(Class<?> ...classes) {
		List<Class<?>> classList = new ArrayList<>();
		for (Class<?> clazz: classes) {
			classList.add(clazz);	
		}
		return classList;
	}

}
