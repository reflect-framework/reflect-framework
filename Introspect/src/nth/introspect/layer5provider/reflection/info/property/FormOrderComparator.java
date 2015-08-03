package nth.introspect.layer5provider.reflection.info.property;

import java.util.Comparator;

public class FormOrderComparator implements Comparator<PropertyInfo> {

	public int compare(PropertyInfo propertyInfo1, PropertyInfo propertyInfo2) {
		double order1 = propertyInfo1.getOrderInForm();
		double order2 = propertyInfo2.getOrderInForm();
		return Double.compare(order1, order2);
		//TODO merge getOrderInForm getOrderInTable into getOrder
	}
}
