package nth.introspect.layer5provider.reflection.info.property;

import java.util.Comparator;

public class TableOrderComparator implements Comparator<PropertyInfo> {

	public int compare(PropertyInfo propertyInfo1, PropertyInfo propertyInfo2) {
		Integer order1 = propertyInfo1.getOrderInTable();
		Integer order2 = propertyInfo2.getOrderInTable();
		return order1.compareTo(order2);
	}
}
