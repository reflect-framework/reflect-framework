package nth.introspect.layer5provider.reflection.info.method.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class LinkedToPropertyFilter implements Filter<MethodInfo> {


	private final String propertyName;

	public LinkedToPropertyFilter(PropertyInfo propertyInfo) {
		propertyName = propertyInfo.getSimpleName();
	}
	
	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		String methodPropertyName = methodInfo.getLinkedPropertyName();
		return propertyName.equals( methodPropertyName);
	}

}
