package nth.reflect.fw.javafx.control.tab.form;

import javafx.beans.binding.BooleanBinding;
import nth.reflect.fw.gui.component.tab.Tabs;
import nth.reflect.fw.gui.item.tab.CancelItem;
import nth.reflect.fw.javafx.UserinterfaceControllerForJavaFX;
import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.javafx.control.mainwindow.MainWindow;
import nth.reflect.fw.javafx.control.tab.Tab;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

public class FormCancelButton extends ContentButton {

	public FormCancelButton(FormTab formTab) {
		super(createCancelItem(formTab));
		hideButtonWhenWindowIsSmall(formTab);
	}

	private void hideButtonWhenWindowIsSmall(FormTab formTab) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		MainWindow window = userInterfaceContainer.get(MainWindow.class);
		BooleanBinding extraWideBinding = window.getExtraWideBinding();
		visibleProperty().bind(extraWideBinding);
		managedProperty().bind(extraWideBinding);
	}

	private static CancelItem<Tab> createCancelItem(FormTab formTab) {
		UserInterfaceContainer userInterfaceContainer = formTab.getUserInterfaceContainer();
		UserinterfaceControllerForJavaFX userInterfaceController = userInterfaceContainer
				.get(UserinterfaceControllerForJavaFX.class);
		Tabs<Tab> tabs = userInterfaceController.getTabs();
		LanguageProvider languageProvider = userInterfaceContainer.get(LanguageProvider.class);
		CancelItem<Tab> cancelItem = new CancelItem<>(languageProvider, tabs, formTab);
		return cancelItem;
	}
}
