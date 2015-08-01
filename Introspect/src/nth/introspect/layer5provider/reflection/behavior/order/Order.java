package nth.introspect.layer5provider.reflection.behavior.order;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;
/**
 * <p>
 * The order in which the class members ({@link DomainObjectProperty} and
 * {@link ActionMethod}) are displayed is defined with the {@link Order}
 * annotation. Note that the order of class members can not be changed during
 * runtime.
 * </p>
 * <p>
 * The {@link Order} annotation can be added before the getter method of a
 * {@link DomainObjectProperty} or before an {@link ActionMethod}. Both
 * {@link DomainObjectProperty} and {@link ActionMethod}s are ordered separately
 * but use the same annotation.
 * </p>
 * <p>
 * Syntax: @Order(double sequenceNumber)
 * </p>
 * <p>
 * The sequenceNumber of the {@link Order} annotation determines the position of
 * the class member. The class member with the lowest sequenceNumber will be
 * shown first, a higher sequenceNumber later. Class members that are not
 * annotated will be shown last.
 * </p>
 * It is recommended to use an interval (of let's say 10) between the
 * sequenceNumbers so that you do not have to renumber all the existing {@link Order}
 * annotations every time you add a new class member in between. Otherwise you
 * can always fall back on using decimals (e.g. 1.5 or 10.25). </p>
 * 
 * @author nilsth
 *
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Order {
	public double sequenceNumber();
}
