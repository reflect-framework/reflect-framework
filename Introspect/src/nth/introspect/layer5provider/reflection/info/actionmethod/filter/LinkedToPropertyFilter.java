package nth.introspect.layer5provider.reflection.info.actionmethod.filter;

import nth.introspect.generic.filter.Filter;
import nth.introspect.generic.util.StringUtil;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;

public class LinkedToPropertyFilter implements Filter<ActionMethodInfo> {


	private final String propertyName;

	public LinkedToPropertyFilter(PropertyInfo propertyInfo) {
		propertyName = StringUtil.firstCharToUpperCase(propertyInfo.getSimpleName());
	}
	
	@Override
	public boolean isMatch(ActionMethodInfo actionMethodInfo) {
		String linkedPropertyName = actionMethodInfo.getLinkedPropertyName();
		return  propertyName.equals(linkedPropertyName);
	}

}
