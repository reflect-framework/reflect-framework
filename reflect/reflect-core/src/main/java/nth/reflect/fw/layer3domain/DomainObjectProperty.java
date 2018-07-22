package nth.reflect.fw.layer3domain;

import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer4infrastructure.InfrastructureObject;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.behavior.ObjectBehavior;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * {@link DomainObject}s have <a
 * href="http://en.wikipedia.org/wiki/State_(computer_science)">state</a>. This
 * means the domain objects contain information that may change over time. This
 * information is represented by <a
 * href="http://en.wikipedia.org/wiki/Property_(programming)">properties</a>.
 * </p> Here is an example of a domain class customer that has the following
 * properties:
 * <ul>
 * <li>givenName</li>
 * <li>familyName</li>
 * <li>fullName</li>
 * <li>bonusMember</li>
 * </ul>
 * 
 * 
 * <pre>
 * public class Customer {
 * 	private String givenName;
 * 	private String familyName;
 * 	private boolean bonusMember;
 * 
 * 	public String getGivenName() {
 * 		return givenName;
 * 	}
 * 
 * 	public void setGivenName(String givenName) {
 * 		this.givenName = givenName;
 * 	}
 * 
 * 	public String getFamilyName() {
 * 		return familyName;
 * 	}
 * 
 * 	public void setFamilyName(String familyName) {
 * 		this.familyName = familyName;
 * 	}
 * 
 * 	public String getFullName() {
 * 		return new TitleBuilder().append(givenName).append(familyName)
 * 				.toString();
 * 	}
 * 
 * 	public boolean isBonusMember() {
 * 		return bonusMember;
 * 	}
 * 
 * 	public void setBonusMember(boolean bonusMember) {
 * 		this.bonusMember = bonusMember;
 * 	}
 * }
 * </pre>
 * 
 * <p>
 * Properties are a special type of class members and are an intermediate
 * between <a href="http://en.wikipedia.org/wiki/Mutator_method">getter and
 * setter methods</a> and a <a
 * href="http://en.wikipedia.org/wiki/Field_(computer_science)">field</a>. The
 * following 3 sections will explain this in more detail.
 * <p>
 * 
 * <h3>Getter methods</h3>
 * <ul>
 * <li>A property always has a getter method, so that other objects can read its
 * value</li>
 * <li>The getter method name starts with get, followed by the property name in
 * <a href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> when the
 * property type is NOT a boolean (See the getGivenName() method in the example
 * above)</li>
 * <li>The getter method name starts with is, followed by the property name in
 * <a href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> when the
 * property type IS a boolean (See the isBonusMember() method in the example
 * above)</li>
 * <li>The getter methods are always public (accessible by other objects)</li>
 * </ul>
 * <h3>Setter methods</h3>
 * <ul>
 * <li>A property might have a setter method, so that other objects can change
 * its value. The property is read-only when it does not have a setter method</li>
 * <li>The setter method name starts with set, followed by the property name in
 * <a href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> (See the
 * setFamilyName() method in the example above)</li>
 * <li>The setter methods are always public (accessible by other objects)</li>
 * <li>The setter methods are commonly placed after the corresponding getter
 * method</li>
 * </ul>
 * <h3>Fields</h3>
 * <ul>
 * <li>Properties can use <a
 * href="http://en.wikipedia.org/wiki/Field_%28computer_science%29">fields</a>
 * to hold information in the domain object. These fields need to be private
 * (not accessible by other objects) to ensure <a
 * href="http://en.wikipedia.org/wiki/Encapsulation_(computer_science)">
 * encapsulation</a> (See private fields givenName, familyName and male in the
 * example above)</li>
 * <li>A property does not need to have a field. The value can be a computation
 * of fields of other properties (such as the fullName property in the example
 * above), or maybe even a value from an {@link InfrastructureObject} or a
 * {@link Provider}.</li>
 * <li>Fields are commonly defined at the beginning of the class</li>
 * </ul>
 * 
 * <h3>Property types</h3>
 * Property types can be divided into 3 groups:
 * <ul>
 * <li>Value property: A char, string, boolean, number, date, or other <a href=
 * "http://en.wikipedia.org/wiki/Primitive_data_type#Specific_primitive_data_types"
 * >primitive data types</a>. The {@link UserInterfaceController} will display
 * these types as a <a href="http://en.wikipedia.org/wiki/Text_box">textbox</a>.
 * Assuming that the user is allowed to modify that property, they may enter the
 * value by typing in text, which will be validated and formatted according to
 * the value type. Certainly value types may provide alternative mechanisms for
 * user input, such as a calendar-selector for a date field.</li>
 * <li>Enumeration property: The {@link UserInterfaceController} will display
 * these types as as a <a
 * href="http://en.wikipedia.org/wiki/Combo_box">combo-box</a>, containing the
 * different enumeration values as text.</li>
 * <li>Reference Property: A 'reference property' or 'entity' is one where the
 * type is another {@link DomainObject}. Reference properties are thus sometimes
 * referred to as 'associations'. The {@link UserInterfaceController} will
 * display a {@link DomainObject} property as a <a
 * href="http://en.wikipedia.org/wiki/Text_box">textbox</a>, containing the
 * referenced object (as an icon and title) and a menu button. The menu button
 * will open a <a href="http://en.wikipedia.org/wiki/Context_menu">context
 * menu</a>, that contains {@link ActionMethod}s to manipulate the property
 * value.</li>
 * <li>Collection Property: A 'collection property' is a property that returns
 * an collection of either value properties (<a href=
 * "http://en.wikipedia.org/wiki/Primitive_data_type#Specific_primitive_data_types"
 * >primitive data type</a>) or reference properties ({@link DomainObject}s).
 * The {@link UserInterfaceController} will display these types as a table. If
 * the user clicks on a row a <a
 * href="http://en.wikipedia.org/wiki/Context_menu">context menu</a> opens,
 * containing {@link ActionMethod}s to manipulate the property value.
 * Collections must be initialized (collection properties should never return
 * null). Collection properties should not contain large collections. If you
 * have a big collection associated with a {@link DomainObject} it is better to
 * return the collection from an {@link ActionMethod} in the
 * {@link DomainObject}, so that the table is displayed on a new tab.</li>
 * </ul>
 * 
 * <h3>Property behavior</h3>
 * You can specify certain things about both the behavior and presentation of
 * properties by adding specific attributes or methods. See section
 * {@link ObjectBehavior}.
 *
 */
public interface DomainObjectProperty extends ReflectDocumentationInterface {

}
