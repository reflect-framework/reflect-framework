package nth.introspect.layer5provider.reflection.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class LinkedToPropertyFilter implements Filter<ActionMethodInfo> {


	private final String propertyName;

	public LinkedToPropertyFilter(PropertyInfo propertyInfo) {
		propertyName = propertyInfo.getSimpleName();
	}
	
	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		String methodPropertyName = actionMethodInfo.getLinkedPropertyName();
		return propertyName.equals( methodPropertyName);
	}

}
