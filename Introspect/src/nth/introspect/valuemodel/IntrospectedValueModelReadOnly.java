package nth.introspect.valuemodel;

import nth.introspect.provider.domain.format.MethodNotSupportedException;

public abstract class IntrospectedValueModelReadOnly implements ReadOnlyValueModel {

	@Override
	public Object getValue() {
		throw new MethodNotSupportedException("Use getValue(Object introspectedObject) instead.");
	}

	/**
	 * @return result depends on introspectedObject
	 */
	public abstract Object getValue(Object introspectedObject);
}
