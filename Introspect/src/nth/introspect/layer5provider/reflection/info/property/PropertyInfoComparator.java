package nth.introspect.layer5provider.reflection.info.property;

import java.util.Comparator;

public class PropertyInfoComparator implements Comparator<PropertyInfo> {

	public int compare(PropertyInfo propertyInfo1, PropertyInfo propertyInfo2) {
		double order1 = propertyInfo1.getOrder();
		double order2 = propertyInfo2.getOrder();
		return Double.compare(order1, order2);
	}
}
