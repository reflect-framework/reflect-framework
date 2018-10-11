package nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class NotLinkedToPropertyFilter implements Predicate<ActionMethodInfo> {

	private ArrayList<String> propertyNames;

	public NotLinkedToPropertyFilter(ReflectionProvider reflectionProvider, Class<?> domainClass) {
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();
		propertyNames = new ArrayList<String>();
		for (PropertyInfo propertyInfo : propertyInfos) {
			propertyNames.add(propertyInfo.getSimpleName());
		}
	}

	@Override
	public boolean test(ActionMethodInfo actionMethodInfo) {
		if (! actionMethodInfo.hasParameter() || actionMethodInfo.hasParameterFactory()) {
			for (String propertyName : propertyNames) {
				if (actionMethodInfo.getSimpleName().startsWith(propertyName)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

}
