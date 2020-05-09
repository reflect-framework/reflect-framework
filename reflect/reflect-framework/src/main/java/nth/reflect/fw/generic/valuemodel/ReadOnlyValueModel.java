
package nth.reflect.fw.generic.valuemodel;
public interface ReadOnlyValueModel {

	Object getValue();

	Class<?> getValueType();

	boolean canGetValue();

}
