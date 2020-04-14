package nth.reflect.fw.layer5provider.stringconverter.java.number;

import nth.reflect.fw.layer3domain.FullFeatureDomainObject;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverterFactoryInfo;

public class ByteStringConverterTest extends NumberStringConverterTest {

	@Override
	protected StringConverter createStringConverter(String formatPattern) {
		StringConverterFactoryInfo info = createInfo(FullFeatureDomainObject.GET_MY_BYTE, formatPattern);
		return new ByteStringConverter(info);
	}

	@Override
	protected Number getMinValue() {
		return Byte.MIN_VALUE;
	}

	@Override
	protected Number getMaxValue() {
		return Byte.MAX_VALUE;
	}

}
