package nth.reflect.fw.ui.item.method;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.component.tab.form.FormMode;
import nth.reflect.fw.ui.component.tab.form.FormTab;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;

public class FormOkItem extends MethodItem {

	@SuppressWarnings("rawtypes")
	public FormOkItem( final FormTab formTab, final Object methodOwner, final ActionMethodInfo actionMethodInfo, final BufferedDomainValueModel domainValueModel) {
		super (formTab.getUserInterfaceContainer(), methodOwner, actionMethodInfo, domainValueModel);
		final GraphicalUserinterfaceController userInterfaceController = formTab.getUserInterfaceContainer().get(GraphicalUserinterfaceController.class);
		setAction(new Action() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				if (FormMode.EDIT_MODE== formTab.getFormMode()) {
					domainValueModel.commit();
				}
				userInterfaceController.processActionMethodExecution(methodOwner, actionMethodInfo, domainValueModel.getValue());
				userInterfaceController.getTabs().remove(formTab);
			}

		});
	}

	
}
