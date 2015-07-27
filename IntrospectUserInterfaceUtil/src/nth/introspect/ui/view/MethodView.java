package nth.introspect.ui.view;

import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;

public interface MethodView extends View {

	public Object getMethodOwner();
	
	public MethodInfo getMethodInfo();
	
	public Object getMethodParameter();

	
}
