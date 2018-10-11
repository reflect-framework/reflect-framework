package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.function.Predicate;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class LinkedToPropertyFilter implements Predicate<ActionMethodInfo> {


	private final String propertyName;

	public LinkedToPropertyFilter(PropertyInfo propertyInfo) {
		propertyName = StringUtil.firstCharToUpperCase(propertyInfo.getSimpleName());
	}
	
	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		String linkedPropertyName = actionMethodInfo.getLinkedPropertyName();
		return  propertyName.equals(linkedPropertyName);
	}

}
