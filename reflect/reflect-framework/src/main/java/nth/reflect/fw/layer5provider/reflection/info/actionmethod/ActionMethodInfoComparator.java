package nth.reflect.fw.layer5provider.reflection.info.actionmethod;

import java.util.Comparator;

public class ActionMethodInfoComparator implements Comparator<ActionMethodInfo> {

	public int compare(ActionMethodInfo methodInfo1, ActionMethodInfo actionMethodInfo) {
		double order1 =  methodInfo1.getOrder();
		double order2 = actionMethodInfo.getOrder();
		return Double.compare(order1, order2);
	}
}
