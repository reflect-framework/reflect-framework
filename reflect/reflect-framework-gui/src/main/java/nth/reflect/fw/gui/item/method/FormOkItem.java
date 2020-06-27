package nth.reflect.fw.gui.item.method;

import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.component.tab.form.FormMode;
import nth.reflect.fw.gui.component.tab.form.FormTab;
import nth.reflect.fw.gui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodInvoker;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class FormOkItem extends MethodItem {

	private static final TranslatableString ONE_ORE_MORE_PROPERTIES_ARE_INVALID = new TranslatableString(
			FormOkItem.class.getCanonicalName() + ".propertiesArInvalid", "One ore more properties are invalid.");

	@SuppressWarnings("rawtypes")
	public FormOkItem(final FormTab formTab, final Object methodOwner, final ActionMethodInfo methodInfo,
			final BufferedDomainValueModel domainValueModel) {
		super(formTab.getUserInterfaceContainer(), methodOwner, methodInfo, domainValueModel);
		Action createAction = createAction(formTab, methodOwner, methodInfo, domainValueModel);
		setAction(createAction);
	}

	private Action createAction(final FormTab formTab, Object methodOwner, final ActionMethodInfo methodInfo,
			final BufferedDomainValueModel domainValueModel) {

		UserInterfaceContainer container = formTab.getUserInterfaceContainer();
		GraphicalUserInterfaceController userInterface = container.get(GraphicalUserInterfaceController.class);

		return new Action() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				if (FormMode.EDIT == formTab.getFormMode()) {
					if (!domainValueModel.isValid()) {
						userInterface.showMessage(ONE_ORE_MORE_PROPERTIES_ARE_INVALID);
						return;
					} else {
						domainValueModel.commit();
					}
				}
				Object methodParameter = domainValueModel.getValue();
				ActionMethodInvoker invoker = new ActionMethodInvoker(container, methodInfo, methodOwner,
						methodParameter);
				invoker.run();

				userInterface.getTabs().remove(formTab);
			}

		};
	}

}
