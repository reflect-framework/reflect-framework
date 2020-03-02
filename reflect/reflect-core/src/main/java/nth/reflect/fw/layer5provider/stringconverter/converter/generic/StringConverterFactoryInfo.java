package nth.reflect.fw.layer5provider.stringconverter.converter.generic;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class StringConverterFactoryInfo {
	private final TypeInfo typeInfo;
	private final DependencyInjectionContainer container;
	private final String formatPattern;

	public StringConverterFactoryInfo(TypeInfo typeInfo, DependencyInjectionContainer container, String formatPattern) {
		super();
		this.typeInfo = typeInfo;
		this.container = container;
		this.formatPattern = formatPattern;
	}


	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	public DependencyInjectionContainer getContainer() {
		return container;
	}

	public String getFormatPattern() {
		return formatPattern;
	}

}
