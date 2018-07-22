package nth.reflect.fw.generic.util;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.title.TitleModel;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class TitleUtil {

	// FIXME: move to ActionMethodInfo
	public static String createTitle(ReflectionProvider reflectionProvider,
			ActionMethodInfo actionMethodInfo, Object methodParameter) {
		StringBuffer title = new StringBuffer();
		String methodDisplayName = actionMethodInfo.getDisplayName();
		if (methodDisplayName != null) {
			title.append(methodDisplayName);
		}

		if (methodParameter != null) {
			String parameterText = createMethodParameterTitle(reflectionProvider, methodParameter);
			if (parameterText.length() > 0) {
				// append to title between parentheses
				title.append(" (");
				title.append(parameterText);
				title.append(")");
			}
		}
		return title.toString();

	}

	private static String createMethodParameterTitle(ReflectionProvider reflectionProvider,
			Object methodParameter) {
		TitleModel model = new TitleModel(reflectionProvider);
		String parameterText = model.getTitle(methodParameter);
		return parameterText;
	}

}
