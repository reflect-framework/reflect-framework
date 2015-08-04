package nth.introspect.generic.util;

import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.method.MethodInfo;

public class TitleUtil {

	public static String createTitle(ReflectionProvider reflectionProvider, MethodInfo methodInfo, Object methodParameter, boolean shortTile) {
		StringBuffer title = new StringBuffer();
		String methodDisplayName = methodInfo.getDisplayName();
		if (methodDisplayName != null) {
			if (shortTile && methodDisplayName.length() > 20) {
				methodDisplayName = methodDisplayName.substring(0, 17) + "...";
			}
			title.append(methodDisplayName);
		}

		if (methodParameter != null) {
			// add method parameter value between parentheses
			ClassInfo classInfo = reflectionProvider.getClassInfo(methodParameter.getClass());
			String parameterText = classInfo.getTitle(methodParameter);
			// shorten if needed
			if (shortTile && parameterText.length() > 20) {
				parameterText = parameterText.substring(0, 17) + "...";
			}
			// append to title between parentheses
			title.append(" (");
			title.append(parameterText);
			title.append(")");
		}
		return title.toString();

	}
	

}
