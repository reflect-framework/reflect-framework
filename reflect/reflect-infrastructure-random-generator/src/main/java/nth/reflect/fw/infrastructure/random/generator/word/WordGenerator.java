package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

public class WordGenerator extends StringGenerator {
	private final SyllableGenerator syllableGenerator;
	private final int minSyllables;
	private final int maxSyllables;
	private final int length;

	public WordGenerator() {
		this(1, 3);
	}

	public WordGenerator(int length) {
		syllableGenerator = Random.syllableGenerator();
		this.minSyllables = -1;
		this.maxSyllables = -1;
		this.length = length;
	}

	public WordGenerator(int minSyllables, int maxSyllables) {
		syllableGenerator = Random.syllableGenerator();
		if (minSyllables < 1) {
			minSyllables = 1;
		}
		if (minSyllables > maxSyllables) {
			maxSyllables = minSyllables;
		}
		this.minSyllables = minSyllables;
		this.maxSyllables = maxSyllables;
		this.length = -1;
	}
	
	public WordGenerator forLength(int length) {
		return new WordGenerator(length);
	}
	
	public WordGenerator forSyllables(int minSyllables, int maxSyllables) {
		return new WordGenerator(minSyllables, maxSyllables);
	}

	@Override
	public String generate() {
		if (length > 0) {
			StringBuilder word = new StringBuilder();
			while (word.length() < length) {
				word.append(syllableGenerator.generate());
			}
			return word.substring(0, length);
		} else {
			return syllableGenerator.generateString(minSyllables, maxSyllables, "");
		}
	}

}
