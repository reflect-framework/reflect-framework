package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class IntegerStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_INTEGER, formatPattern);
		return new IntegerStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return Integer.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Integer.MAX_VALUE;
	}

}
