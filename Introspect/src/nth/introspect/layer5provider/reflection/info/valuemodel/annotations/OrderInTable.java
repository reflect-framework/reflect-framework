package nth.introspect.layer5provider.reflection.info.valuemodel.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.layer5provider.reflection.behavior.order.Order;

/**
 * @deprecated: user {@link Order} instead
 * @author nilsth
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OrderInTable {
	public int value();
}
