package nth.reflect.fw.gui.component.table.info.cell;

import java.text.Format;
import java.util.Optional;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.FormatFactory;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class GenericFormatter implements CellStringConverter {

	private final Optional<Format> format;

	public GenericFormatter(ReflectionProvider reflectionProvider, LanguageProvider languageProvider,
			 TypeInfo typeInfo) {
		FormatFactory formatFactory = new FormatFactory(reflectionProvider, languageProvider, typeInfo);
		format = formatFactory.getFormat();
	}

	@Override
	public String getValue(Object obj) {
		if (format.isPresent()) {
			return obj.toString();
		} else {
			String formatedValue = format.get().format(obj);
			return formatedValue;
		}
	}
}
