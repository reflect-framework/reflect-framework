package nth.reflect.fw.infrastructure.random.generator.word;

import nth.reflect.fw.infrastructure.random.IntRange;
import nth.reflect.fw.infrastructure.random.Random;
import nth.reflect.fw.infrastructure.random.RandomGenerator;

public class WordGenerator extends RandomGenerator<String> {
	private final SyllableGenerator syllableGenerator;
	private final IntRange numberOfSyllables;
	private final int length;

	public WordGenerator() {
		this(1, 4);
	}

	public WordGenerator(int length) {
		syllableGenerator = Random.syllable();
		this.numberOfSyllables = null;
		this.length = length;
	}

	public WordGenerator(int minSyllables, int maxSyllables) {
		syllableGenerator = Random.syllable();
		this.numberOfSyllables = new IntRange(minSyllables, maxSyllables);
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
			return syllableGenerator.generateString(numberOfSyllables.getMin(), numberOfSyllables.getMax(), "");
		}
	}

}
