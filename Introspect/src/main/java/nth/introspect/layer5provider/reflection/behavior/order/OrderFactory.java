package nth.introspect.layer5provider.reflection.behavior.order;

import java.lang.reflect.Method;

/**
 * Gets the sequanceNumber from the {@link Order} annotation. Methods that do
 * not have a {@link Order} annotation need to be sorted last (so have a high
 * order number)
 * 
 * @author nilsth
 *
 */
public class OrderFactory {
	//
	private static final double ORDER_DEFAULT_VALUE = Double.MAX_VALUE;

	public static double create(Method method) {
		Order order = method.getAnnotation(Order.class);
		if (order == null) {
			return ORDER_DEFAULT_VALUE;
		} else {
			return order.sequenceNumber();
		}
	}

}
