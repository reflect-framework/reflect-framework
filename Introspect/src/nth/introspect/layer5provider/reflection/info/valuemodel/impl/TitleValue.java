package nth.introspect.layer5provider.reflection.info.valuemodel.impl;

import java.text.Format;
import java.util.List;

import nth.introspect.generic.util.StringUtil;
import nth.introspect.generic.valuemodel.IntrospectedValueModelReadOnly;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

public class TitleValue extends IntrospectedValueModelReadOnly {

	private static final String TITLE_SEPARATOR = ", ";
	private final ReflectionProvider reflectionProvider;
	private final LanguageProvider languageProvider;

	public TitleValue(ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider) {
		this.reflectionProvider = reflectionProvider;
		this.languageProvider = languageProvider;
	}

	public Object getValue(Object obj) {
		if (obj.getClass().isEnum()) {
			String enumValue = obj.toString();
			Class<? extends Object> enumClass = obj.getClass();
			String key = enumClass.getCanonicalName() + "." + enumValue;
			String defaultText = StringUtil.convertToNormalCase(obj.toString());
			return languageProvider.getText(key, defaultText);
		}

		boolean toStingValueIsOverwritten = isOverWrittenToStringMethod(obj);
		if (toStingValueIsOverwritten) {
			return obj.toString();
		} else {
			StringBuffer titleBuffer = createTitle(obj);
			return titleBuffer.toString();
		}

	}

	private StringBuffer createTitle(Object obj) {
		StringBuffer titleBuffer = new StringBuffer();
		List<PropertyInfo> propertyInfos = reflectionProvider
				.getOrderedAndVisiblePropertyInfos(obj.getClass());
		for (PropertyInfo propertyInfo : propertyInfos) {
			Object propertyValue = propertyInfo.getValue(obj);
			Format format = propertyInfo.getFormat();
			TypeCategory typeCatagory = propertyInfo.getPropertyType()
					.getTypeCategory();
			if (propertyValue != null
					&& typeCatagory != TypeCategory.COLLECTION_TYPE) {
				StringBuffer propertyText = new StringBuffer();
				String propertyValueText = format.format(propertyValue);
				propertyText.append(propertyValueText);
				if (propertyText.toString().trim().length() > 0) {
					if (titleBuffer.length() > 0) {
						titleBuffer.append(TITLE_SEPARATOR);
					}
					titleBuffer.append(propertyText);
				}
			}
		}
		return titleBuffer;
	}

	private boolean isOverWrittenToStringMethod(Object obj) {
		String title = obj.toString();
		String defaultToStringImplValue = obj.getClass().getName() + "@"
				+ Integer.toHexString(obj.hashCode());
		boolean toStingValueIsOverwritten = title == null
				|| title.trim().length() == 0
				|| title.equals(defaultToStringImplValue);
		return toStingValueIsOverwritten;
	}

	@Override
	public Class<?> getValueType() {
		return String.class;
	}

	@Override
	public boolean canGetValue() {
		return true;
	}

}
