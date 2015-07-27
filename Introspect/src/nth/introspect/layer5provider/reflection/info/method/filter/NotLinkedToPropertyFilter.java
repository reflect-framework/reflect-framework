package nth.introspect.layer5provider.reflection.info.method.filter;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

public class NotLinkedToPropertyFilter implements Filter<MethodInfo> {

	private ArrayList<String> propertyNames;

	public NotLinkedToPropertyFilter(ReflectionProvider reflectionProvider, Class<?> domainClass) {
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(domainClass);
		propertyNames = new ArrayList<String>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			propertyNames.add(propertyInfo.getName());
		}
	}

	@Override
	public boolean isMatch(MethodInfo methodInfo) {
		if (TypeCategory.NONE == methodInfo.getParameterType().getTypeCategory() || methodInfo.hasParameterFactory()) {
			for (String propertyName : propertyNames) {
				if (methodInfo.getName().startsWith(propertyName)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
