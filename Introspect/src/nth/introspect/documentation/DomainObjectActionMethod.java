package nth.introspect.documentation;

/**
 *TODO  An action is a method that is intended to be invoked by a user - though it may also be invoked programmatically from within another method or another object.

For example:
•	An Order class may have an action method addOrderLine(OrderLine orderLine)
•	An OrderService May have an action method findOrdersOfCustommer(Customer customer)

By default, any public instance method that you add to a class (whether it is a domain class or a service) will be treated as a user action, provided that it complies with the following:
•	The method has no or one parameter. If it has a parameter it needs to be of a type that is recognized by the Introspect framework
•	Its return type (if any) are types recognized by the Introspect framework.

A method will also not be treated as an action if it represents a property or another recognized method. Note also that static methods are ignored by the Introspect framework.

If you have a method that you don't want to be made available as a user-action you should either:
•	Give it a non-public (private) access modifier
•	Mark it with the VisibleInForm and/or VisibleInTable attribute with value false
•	Mark it with the Enabled attribute with value false

You can specify certain things about both the behavior and presentation of actions by adding specific attributes or methods. See object behavior

TODO: explain that introspect only accepts methods with one parameter. You can use methods with multiple parameters, but these methods can only be used internally (calling from code) and won’t be visible for the outside world.

 * @author nilsth
 *
 */
public interface DomainObjectActionMethod extends Documentation {

}
