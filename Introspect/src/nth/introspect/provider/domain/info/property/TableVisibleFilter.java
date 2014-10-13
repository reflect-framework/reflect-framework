package nth.introspect.provider.domain.info.property;

import nth.introspect.filter.Filter;

public class TableVisibleFilter implements Filter<PropertyInfo> {


	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable();
	}

}
