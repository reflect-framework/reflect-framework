package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CommandLineParameterFilter implements Filter<PropertyInfo> {

	@Override
	public boolean isMatch(PropertyInfo propertyInfo) {
		return propertyInfo.isVisibleInTable() && !propertyInfo.isReadOnly();
	}

}
