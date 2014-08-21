package nth.introspect.util;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.classinfo.ClassInfo;
import nth.introspect.provider.domain.info.method.MethodInfo;

public class TitleUtil {

	public static String createTitle(MethodInfo methodInfo, Object methodParameter, boolean shortTile) {
		StringBuffer title = new StringBuffer();
		String methodText = methodInfo.getText();
		if (methodText != null) {
			if (shortTile && methodText.length() > 20) {
				methodText = methodText.substring(0, 17) + "...";
			}
			title.append(methodText);
		}

		if (methodParameter != null) {
			// add method parameter value between parentheses
			DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
			ClassInfo classInfo = domainInfoProvider.getClassInfo(methodParameter.getClass());
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
