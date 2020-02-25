package nth.reflect.fw.layer5provider.stringconverter.abstractconverter;

import java.util.Optional;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class StringConverterFactoryInfo {
	private final TypeInfo typeInfo;
	private final DependencyInjectionContainer container;
	private final Optional<String> formatPattern;

	public StringConverterFactoryInfo(TypeInfo typeInfo, DependencyInjectionContainer container, String formatPattern) {
		super();
		this.typeInfo = typeInfo;
		this.container = container;
		this.formatPattern = getFormatPattern(formatPattern);
	}

	private Optional<String> getFormatPattern(String formatPatternString) {
		if (formatPatternString==null) {
			return Optional.empty();
		} 
		formatPatternString=formatPatternString.trim();
		if (formatPatternString.length()==0) {
			return Optional.empty();	
		}
		return Optional.of(formatPatternString);
	}

	public TypeInfo getTypeInfo() {
		return typeInfo;
	}

	public DependencyInjectionContainer getContainer() {
		return container;
	}

	public Optional<String> getFormatPattern() {
		return formatPattern;
	}

}
