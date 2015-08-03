package nth.introspect.layer5provider.reflection.behavior.order;

import java.lang.reflect.Method;

import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;

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

	public static double getOrderSequenceNumber(Method method) {
		Order order = method.getAnnotation(Order.class);
		if (order == null) {
			return ORDER_DEFAULT_VALUE;
		} else {
			return order.sequenceNumber();
		}
	}

}
