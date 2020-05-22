package nth.reflect.fw.vaadin.layer5provider.actionmethod.result;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.vaadin.tab.table.TableTab;

public class MultipleValueResultHandler extends nth.reflect.fw.gui.layer5provider.actionmethod.result.MultipleValueResultHandler {

	@Override
	public TableTab createTableTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter) {
		return new TableTab(container, methodOwner, actionMethodInfo, methodParameter);
	}

}
