package nth.introspect.generic.util;

import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.title.TitleModel;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

public class TitleUtil {

	//FIXME: move to ActionMethodInfo
	public static String createTitle(ReflectionProvider reflectionProvider,
			ActionMethodInfo actionMethodInfo, Object methodParameter,
			boolean shortTile) {
		StringBuffer title = new StringBuffer();
		String methodDisplayName = actionMethodInfo.getDisplayName();
		if (methodDisplayName != null) {
			if (shortTile && methodDisplayName.length() > 20) {
				methodDisplayName = methodDisplayName.substring(0, 17) + "...";
			}
			title.append(methodDisplayName);
		}

		if (methodParameter != null) {
			String parameterText = createMethodParameterTitle(reflectionProvider, methodParameter,
					shortTile);
			// append to title between parentheses
			title.append(" (");
			title.append(parameterText);
			title.append(")");
		}
		return title.toString();

	}

	private static String createMethodParameterTitle(ReflectionProvider reflectionProvider,
			Object methodParameter, boolean shortTile) {
		TitleModel model=new TitleModel(reflectionProvider);
		String parameterText =model.getTitle(methodParameter);
		// shorten if needed
		if (shortTile && parameterText.length() > 20) {
			parameterText = parameterText.substring(0, 17) + "...";
		}
		return parameterText;
	}


}
