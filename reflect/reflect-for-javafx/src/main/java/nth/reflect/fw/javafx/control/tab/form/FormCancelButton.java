package nth.reflect.fw.javafx.control.tab.form;

import javafx.beans.binding.BooleanBinding;
import nth.reflect.fw.javafx.RfxUserinterfaceController;
import nth.reflect.fw.javafx.control.button.RfxContentButton;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.javafx.control.window.RfxWindow;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.ui.item.tab.CancelItem;
import nth.reflect.fw.ui.tab.Tabs;

public class FormCancelButton extends RfxContentButton {

	public FormCancelButton(FormTab formTab) {
		super(createCancelItem(formTab));
		hideButtonWhenWindowIsSmall(formTab);
	}

	private void hideButtonWhenWindowIsSmall(FormTab formTab) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		RfxWindow window = userInterfaceContainer.get(RfxWindow.class);
		BooleanBinding extraWideBinding = window.getExtraWideBinding();
		visibleProperty().bind(extraWideBinding);
		managedProperty().bind(extraWideBinding);
	}

	private static CancelItem<Tab> createCancelItem(FormTab formTab) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		RfxUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(RfxUserinterfaceController.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CancelItem<Tab> cancelItem = new CancelItem<>(languageProvider, tabs, formTab);
		return cancelItem;
	}
}
