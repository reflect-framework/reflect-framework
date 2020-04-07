package nth.reflect.maven.plugin.language.files.texts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
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
		String text = translatableString.getDefaultEnglishWithoutParameters();
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
		TranslatableString description = nameInfo.getDescription();
		put(description);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> map) {
		for (String key : map.keySet()) {
			String text = map.get(key);
			put(key, text);
		}
	}

	@Override
	public String put(String key, String value) {
		if (containsKey(key) && !get(key).contentEquals(value)) {
			throw new RuntimeException(String.format("Key: %s already contains a different value: %s", key, value));
		}
		return super.put(key, value);
	}

	@Override
	public String toString() {
		Iterator<Entry<String, String>> i = entrySet().iterator();
		if (!i.hasNext()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		while (i.hasNext()) {
			Entry<String, String> e = i.next();
			String key = e.getKey();
			String value = e.getValue();
			sb.append(key);
			sb.append('=');
			sb.append(value);
			sb.append("\r\n");
		}
		return sb.toString();
	}
}
