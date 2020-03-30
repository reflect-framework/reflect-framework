package nth.reflect.fw.language.file.texts;

import java.util.ArrayList;

public class UniqueKeyTest extends ArrayList<String> {

	private static final long serialVersionUID = 1L;

	public UniqueKeyTest(AllTexts allTexts) {
		addKeys(allTexts.getReflectApplicationTexts());
		addKeys(allTexts.getServiceTexts());
		addKeys(allTexts.getDomainTexts());
		addKeys(allTexts.getInfrasturctureTexts());
		addKeys(allTexts.getReflectFrameWorkTexts());
	}

	private void addKeys(Texts texts) {
		for (String key : texts.keySet()) {
			if (contains(key)) {
				throw new RuntimeException("Double key: " + key);
			} else {
				add(key);
			}
		}
	}

}
