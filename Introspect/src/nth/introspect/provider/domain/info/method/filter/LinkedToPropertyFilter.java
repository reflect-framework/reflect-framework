package nth.introspect.provider.domain.info.method.filter;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public class LinkedToPropertyFilter implements Filter<MethodInfo> {


	private final String propertyName;

	public LinkedToPropertyFilter(PropertyInfo propertyInfo) {
		propertyName = propertyInfo.getName();
	}
	
	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		String methodPropertyName = methodInfo.getLinkedPropertyName();
		return propertyName.equals( methodPropertyName);
	}

}
