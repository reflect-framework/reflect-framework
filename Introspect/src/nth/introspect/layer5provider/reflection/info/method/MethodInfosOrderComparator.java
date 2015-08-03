package nth.introspect.layer5provider.reflection.info.method;

import java.util.Comparator;

public class MethodInfosOrderComparator implements Comparator<MethodInfo> {

	public int compare(MethodInfo methodInfo1, MethodInfo methodInfo) {
		double order1 =  methodInfo1.getOrder();
		double order2 = methodInfo.getOrder();
		return Double.compare(order1, order2);
	}
}
