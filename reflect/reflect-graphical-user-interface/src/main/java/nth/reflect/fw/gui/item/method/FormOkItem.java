package nth.reflect.fw.gui.item.method;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class FormOkItem extends MethodItem {

	@SuppressWarnings("rawtypes")
	public FormOkItem( final FormTab formTab, final Object methodOwner, final ActionMethodInfo actionMethodInfo, final BufferedDomainValueModel domainValueModel) {
		super (formTab.getUserInterfaceContainer(), methodOwner, actionMethodInfo, domainValueModel);
		final GraphicalUserinterfaceController userInterfaceController = formTab.getUserInterfaceContainer().get(GraphicalUserinterfaceController.class);
		setAction(new Action() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				if (FormMode.EDIT== formTab.getFormMode()) {
					domainValueModel.commit();
				}
				userInterfaceController.processActionMethodExecution(methodOwner, actionMethodInfo, domainValueModel.getValue());
				userInterfaceController.getTabs().remove(formTab);
			}

		});
	}

	
}
