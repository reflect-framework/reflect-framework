package nth.reflect.fw.gui.layer5provider.actionmethod.prehandler;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.GraphicalUserInterfaceController;
import nth.reflect.fw.gui.item.dialog.DialogCancelItem;
import nth.reflect.fw.gui.item.dialog.DialogMethodItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;

public class AskConfirmation extends nth.reflect.fw.layer5provider.actionmethod.prehandler.impl.AskConfirmation {

	public AskConfirmation(StringConverterProvider stringConverterProvider) {
		super(stringConverterProvider);
	}

	public static final TranslatableString DIALOG_TITLE = new TranslatableString(
			AskConfirmation.class.getCanonicalName() + ".dialog.title", "Confirmation");
	public static final TranslatableString CONFIRMATION_DIALOG_QUESTION = new TranslatableString(
			AskConfirmation.class.getCanonicalName() + ".dialog.question", "Do you want to execute: %s ?");

	@Override
	public void preProcess(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter) throws Exception {

		List<Item> items = createButtonItems(container, methodInfo, methodOwner, methodParameter);

		GraphicalUserInterfaceController userinterface = container.get(GraphicalUserInterfaceController.class);
		TranslatableString title = DIALOG_TITLE;
		TranslatableString methodTitle = methodInfo.getTitle(methodParameter);
		TranslatableString question = CONFIRMATION_DIALOG_QUESTION.withParameters(methodTitle);
		userinterface.showDialog(title, question, items);

	}

	private List<Item> createButtonItems(UserInterfaceContainer container, ActionMethodInfo methodInfo,
			Object methodOwner, Object methodParameter) {
		LanguageProvider languageProvider = container.get(LanguageProvider.class);
		List<Item> items = new ArrayList<Item>();
		DialogMethodItem methodExecuteItem = new DialogMethodItem(container, methodOwner, methodInfo, methodParameter);
		items.add(methodExecuteItem);
		DialogCancelItem cancelItem = new DialogCancelItem(languageProvider);
		items.add(cancelItem);
		return items;
	}

}
