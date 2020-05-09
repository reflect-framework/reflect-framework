package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer3domain.AllFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class ShortStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(AllFeatureDomainObject.GET_MY_SHORT, formatPattern);
		return new ShortStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return Short.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Short.MAX_VALUE;
	}

}
