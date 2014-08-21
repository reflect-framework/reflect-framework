package nth.introspect.util;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;

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
	public static Object clone(Object sourceObject, Object destinationObject) {
		Class<?> sourceClass = sourceObject.getClass();
		Class<?> destinationClass = destinationObject.getClass();
		DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
		List<PropertyInfo> propertyInfos = domainInfoProvider.getPropertyInfos(sourceClass);
		for (PropertyInfo sourcePropertyInfo : propertyInfos) {
			PropertyInfo destinationPropertyInfo = domainInfoProvider.getPropertyInfo(destinationClass, sourcePropertyInfo.getName());
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
	 * @return the instantiated with the property values of the sourceObject
	 */

	public static Object clone(Object sourceObject) {
		Class<?> sourceClass = sourceObject.getClass();
		try {
			Object destinationObject = sourceClass.newInstance();
			return clone(sourceObject, destinationObject);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
