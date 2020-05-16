package nth.reflect.fw.ui.commandline.provider.actionmethodexecution.result;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatPatternFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class StringConverterResultHandler
		extends nth.reflect.fw.layer5provider.actionmethodexecution.result.StringConverterResultHandler {

	public static final TranslatableString MESSAGE = new TranslatableString(
			StringConverterResultHandler.class.getCanonicalName() + ".message", "%s: Result is: %s");

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		return stringConverterProvider.canCreate(typeInfo);
	}

	@Override
	public void process(UserInterfaceContainer container, Object methodOwner, ActionMethodInfo methodInfo,
			Object methodParameter, Object methodResult) {
		UserInterfaceController userinterface = container.get(UserInterfaceController.class);

		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		String formatPattern = FormatPatternFactory.create(methodInfo.getMethod());
		StringConverter stringConverter = stringConverterProvider.create(typeInfo, formatPattern);
		String methodResultString = stringConverter.toString(methodResult);

		TranslatableString title = methodInfo.getTitle(methodParameter);
		TranslatableString message = MESSAGE.withParameters(title, methodResultString);

		userinterface.showMessage(message);
	}

}
