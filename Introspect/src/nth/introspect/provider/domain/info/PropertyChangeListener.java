package nth.introspect.provider.domain.info;

import nth.introspect.provider.domain.info.property.PropertyInfo;

public interface PropertyChangeListener {

	public void propertyChanged(PropertyChangeType propertyChangeType);
	public Object getIntrospectedObject();
	public PropertyInfo getPropertyInfo();
	
}
