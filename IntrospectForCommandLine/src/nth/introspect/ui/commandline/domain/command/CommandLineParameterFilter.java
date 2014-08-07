package nth.introspect.ui.commandline.domain.command;

import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public class CommandLineParameterFilter implements Filter<PropertyInfo> {

	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable() && !propertyInfo.isReadOnly();
	}

}
