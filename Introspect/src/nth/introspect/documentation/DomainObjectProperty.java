package nth.introspect.documentation;

/**
 * Domain objects have properties. Properties are a special type of class
 * members and are an intermediate between a field and a method. Properties are
 * read and sometimes written.
 * 
 * Introspect properties uses the same naming convention as JavaBeans
 * 
 * TODO get
 * 
 * TODO is
 * 
 * TODO set TODO note that the introspect framework only recognizes public set
 * methods with one parameter with the same type as being used for the getter
 * 
 * TODO read only properties such as composit property TODO note that the
 * introspect framework only recognizes public properties
 * 
 * 
 * Here is an example of a domain class customer that has 2 properties
 * {@code Public class Customer() { 
 * 
 * Private String name; 
 * 
 * Private Boolean male;
 * 
 * Public string getName() { return name; }
 * 
 * Public void setName(String newName) { name=new name }
 * 
 * Public string isMale() { return male; }
 * 
 * Public void setMale(boolean isMale) { male=isMale; } 
 * }
 * 
 * @author nilsth
 *
 */
public interface DomainObjectProperty extends Documentation {

}
