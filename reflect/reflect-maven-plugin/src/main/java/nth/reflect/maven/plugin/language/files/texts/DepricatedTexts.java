package nth.reflect.maven.plugin.language.files.texts;

import java.util.Properties;

public class DepricatedTexts extends Texts {

	private static final String DEPRICATED_PREFIX = "depricated.";
	private static final long serialVersionUID = -3479760601172756607L;

	public DepricatedTexts(AllTexts newTexts, Texts oldTexts) {
		for (String oldKey : oldTexts.keySet()) {
			if (!newTexts.containsKey(oldKey)) {
				String depricatedKey = getDepricatedKey(oldKey);
				String depricatedText = oldTexts.get(oldKey);
				put(depricatedKey, depricatedText);
			}
		}
	}

	public DepricatedTexts(AllTexts newTexts, Properties oldTexts) {
		this(newTexts, new PropertyTexts(oldTexts));
	}

	private String getDepricatedKey(String oldKey) {
		if (oldKey.startsWith(DEPRICATED_PREFIX)) {
			return oldKey;
		} else {
			return DEPRICATED_PREFIX + oldKey;
		}
	}

}
