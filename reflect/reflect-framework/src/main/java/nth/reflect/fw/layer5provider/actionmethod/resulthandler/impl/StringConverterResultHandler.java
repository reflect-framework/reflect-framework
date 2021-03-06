package nth.reflect.fw.layer5provider.actionmethod.resulthandler.impl;

import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.resulthandler.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.StringConverterProvider;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;

/**
 * Shows the return value from an {@link ActionMethod} in a Dialog, if the
 * return value type is supported by the {@link StringConverterFactory}
 * 
 * @author nilsth
 *
 */
public abstract class StringConverterResultHandler implements ActionMethodResultHandler {

	public static final TranslatableString MESSAGE = new TranslatableString(
			StringConverterResultHandler.class.getCanonicalName() + ".message", "Result is: %s");

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		StringConverterProvider stringConverterProvider = container.get(StringConverterProvider.class);
		TypeInfo typeInfo = methodInfo.getReturnTypeInfo();
		return stringConverterProvider.canCreate(typeInfo);
	}

}
