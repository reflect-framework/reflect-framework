package nth.reflect.fw.layer5provider.stringconverter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.type.ReturnTypeInfo;
import nth.reflect.fw.layer5provider.stringconverter.converter.BigDecimalStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.BigIntegerStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.BooleanStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.ByteStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.DoubleStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.FloatStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.IntegerStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.LongStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.ShortStringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverter;
import nth.reflect.fw.layer5provider.stringconverter.converter.generic.StringConverterFactoryInfo;

/**
 * This {@link Map} contains {@link StringConverterFactoryInfo}s (created from
 * {@link DomainObjectProperty}s of the {@link DomainObject} class) and their
 * corresponding {@link StringConverter} type.
 * {@link StringConverterFactoryInfoMap} is used for JUnit testing
 * 
 * @author nilsth
 *
 */
public class StringConverterFactoryInfoMap
		extends HashMap<StringConverterFactoryInfo, Class<? extends StringConverter>> {

	private static final long serialVersionUID = 799702481550723556L;
	private final DependencyInjectionContainer container;
	private final ReflectApplication application;

	public StringConverterFactoryInfoMap(DependencyInjectionContainer container) {
		this.container = container;
		this.application = container.get(ReflectApplication.class);
//		put(DomainObject.GET_MY_String));
		put(DomainObject.IS_MY_BOOLEAN, BooleanStringConverter.class);
		put(DomainObject.IS_MY_PRIMITIVE_BOOLEAN, BooleanStringConverter.class);
//		put(DomainObject.GET_MY_char));
		put(DomainObject.GET_MY_BYTE, ByteStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_BYTE, ByteStringConverter.class);
		put(DomainObject.GET_MY_SHORT, ShortStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_SHORT, ShortStringConverter.class);
		put(DomainObject.GET_MY_INTEGER, IntegerStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_INT, IntegerStringConverter.class);
		put(DomainObject.GET_MY_LONG, LongStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_LONG, LongStringConverter.class);
		put(DomainObject.GET_MY_DOUBLE, DoubleStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_DOUBLE, DoubleStringConverter.class);
		put(DomainObject.GET_MY_FLOAT, FloatStringConverter.class);
		put(DomainObject.GET_MY_PRIMITIVE_FLOAT, FloatStringConverter.class);
		put(DomainObject.GET_MY_BIG_INTEGER, BigIntegerStringConverter.class);
		put(DomainObject.GET_MY_BIG_DECIMAL, BigDecimalStringConverter.class);
// AND MORE!

	}

	private void put(String domainObjectGetterMethod, Class<? extends StringConverter> expectedStringConverterType) {
		StringConverterFactoryInfo info = createInfo(domainObjectGetterMethod);
		put(info, expectedStringConverterType);
	}

	private StringConverterFactoryInfo createInfo(String domainObjectGetterMethod) {
		Method method = findMethod(domainObjectGetterMethod);
		ReturnTypeInfo typeInfo = new ReturnTypeInfo(application, method);
		StringConverterFactoryInfo stringConverterFactoryInfo = new StringConverterFactoryInfo(typeInfo, container,
				null);
		return stringConverterFactoryInfo;
	}

	private Method findMethod(String domainObjectGetterMethod) {
		Method[] allMethods = DomainObject.class.getDeclaredMethods();
		for (Method method : allMethods) {
			if (method.getName().equals(domainObjectGetterMethod)) {
				return method;
			}
		}
		throw new RuntimeException(
				"Could not find method " + domainObjectGetterMethod + " in " + DomainObject.class.getCanonicalName());
	}

}
