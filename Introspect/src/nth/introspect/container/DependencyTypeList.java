package nth.introspect.container;

import java.util.ArrayList;
import java.util.Set;

public class DependencyTypeList extends ArrayList<Class<?>> {

	private static final long serialVersionUID = 3039699067340695883L;

	public boolean containsParent(Class<?> classToFind) {
		Class<?> foundClass = getNearestParent( classToFind);
		return foundClass != null;
	}

	public Class<?> getNearestParent(Class<?> classToFind) {
		if (contains(classToFind)) {
			return classToFind;
		}

		Class<?> nearestRelative = null;
		int generationsOfNearestRelative = Integer.MAX_VALUE;
		for (Class<?> currentClass : this) {
			int generations = getNumberOfGenerationsInbetween(classToFind,
					currentClass);
			if (generations < generationsOfNearestRelative) {
				nearestRelative = currentClass;
				generationsOfNearestRelative = generations;
			}
		}
		
		//TODO check if there is no peer relative. If so throw exception (framework does not know which one to choose)
		
		return nearestRelative;
	}

	private int getNumberOfGenerationsInbetween(Class<?> parent, Class<?> child) {
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

	public void removeParent(Class<?> type) {
		Class<?> nearestParent = getNearestParent(type);
		remove(nearestParent);
	}

	public void removeAllParents(Set<Class<?>> parents) {
		for (Class<?> parent : parents) {
			removeParent(parent);
		}
	}

}
