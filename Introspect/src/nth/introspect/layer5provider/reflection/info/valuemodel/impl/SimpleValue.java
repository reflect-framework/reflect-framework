package nth.introspect.layer5provider.reflection.info.valuemodel.impl;

import nth.introspect.generic.valuemodel.ReadOnlyValueModel;

public class SimpleValue implements ReadOnlyValueModel {
	private final Object value;

	public SimpleValue(Object value) {
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		return value.toString();
	}

	@Override
	public Class<?> getValueType() {
		return value.getClass();
	}

	@Override
	public boolean canGetValue() {
		return true;
	}
}
