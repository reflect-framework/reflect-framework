package nth.introspect.documentation;

import nth.introspect.provider.Provider;
import nth.introspect.util.TitleBuilder;

/**
 * Domain objects have <a
 * href="http://en.wikipedia.org/wiki/State_(computer_science)">state</a>. This
 * means the domain objects contain information that may change over time. This
 * information is stored in <a
 * href="http://en.wikipedia.org/wiki/Property_(programming)">properties</a>.
 * <p>
 * Properties are a special type of class members and are an intermediate
 * between a <a
 * href="http://en.wikipedia.org/wiki/Field_(computer_science)">field</a> and <a
 * href="http://en.wikipedia.org/wiki/Mutator_method">getter and setter
 * methods</a>
 * <p>
 * Here is an example of a domain class customer that has the following
 * properties:
 * <ul>
 * <li>givenName</li>
 * <li>familyName</li>
 * <li>fullName</li>
 * <li>male</li>
 * </ul>
 * Code:
 * </p>
 * 
 * <pre>
 * {@code
 * 	public class Customer {
 * 		private String givenName;
 * 		private String familyName;
 * 		private boolean male;
 * 
 * 		public String getGivenName() {
 * 			return givenName;
 * 		}
 * 
 * 		public void setGivenName(String givenName) {
 * 			this.givenName = givenName;
 * 		}
 * 
 * 		public String getFamilyName() {
 * 			return familyName;
 * 		}
 * 
 * 		public void setFamilyName(String familyName) {
 * 			this.familyName = familyName;
 * 		}
 * 
 * 		public String getFullName() {
 * 			return new TitleBuilder().append(givenName).append(familyName)
 * 					.toString();
 * 		}
 * 
 * 		public boolean isMale() {
 * 			return male;
 * 		}
 * 
 * 		public void setMale(boolean male) {
 * 			this.male = male;
 * 		}
 * 
 * 	}
 * </pre>
 * 
 * Some rules on properties:
 * <h3>Getter methods</h3>
 * <ul>
 * <li>A property always has a getter method, to read its value</li>
 * <li>The getter method name starts with get, followed by the property name in
 * <a href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> when the
 * property type is NOT a boolean (See the getGivenName() method in the example
 * above)</li>
 * <li>The getter method name starts with is, followed by the property name in <a
 * href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> when the property
 * type IS a boolean (See the isMale() method in the example above)</li>
 * <li>The getter methods are always public (accessible by other objects)</li>
 * </ul>
 * <h3>Setter methods</h3>
 * <ul>
 * <li>A property might have a setter method, to set or change its value. The
 * property is read-only when it does not have a setter method</li>
 * <li>The setter method name starts with set, followed by the property name in
 * <a href="http://en.wikipedia.org/wiki/CamelCase">CamelCase</a> (See the
 * setFamilyName() method in the example above)</li>
 * <li>The setter methods are always public (accessible by other objects)</li>
 * <li>The setter methods are commonly placed after the corresponding getter method</li>
 * </ul>
 * </ul><h3>Fields</h3>
 * <ul>
 * <li>
 * Properties can use fields to store the information in the domain object.
 * These fields need to be private (not accessible by other objects) to ensure <a
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
 * TODO
 * 
 * <h3>Property presentation</h3>
 * TODO
 * 
 * @author nilsth
 *
 */
public interface DomainObjectProperty extends Documentation {

}
