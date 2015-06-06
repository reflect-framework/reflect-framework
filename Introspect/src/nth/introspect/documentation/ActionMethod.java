package nth.introspect.documentation;

import nth.introspect.controller.userinterface.DownloadStream;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.domain.format.DomainObjectFormat;
import nth.introspect.provider.domain.info.valuemodel.annotations.GenericReturnType;

/**
 * An action is a method in a {@link DomainObject} or {@link ServiceObject} is
 * displayed by the {@link UserInterfaceController} as a menu item. An Action method is
 * invoked by the {@link UserInterfaceController} when the user clicks on the
 * menu item. <br>
 * Examples of action methods:
 * <ul>
 * <li>A {@link DomainObject} Order may have an action method addOrderLine(OrderLine
 * orderLine)</li>
 * <li>A {@link ServiceObject} OrderService may have an action method findOrdersOfCustommer(Customer
 * customer) that returns a list of Customers</li>
 * </ul>
 * Any method in a {@link DomainObject} or {@link ServiceObject} can be an
 * Action method, provided that it complies with the following:
 * <ul>
 * <li>The method has no parameter or a single {@link DomainObject} parameter</li>
 * <li>Its return type (if any) are types recognized by the Introspect framework
 * (see below)</li>
 * <li>The method is not a getter method or a setter method (see {@link DomainObjectProperty})</li>
 * <li>The method is public (private methods can not be action methods)</li>
 * <li>The method is NOT static</li>
 * </ul>
 * 
 * <h3>Action method parameter</h3> An action method either has no parameter or
 * a single parameter (must be a {@link DomainObject} type). If not, the
 * Introspect Framework recognized a method as an Action method. <h3>Action
 * method return value</h3> The {@link UserInterfaceController} renders the
 * output of a method, depending on type of the action method return value:
 * <ul>
 * <li>No return value (void method): The {@link UserInterfaceController} will
 * display a short message when the method has been executed</li>
 * <li><a href=
 * "https://docs.oracle.com/javase/tutorial/java/nutsandbolts/datatypes.html">A
 * primitive data type</a>: The {@link UserInterfaceController} will display a
 * message dialog that displays the return value after the method is been
 * executed</li>
 * <li>A {@link DomainObject}: The {@link UserInterfaceController} displays the
 * domain object in a form on a new tab.</li>
 * <li>A <a
 * href="http://en.wikipedia.org/wiki/Java_collections_framework">collection</a>
 * of {@link DomainObject}s: The {@link UserInterfaceController} displays the
 * domain objects in table on a new tab. Note that you will need to annotate the
 * Action method with a @{@link GenericReturnType} annotation in which you need
 * to define the {@link DomainObject} type.</li>
 * <li>A URI: The {@link UserInterfaceController} displays the contents of the
 * URI on a new tab.</li>
 * <li>A {@link DownloadStream}: The {@link UserInterfaceController} will open a
 * "Save as" dialog" so that the file can be down loaded.</li>
 * </ul>
 * 
 * <h3>Action behavior</h3> You can specify certain things about both the
 * behavior and presentation of actions methods, such as their display name,
 * icon, visibility, enabled state, etc with help of annotations or behavior
 * methods. See chapter on {@link ObjectBehavior}.
 * 
 * <h3>Action execution modes</h3> TODO
 * 
 * TODO: Domain objects and Action Methods on properties (to manipulate properties of type domainobject or collections) e.g. customer.moveToNewAddress(Address newAddress) or shoppingCart.addLineItem(LineItem item) 
 * TODO: Service objects and Action Methods: customerService.findCustomer(SearchCriteria), productSalesService.bestSellingProducts()   
See CQRS https://msdn.microsoft.com/en-us/library/ms978509.aspx   or   https://msdn.microsoft.com/en-us/library/ms954638.aspx)
 * 
 * @author nilsth
 *
 */
public interface ActionMethod extends Documentation {

}
