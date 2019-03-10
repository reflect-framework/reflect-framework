package nth.reflect.fw.gui.component.table.info.cell;

import java.text.Format;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.format.impl.JavaFormatFactory;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class CellValueFactoryForJavaAndEnumTypes implements CellValueFactory {

	private final Format format;

	public CellValueFactoryForJavaAndEnumTypes(ReflectApplication reflectApplication, LanguageProvider languageProvider,
			Class<?> type) {
		JavaFormatFactory formatFactory = new JavaFormatFactory(languageProvider);
		TypeInfo typeInfo = new TypeInfo(reflectApplication, type, type);
		format = formatFactory.create(typeInfo);
	}

	@Override
	public String getValue(Object obj) {
		String formatedValue = format.format(obj);
		return formatedValue;
	}
}
