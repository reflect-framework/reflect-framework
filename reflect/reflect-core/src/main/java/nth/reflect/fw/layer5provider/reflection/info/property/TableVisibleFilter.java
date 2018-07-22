package nth.reflect.fw.layer5provider.reflection.info.property;

import nth.reflect.fw.generic.filter.Filter;

public class TableVisibleFilter implements Filter<PropertyInfo> {


	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable();
	}

}
