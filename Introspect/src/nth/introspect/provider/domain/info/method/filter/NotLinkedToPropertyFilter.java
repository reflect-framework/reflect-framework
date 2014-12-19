package nth.introspect.provider.domain.info.method.filter;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.type.TypeCategory;

public class NotLinkedToPropertyFilter implements Filter<MethodInfo> {

	private ArrayList<String> propertyNames;

	public NotLinkedToPropertyFilter(DomainInfoProvider domainInfoProvider, Class<?> domainClass) {
		List<PropertyInfo> propertyInfos = domainInfoProvider.getPropertyInfos(domainClass);
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
