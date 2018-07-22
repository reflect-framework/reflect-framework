package nth.reflect.fw.generic.valuemodel;

public interface ReadWriteValueModel extends ReadOnlyValueModel{

	void setValue(Object value);

	boolean canSetValue();

}
