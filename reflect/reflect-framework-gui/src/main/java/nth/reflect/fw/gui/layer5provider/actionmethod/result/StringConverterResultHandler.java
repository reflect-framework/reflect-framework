package nth.reflect.fw.gui.layer5provider.actionmethod.result;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

/**
 * Shows the return value from an {@link ActionMethod} in a Dialog, if the
 * return value type is supported by the {@link StringConverterFactory}
 * 
 * @author nilsth
 *
 */
public class StringConverterResultHandler
		extends nth.reflect.fw.layer5provider.actionmethod.result.handler.StringConverterResultHandler {

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		GraphicalUserinterfaceController userinterface = container.get(GraphicalUserinterfaceController.class);
		LanguageProvider languageProvider = container.get(LanguageProvider.class);

		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		String formatPattern = FormatPatternFactory.create(methodInfo.getMethod());
		StringConverter stringConverter = stringConverterProvider.create(typeInfo, formatPattern);
		String methodResultString = stringConverter.toString(methodResult);

		TranslatableString title = methodInfo.getTitle(methodParameter);
		TranslatableString message = MESSAGE.withParameters(methodResultString);

		List<Item> items = new ArrayList();
		items.add(new DialogCloseItem(languageProvider));

		userinterface.showDialog(title, message, items);
	}

}
