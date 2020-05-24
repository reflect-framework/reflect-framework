package nth.reflect.fw.layer5provider.stringconverter;

import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.ProviderHelperNotDeclaredException;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.url.ReflectUrlStreamHandler;

/**
 * The {@link StringConverterProvider} is a {@link Provider} that provides a
 * {@link StringConverter} for a given {@link TypeInfo} and a given (optional)
 * {@link Format} pattern.
 * <p>
 * {@insert StringConverter}
 * <p>
 * You can append or override custom {@link StringConverter}s by overriding the
 * {@link ReflectApplication#getStringConverterProviderClass()} method.
 * <p>
 * {@insert StringConverterFactories}
 * 
 * @author nilsth
 *
 */

public class StringConverterProvider implements Provider {

	private List<StringConverterFactory> stringConverterFactories;
	private ProviderContainer providerContainer;

	public StringConverterProvider(ProviderContainer providerContainer) {
		this.providerContainer = providerContainer;
		stringConverterFactories = createFactories();
	}

	private List<StringConverterFactory> createFactories() {
		List<Class<? extends StringConverterFactory>> factoryClasses = getFactoryClasses();
		ArrayList<StringConverterFactory> factories = new ArrayList();
		for (Class<? extends StringConverterFactory> factoryClass : factoryClasses) {
			providerContainer.add(factoryClass);
			StringConverterFactory factory = providerContainer.get(factoryClass);
			factories.add(factory);
		}
		return Collections.unmodifiableList(factories);
	}

	private List<Class<? extends StringConverterFactory>> getFactoryClasses() {
		ReflectApplication application = providerContainer.get(ReflectApplication.class);
		List<Class<? extends StringConverterFactory>> factoryClasses = application.getStringConverterFactoryClasses();
		if (factoryClasses == null || factoryClasses.size() == 0) {
			String canonicalMethodName = ReflectApplication.class.getSimpleName() + ".getStringConverterClasses";
			throw new ProviderHelperNotDeclaredException(ReflectUrlStreamHandler.class, canonicalMethodName);
		}
		return factoryClasses;
	}

	public StringConverterFactory find(TypeInfo typeInfo) {
		Optional<StringConverterFactory> result = stringConverterFactories
				.stream()
				.filter(stringConverterFactory -> stringConverterFactory.canCreate(typeInfo))
				.findFirst();
		return result.orElseThrow(() -> new StringConverterFactoryNotFoundException(typeInfo));
	}

	public boolean canCreate(TypeInfo typeInfo) {
		boolean canCreate = stringConverterFactories
				.stream()
				.anyMatch(stringConverterFactory -> stringConverterFactory.canCreate(typeInfo));
		return canCreate;
	}

	public StringConverter create(TypeInfo typeInfo, String formatPattern) {
		StringConverterFactory stringConverterFactory = find(typeInfo);
		StringConverter stringConverter = stringConverterFactory.create(typeInfo, formatPattern);
		return stringConverter;
	}

}
