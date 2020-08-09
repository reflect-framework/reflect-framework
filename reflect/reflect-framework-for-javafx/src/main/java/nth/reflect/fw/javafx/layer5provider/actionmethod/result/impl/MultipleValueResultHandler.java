package nth.reflect.fw.javafx.layer5provider.actionmethod.result.impl;

import nth.reflect.fw.javafx.control.tab.table.TableTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class MultipleValueResultHandler
		extends nth.reflect.fw.gui.layer5provider.actionmethod.result.impl.MultipleValueResultHandler {

	@Override
	public TableTab createTableTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter) {
		return new TableTab(container, methodOwner, actionMethodInfo, methodParameter);
	}

}
