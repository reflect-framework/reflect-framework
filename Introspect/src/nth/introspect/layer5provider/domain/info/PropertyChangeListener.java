package nth.introspect.layer5provider.domain.info;

import nth.introspect.layer5provider.domain.info.property.PropertyInfo;

public interface PropertyChangeListener {

	public void propertyChanged(PropertyChangeType propertyChangeType);
	public Object getIntrospectedObject();
	public PropertyInfo getPropertyInfo();
	
}
