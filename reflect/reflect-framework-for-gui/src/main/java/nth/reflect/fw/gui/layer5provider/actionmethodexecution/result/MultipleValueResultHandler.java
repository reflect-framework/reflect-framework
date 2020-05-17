package nth.reflect.fw.gui.layer5provider.actionmethodexecution.result;

import java.util.Optional;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.table.TableTab;
import nth.reflect.fw.gui.component.tab.table.TableTabFilter;
import nth.reflect.fw.gui.component.table.info.TableInfoForTableTab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * Shows the return values of a {@link ActionMethod} in a {@link TableTab}, if
 * these values are supported by
 * {@link TableInfoForTableTab#supports(ActionMethodInfo)}
 * 
 * @author nilsth
 *
 */
public abstract class MultipleValueResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.result.handler.MultipleValueResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		return TableInfoForTableTab.supports(methodInfo);
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		GraphicalUserinterfaceController userInterface = container.get(GraphicalUserinterfaceController.class);

		Tabs tabs = userInterface.getTabs();

		TableTabFilter tableTabFilter = new TableTabFilter(methodOwner, methodInfo, methodParameter, methodResult);
		Optional<TableTab> result = tabs.stream().filter(tableTabFilter).findFirst();

		if (result.isPresent()) {
			tabs.setSelected(result.get());
		} else {
			Tab tableTab = createTableTab(container, methodOwner, methodInfo, methodParameter);
			tabs.setSelected(tableTab);
		}
	}

	public abstract TableTab createTableTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter);

}
