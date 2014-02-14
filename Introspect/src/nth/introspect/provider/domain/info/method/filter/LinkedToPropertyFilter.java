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
		//return (TypeCategory.NONE== methodInfo.getParameterType().getTypeCategory()  || methodInfo.hasParameterFactory()) && propertyName.equals( methodInfo.getLinkedPropertyName());
		return propertyName.equals( methodInfo.getLinkedPropertyName());
	}

}
