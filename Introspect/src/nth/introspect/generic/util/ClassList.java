package nth.introspect.generic.util;

import java.util.ArrayList;

public class ClassList extends ArrayList<Class<?>> {

	private static final long serialVersionUID = 9016197215118606080L;

	public ClassList(Class<?> ...classes) {
		for (Class<?> clazz : classes) {
			add(clazz);
		}
	}
	
}
