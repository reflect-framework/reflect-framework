package nth.reflect.fw.ui.swing.layer5provider.actionmethodexecution;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.swing.tab.table.TableTab;

public class TableResultHandler extends nth.reflect.fw.gui.provider.actionmethodexecution.result.TableResultHandler {

	@Override
	public TableTab createTableTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter) {
		return new TableTab(container, methodOwner, actionMethodInfo, methodParameter);
	}

}
