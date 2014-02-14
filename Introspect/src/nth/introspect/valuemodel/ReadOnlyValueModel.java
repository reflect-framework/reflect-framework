
package nth.introspect.valuemodel;
public interface ReadOnlyValueModel {

	Object getValue();

	Class<?> getValueType();

	boolean canGetValue();

}
