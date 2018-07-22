package nth.reflect.fw.container;

import java.util.Collection;

public class NearestParentFinder {
	public  static  Class<?> findParent(Collection<Class<?>> classes, Class<?> classToFind) {
		if (classes.contains(classToFind)) {
			return classToFind;
		}

		Class<?> nearestRelative = null;
		int generationsOfNearestRelative = Integer.MAX_VALUE;
		for (Class<?> aClass : classes) {
			int generations = getNumberOfGenerationsInbetween(classToFind,
					aClass);
			if (generations < generationsOfNearestRelative) {
				nearestRelative = aClass;
				generationsOfNearestRelative = generations;
			}
		}
		
		//TODO check if there is no peer relative. If so throw exception (framework does not know which one to choose)
		
		return nearestRelative;
	}

	private static int getNumberOfGenerationsInbetween(Class<?> parent, Class<?> child) {
		if (parent.isAssignableFrom(child)) {
			int numberOfGenerationsInBetween = 0;
			Class<?> current = child;

			if (parent.isInterface()) {
				// find highest super class that implements interface
				while (parent.isAssignableFrom(current)) {
					current = current.getSuperclass();
					numberOfGenerationsInBetween++;
				}
			} else {
				// find highest superclass that compares with parent type
				while (!current.equals(parent)) {
					current = current.getSuperclass();
					numberOfGenerationsInBetween++;
				}
			}
			return numberOfGenerationsInBetween;
		} else {
			//not a relative
			return Integer.MAX_VALUE;
		}
	}
	
	public static boolean containsParent(Collection<Class<?>> classes,Class<?> classToFind) {
		Class<?> foundClass = findParent(classes, classToFind);
		return foundClass != null;
	}
	
}
