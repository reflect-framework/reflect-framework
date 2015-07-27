package nth.introspect.layer5provider.reflection.info;

import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public interface PropertyChangeListener {

	public void propertyChanged(PropertyChangeType propertyChangeType);
	public Object getIntrospectedObject();
	public PropertyInfo getPropertyInfo();
	
}
