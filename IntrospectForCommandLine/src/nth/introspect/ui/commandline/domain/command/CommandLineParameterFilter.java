package nth.introspect.ui.commandline.domain.command;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.domain.info.property.PropertyInfo;

public class CommandLineParameterFilter implements Filter<PropertyInfo> {

	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable() && !propertyInfo.isReadOnly();
	}

}
