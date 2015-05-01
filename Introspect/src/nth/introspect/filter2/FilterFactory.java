package nth.introspect.filter2;

import java.util.function.Predicate;

import nth.introspect.provider.domain.info.property.PropertyInfo;

public class FilterFactory {
	public static Filter2<?> heaving(final PropertyInfo propertyInfo,
			final Predicate<Object> filterForPropertyValue) {
		return new Filter2<Object>() {

			@Override
			public boolean test(Object domainObject) {
				Object propertyValue = propertyInfo.getValue(domainObject);
				return filterForPropertyValue.test(propertyValue);
			}
		};
	}
	
	
}
