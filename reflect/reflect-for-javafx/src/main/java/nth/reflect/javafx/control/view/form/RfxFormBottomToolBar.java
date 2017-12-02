package nth.reflect.javafx.control.view.form;

import javafx.beans.binding.BooleanBinding;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.item.method.FormOkItem;
import nth.introspect.ui.item.tab.CancelItem;
import nth.introspect.ui.item.tab.CloseThisTabItem;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.javafx.control.window.RfxWindow;

public class RfxFormBottomToolBar extends RfxContentBottomToolbar {
 
	public RfxFormBottomToolBar(RfxFormView formView) {
		UserInterfaceContainer userInterfaceContainer = formView.getUserInterfaceContainer();
		switch (formView.getFormMode()) {
		case READ_ONLY_MODE:
			//TODO no buttons or bottom bar when in read only mode (close tab on tab header bar)
			getChildren().add(createCloseButton(userInterfaceContainer, formView));
			break;
		case EDIT_MODE:
			getChildren().add(createOkButton(formView));
			getChildren().add(createCancelButton(userInterfaceContainer, formView));
			break;
		default:
			break;
		}
}
	
	public RfxContentBottomToolbarButton createCloseButton(UserInterfaceContainer userInterfaceContainer, RfxFormView formView) {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CloseThisTabItem closeItem = new CloseThisTabItem(languageProvider,
				viewContainer, formView);
		return new RfxContentBottomToolbarButton(closeItem);
	}

	public RfxContentBottomToolbarButton createCancelButton(UserInterfaceContainer userInterfaceContainer, RfxFormView formView) {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, viewContainer,
				formView);
		RfxContentBottomToolbarButton cancelButton = new RfxContentBottomToolbarButton(cancelItem);
		RfxWindow window=userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding extraWideBinding = window.getExtraWideBinding();
		cancelButton.visibleProperty().bind(extraWideBinding);
		return cancelButton;
	}

	public RfxContentBottomToolbarButton createOkButton( RfxFormView formView) {
		Object methodOwner = formView.getMethodOwner();
		ActionMethodInfo actionMethodInfo = formView.getMethodInfo();
		BufferedDomainValueModel domainValueModel = formView.getDomainValueModel();
		FormOkItem okItem = new FormOkItem(formView, methodOwner, actionMethodInfo,
				domainValueModel);
		return new RfxContentBottomToolbarButton(okItem);
	}

}
