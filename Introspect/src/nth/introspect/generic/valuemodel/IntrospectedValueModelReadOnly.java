package nth.introspect.generic.valuemodel;

import nth.introspect.generic.exception.MethodNotSupportedException;

public abstract class IntrospectedValueModelReadOnly implements ReadOnlyValueModel {

	@Override
	public Object getValue() {
		throw new MethodNotSupportedException("Use getValue(Object obj) instead.");
	}

	/**
	 * @return result depends on obj
	 */
	public abstract Object getValue(Object obj);
}
