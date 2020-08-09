package nth.reflect.fw.gui.layer5provider.actionmethod.prehandler.impl;

import java.util.Optional;

import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.FormTabFilter;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.actionmethod.prehandler.ActionMethodPreHandler;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

/**
 * The {@link ActionMethod} parameter is edited by the user and then the
 * {@link ActionMethod} is invoked by a {@link ActionMethodInvoker} or the
 * action is canceled.
 * 
 * @author nilsth
 *
 */
public abstract class EditDomainObject implements ActionMethodPreHandler {

	@Override
	public boolean canProcess(ActionMethodInfo methodInfo) {
		return ExecutionModeType.EDIT_PARAMETER_THEN_EXECUTE_METHOD_OR_CANCEL == methodInfo.getExecutionMode()
				&& methodInfo.getFirstParameterTypeInfo().isDomainClass();
	}

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {
		GraphicalUserInterfaceController userInterface = container.get(GraphicalUserInterfaceController.class);
		// TODO see GIT Issue#496

		Tabs tabs = userInterface.getTabs();

		Object domainObject = methodParameter;
		FormTabFilter formTabFilter = createFormTabFilter(methodInfo, methodOwner, methodParameter, domainObject);
		Optional<FormTab> result = findFormTab(tabs, formTabFilter);

		if (result.isPresent()) {
			FormTab existingFormTab = result.get();
			tabs.setSelected(existingFormTab);
		} else {
			Tab newFormTab = createEditableFormTab(container, methodOwner, methodInfo, methodParameter, domainObject);
			tabs.setSelected(newFormTab);
		}
	}

	private FormTabFilter createFormTabFilter(ActionMethodInfo methodInfo, Object methodOwner, Object methodParameter,
			Object domainObject) {
		FormMode formMode = FormMode.EDIT;
		FormTabFilter formTabFilter = new FormTabFilter(methodOwner, methodInfo, methodParameter, domainObject,
				formMode);
		return formTabFilter;
	}

	private Optional findFormTab(Tabs tabs, FormTabFilter formTabFilter) {
		Optional result = tabs.stream().filter(formTabFilter).findFirst();
		return result;
	}

	public abstract Tab createEditableFormTab(UserInterfaceContainer container, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameter, Object domainObject);

}
