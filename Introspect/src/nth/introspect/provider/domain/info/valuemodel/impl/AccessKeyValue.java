package nth.introspect.provider.domain.info.valuemodel.impl;

import nth.introspect.provider.domain.info.IntrospectionInfo;

public class AccessKeyValue extends TextValue {

	public AccessKeyValue(IntrospectionInfo introspectionInfo, String name) {
		super(introspectionInfo, name);
	}
	
	@Override
	public Object getValue() {
		String text= ((String) super.getValue()).trim();
		if (text.length()>0) {
			return text.substring(0,1).toLowerCase();
		}
		return null;
	}
}
