package nth.reflect.fw.layer5provider.stringconverter.generic;

import java.util.Optional;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.generic.util.TitleBuilder;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class StringConverterFactoryInfo {
	private final TypeInfo typeInfo;
	private final DependencyInjectionContainer container;
	private final Optional<String> formatPattern;

	public StringConverterFactoryInfo(TypeInfo typeInfo, DependencyInjectionContainer container, String formatPattern) {
		super();
		this.typeInfo = typeInfo;
		this.container = container;
		this.formatPattern = Optional.ofNullable(formatPattern).filter(s -> !s.isEmpty());
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

	@Override
	public String toString() {
		TitleBuilder titleBuilder = new TitleBuilder();
		titleBuilder.append("Type: " + typeInfo);
		if (formatPattern.isPresent()) {
			titleBuilder.append("Format: " + formatPattern.get());
		}
		return titleBuilder.toString();
	}

}
