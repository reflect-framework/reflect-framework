package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import nth.reflect.fw.generic.filter.Filter;
import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

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
