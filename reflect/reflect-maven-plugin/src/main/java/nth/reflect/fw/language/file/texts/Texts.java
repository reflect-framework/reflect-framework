package nth.reflect.fw.language.file.texts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.SortedMap;
import java.util.TreeMap;

import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.NameInfo;

/**
 * a {@link SortedMap} of keys and default English texts (text from code) for
 * translations
 * 
 * @author nilsth
 *
 */
public abstract class Texts extends TreeMap<String, String> {
	private static final long serialVersionUID = 2334242652084796061L;

	public void put(TranslatableString translatableString) {
		String key = translatableString.getKey();
		String text = translatableString.getDefaultEnglish();
		put(key, text);
	}

	public void putPropertiesFromTranslatableStringsFromStaticFields(Class<?> owner) {
		Field[] fields = owner.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);// also get private fields
			if (TranslatableString.class.isAssignableFrom(field.getType())) {
				if (Modifier.isStatic(field.getModifiers())) {
					TranslatableString translatableString;
					try {
						translatableString = (TranslatableString) field.get(null);
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					put(translatableString);
				}
			}
		}
	}

	protected void put(NameInfo nameInfo) {
		TranslatableString displayName = nameInfo.getDisplayName();
		put(displayName);
		TranslatableString description = nameInfo.getDisplayName();
		put(description);
	}

}
