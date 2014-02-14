package nth.introspect.provider.domain.info.method;

import java.util.Comparator;

public class MethodInfosOrderComparator implements Comparator<MethodInfo> {

	public int compare(MethodInfo methodInfo1, MethodInfo methodInfo) {
		Integer order1 =  methodInfo1.getOrder();
		Integer order2 = methodInfo.getOrder();
		return order1.compareTo(order2);
	}
}
