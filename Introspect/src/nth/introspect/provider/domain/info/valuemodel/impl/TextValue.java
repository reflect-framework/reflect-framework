package nth.introspect.provider.domain.info.valuemodel.impl;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.IntrospectionInfo;
import nth.introspect.util.StringUtil;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public class TextValue implements ReadOnlyValueModel {

	private String defaultValue;
	private final String key;

	public TextValue(IntrospectionInfo introspectInfo, String suffix) {
		this(introspectInfo, suffix, null);
	}

	public TextValue(IntrospectionInfo introspectInfo, String suffix, String regexpToRemoveFromDefaultText) {
		defaultValue = introspectInfo.getName();
		if (regexpToRemoveFromDefaultText != null) {
			defaultValue=defaultValue.replaceAll(regexpToRemoveFromDefaultText, "");
		}
		defaultValue = StringUtil.convertToNormalCase(defaultValue);
		StringBuilder key = new StringBuilder();
		key.append(introspectInfo.getNamePath());
		key.append(".");
		key.append(suffix);
		this.key = key.toString();
	}

	public String getKey() {
		return key;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public Object getValue() {
		// try to get value from resource file with key
		return Introspect.getLanguageProvider().getText(key, defaultValue);
	}

	@Override
	public String toString() {
		return defaultValue;
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
