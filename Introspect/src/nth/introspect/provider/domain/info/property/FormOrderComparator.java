package nth.introspect.provider.domain.info.property;

import java.util.Comparator;

public class FormOrderComparator implements Comparator<PropertyInfo> {

	public int compare(PropertyInfo propertyInfo1, PropertyInfo propertyInfo2) {
		Integer order1 = propertyInfo1.getOrderInForm();
		Integer order2 = propertyInfo2.getOrderInForm();
		return order1.compareTo(order2);
	}
}
