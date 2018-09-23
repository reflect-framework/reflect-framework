package nth.reflect.fw.javafx.control.view.form;

import javafx.beans.binding.BooleanBinding;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.RfxViewContainer;
import nth.reflect.fw.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.item.method.FormOkItem;
import nth.reflect.fw.ui.item.tab.CancelItem;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;

public class RfxFormBottomToolBar extends RfxContentBottomToolbar {

	public RfxFormBottomToolBar(FormView formView) {
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		switch (formView.getFormMode()) {
		case READ_ONLY_MODE:
			hide();
			break;
		case EDIT_MODE:
			getChildren().add(createOkButton(formView));
			getChildren().add(createCancelButton(userInterfaceContainer, formView));
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
			UserInterfaceContainer userInterfaceContainer, FormView formView) {
		RfxUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		RfxViewContainer viewContainer = userInterfaceController.getViewController();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, viewContainer, formView);
		RfxContentBottomToolbarButton cancelButton = new RfxContentBottomToolbarButton(cancelItem);
		RfxWindow window = userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding extraWideBinding = window.getExtraWideBinding();
		cancelButton.visibleProperty().bind(extraWideBinding);
		cancelButton.managedProperty().bind(extraWideBinding);
		return cancelButton;
	}

	public RfxContentBottomToolbarButton createOkButton(FormView formView) {
		Object methodOwner = formView.getMethodOwner();
		ActionMethodInfo actionMethodInfo = formView.getMethodInfo();
		BufferedDomainValueModel domainValueModel = formView.getDomainValueModel();
		FormOkItem okItem = new FormOkItem(formView, methodOwner, actionMethodInfo,
				domainValueModel);
		return new RfxContentBottomToolbarButton(okItem);
	}

}
