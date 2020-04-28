package nth.reflect.fw.gui.provider.actionmethodexecution.result;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.gui.item.dialog.DialogCloseItem;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class DialogResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		String formatPattern = FormatPatternFactory.create(methodInfo.getMethod());
		StringConverterFactoryInfo info = new StringConverterFactoryInfo(typeInfo, container, formatPattern);
		return stringConverterProvider.canCreate(info);
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		GraphicalUserinterfaceController userinterface = container.get(GraphicalUserinterfaceController.class);
		LanguageProvider languageProvider = container.get(LanguageProvider.class);

		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		String formatPattern = FormatPatternFactory.create(methodInfo.getMethod());
		StringConverterFactoryInfo info = new StringConverterFactoryInfo(typeInfo, container, formatPattern);
		StringConverter stringConverter = stringConverterProvider.create(info);
		String stringValue = stringConverter.toString(methodResult);

		TranslatableString title = methodInfo.getTitle(methodParameter);
		TranslatableString message = toTranslatableString(stringValue);

		List<Item> items = new ArrayList();
		items.add(new DialogCloseItem(languageProvider));

		userinterface.showDialog(title, message, items);
	}

	private TranslatableString toTranslatableString(String stringValue) {
		return new TranslatableString(null, "%s", stringValue);
	}

}
