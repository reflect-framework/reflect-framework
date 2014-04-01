package nth.introspect.ui.view;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.view.View;

public interface MethodView extends View {

	public Object getMethodOwner();
	
	public MethodInfo getMethodInfo();
	
	public Object getMethodParameter();

	
}
