package nth.reflect.fw.gui.component.tab;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * A {@link Tab} that displays an {@link ActionMethod} parameter or {@link ActionMethod} result
 * @author nilsth
 *
 */
public interface ActionMethodTab extends Tab {

	public Object getMethodOwner();
	
	public ActionMethodInfo getMethodInfo();
	
	public Object getMethodParameter();

	
}
