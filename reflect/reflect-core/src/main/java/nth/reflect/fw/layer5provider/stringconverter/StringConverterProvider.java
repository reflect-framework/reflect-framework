package nth.reflect.fw.layer5provider.stringconverter;

import java.text.Format;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.generic.contractor.DelegatingContractor;
import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactory;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

/**
 * The {@link StringConverterProvider} is a {@link Provider} that provides a
 * {@link StringConverter} for a given {@link TypeInfo} and a given (optional)
 * {@link Format} pattern.
 * <p>
 * {@insert StringConverter}
 * <p>
 * You can append or override custom {@link StringConverter}s by overriding the
 * {@link ReflectApplication#getStringConverterProvider()} method.
 * <p>
 * {@insert DefaultStringConverters}
 * 
 * @author nilsth
 *
 */

public class StringConverterProvider extends DelegatingContractor<StringConverter, StringConverterFactoryInfo>
		implements Provider {

	public StringConverterProvider(StringConverterFactory... stringConverters) {
		super(stringConverters);
	}

}
