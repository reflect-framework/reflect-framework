package nth.introspect.provider.domain.info.valuemodel.impl;

import java.text.Format;
import java.util.List;

import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.provider.domain.info.type.TypeCategory;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.util.StringUtil;
import nth.introspect.valuemodel.IntrospectedValueModelReadOnly;

public class TitleValue extends IntrospectedValueModelReadOnly {

	private static final String TITLE_SEPARATOR = ", ";
	private final DomainInfoProvider domainInfoProvider;
	private final LanguageProvider languageProvider;

	public TitleValue(DomainInfoProvider domainInfoProvider, LanguageProvider languageProvider) {
		this.domainInfoProvider = domainInfoProvider;
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

		// get title from toString implementation
		String title = obj.toString();
		String defaultToStringImplValue = obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
		if (title == null || title.trim().length() == 0 || title.equals(defaultToStringImplValue)) {
			// toString method is not overwritten or does not return a value so construct our own title
			StringBuffer titleBuffer = new StringBuffer();
			List<PropertyInfo> propertyInfos = domainInfoProvider.getOrderedAndVisiblePropertyInfos(obj.getClass());
			for (PropertyInfo propertyInfo : propertyInfos) {
				Object propertyValue = propertyInfo.getValue(obj);
				Format format=propertyInfo.getFormat();
				 TypeCategory typeCatagory = propertyInfo.getPropertyType().getTypeCategory();
				if (propertyValue != null && typeCatagory!= TypeCategory.COLLECTION_TYPE) {
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
			title = titleBuffer.toString();
		}
		return title;
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
