package nth.reflect.fw.ui.view;

import nth.reflect.fw.layer1userinterface.view.View;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public interface MethodView extends View {

	public Object getMethodOwner();
	
	public ActionMethodInfo getMethodInfo();
	
	public Object getMethodParameter();

	
}
