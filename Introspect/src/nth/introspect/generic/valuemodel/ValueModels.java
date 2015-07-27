package nth.introspect.generic.valuemodel;

import java.util.HashMap;


public class ValueModels extends HashMap<String, ReadOnlyValueModel> {

	private static final long serialVersionUID = 6457902740966267153L;

	public Object getValue(String key) {
		return getValue(key, null);
	}
	
	public Object getValue(String key, Object introspectedObject) {
		ReadOnlyValueModel valueModel = get(key);
		if (valueModel == null) {
			//could not find value model so return null
			return null;
		} else if (valueModel instanceof IntrospectedValueModelReadOnly) {
			if (introspectedObject==null) {
				throw new NullPointerException(IntrospectedValueModelReadOnly.class.getSimpleName()+ " requires a service object as a parameter");
			}
			IntrospectedValueModelReadOnly introspectedValueModelReadOnly = (IntrospectedValueModelReadOnly) valueModel;
			return introspectedValueModelReadOnly.getValue(introspectedObject);
		} else if (valueModel instanceof ReadOnlyValueModel) {
			ReadOnlyValueModel readOnlyValueModel = (ReadOnlyValueModel) valueModel;
			return readOnlyValueModel.getValue();
		} else {
			throw new RuntimeException("Unknown ReadWriteValueModel");
		}
	}

	public Boolean getBooleanValue(String key) {
		Object value = getValue(key, null);
		return (value != null && value instanceof Boolean) ? (Boolean) value : null;
	}

	public Boolean getBooleanValue(String key, Object serviceOrDomainObject) {
		Object value = getValue(key, serviceOrDomainObject);
		return (value != null && value instanceof Boolean) ? (Boolean) value : null;
	}

	public String getStringValue(String key) {
		Object value = getValue(key,null);
		return (value != null && value instanceof String) ? (String) value : null;
	}

	public String getStringValue(String key, Object serviceOrDomainObject) {
		Object value = getValue(key, serviceOrDomainObject);
		return (value != null && value instanceof String) ? (String) value : null;
	}

	public Integer getIntegerValue(String key) {
		Object value = getValue(key,null);
		return (value != null && value instanceof Integer) ? (Integer) value : null;
	}

	public Integer getIntegerValue(String key, Object serviceOrDomainObject) {
		Object value = getValue(key, serviceOrDomainObject);
		return (value != null && value instanceof Integer) ? (Integer) value : null;
	}
}
