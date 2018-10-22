package nth.reflect.fw.javafx.control.tab.form;

import javafx.beans.binding.BooleanBinding;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.item.method.FormOkItem;
import nth.reflect.fw.ui.item.tab.CancelItem;
import nth.reflect.fw.ui.tab.Tabs;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;

public class RfxFormBottomToolBar extends RfxContentBottomToolbar {

	public RfxFormBottomToolBar(FormTab formTab) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		switch (formTab.getFormMode()) {
		case READ_ONLY_MODE:
			hide();
			break;
		case EDIT_MODE:
			getChildren().add(createOkButton(formTab));
			getChildren().add(createCancelButton(userInterfaceContainer, formTab));
			break;
		default:
			break;
		}
	}

	private void hide() {
		setVisible(false);
		setManaged(false);
	}

	public RfxContentBottomToolbarButton createCancelButton(
			UserInterfaceContainer userInterfaceContainer, FormTab formTab) {
		RfxUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CancelItem<Tab> cancelItem = new CancelItem<>(languageProvider, tabs, formTab);
		RfxContentBottomToolbarButton cancelButton = new RfxContentBottomToolbarButton(cancelItem);
		RfxWindow window = userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding extraWideBinding = window.getExtraWideBinding();
		cancelButton.visibleProperty().bind(extraWideBinding);
		cancelButton.managedProperty().bind(extraWideBinding);
		return cancelButton;
	}

	public RfxContentBottomToolbarButton createOkButton(FormTab formTab) {
		Object methodOwner = formTab.getMethodOwner();
		ActionMethodInfo actionMethodInfo = formTab.getMethodInfo();
		BufferedDomainValueModel domainValueModel = formTab.getDomainValueModel();
		FormOkItem okItem = new FormOkItem(formTab, methodOwner, actionMethodInfo,
				domainValueModel);
		return new RfxContentBottomToolbarButton(okItem);
	}

}
