package nth.reflect.fw.layer5provider.stringconverter.generic;

import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public interface StringConverterFactory {

	public boolean canCreate(TypeInfo typeInfo);

	public StringConverter create(TypeInfo typeInfo, String formatPattern);
}
