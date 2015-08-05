package nth.introspect.ui.view;

import nth.introspect.layer1userinterface.view.View;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public interface MethodView extends View {

	public Object getMethodOwner();
	
	public ActionMethodInfo getMethodInfo();
	
	public Object getMethodParameter();

	
}
