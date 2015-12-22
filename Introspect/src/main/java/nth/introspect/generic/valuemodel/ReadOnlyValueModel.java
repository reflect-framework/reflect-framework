
package nth.introspect.generic.valuemodel;
public interface ReadOnlyValueModel {

	Object getValue();

	Class<?> getValueType();

	boolean canGetValue();

}
