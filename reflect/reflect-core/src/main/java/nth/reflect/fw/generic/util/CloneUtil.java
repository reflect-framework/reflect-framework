package nth.reflect.fw.generic.util;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.container.InstanceFactory;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CloneUtil {

	/**
	 * Copies the values of all properties to an other object. NOTE this is not a deep copy!
	 * 
	 * @param sourceObject
	 *            object that holds the property values to be copied to the destinationObject
	 * @param destinationObject
	 *            object that will receive the property values of the sourceObject
	 * @return the destinationObject with the property values of the sourceObject
	 */
	public static Object clone(ReflectionProvider reflectionProvider, Object sourceObject, Object destinationObject) {
		Class<?> sourceClass = sourceObject.getClass();
		ClassInfo classInfo = reflectionProvider.getClassInfo(sourceClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		for (PropertyInfo sourcePropertyInfo : propertyInfos) {
			PropertyInfo destinationPropertyInfo = classInfo.getPropertyInfo(sourcePropertyInfo.getSimpleName());
			if (destinationPropertyInfo.isEnabled(destinationObject)) {
				Object value = sourcePropertyInfo.getValue(sourceObject);
				destinationPropertyInfo.setValue(destinationObject, value);
			}
		}
		return destinationObject;
	}

	/**
	 * Creates a new instance of the source object and copies the values of the source object to the instantiated object.<br>
	 * NOTE this is not a deep copy!
	 * 
	 * @param sourceObject
	 *            object to be instantiated and copied
	 * @param reflectionProvider 
	 * @return the instantiated with the property values of the sourceObject
	 */

	public static Object clone(DependencyInjectionContainer container,  ReflectionProvider reflectionProvider, Object sourceObject) {
		Class<?> sourceClass = sourceObject.getClass();
		try {
			InstanceFactory instanceFactory=new InstanceFactory(sourceClass, container);
			List<Class<?>> classesWaitingToBeInstantiated=new ArrayList<Class<?>>();
			Object destinationObject = instanceFactory.createInstance(classesWaitingToBeInstantiated);
			return clone(reflectionProvider, sourceObject, destinationObject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
