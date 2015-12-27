package nth.introspect.layer5provider.reflection.info.property;

import nth.introspect.generic.filter.Filter;

public class TableVisibleFilter implements Filter<PropertyInfo> {


	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable();
	}

}
