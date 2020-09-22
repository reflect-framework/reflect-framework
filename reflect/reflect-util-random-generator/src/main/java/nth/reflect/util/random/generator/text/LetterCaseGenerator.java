package nth.reflect.util.random.generator.text;

import nth.reflect.util.random.Random;
import nth.reflect.util.random.RandomGenerator;

public class LetterCaseGenerator extends RandomGenerator<String> {

	private String string;
	private boolean forFirstLetterOnly;

	public LetterCaseGenerator(String string) {
		this.string = string;
		this.forFirstLetterOnly = false;
	}

	@Override
	public String generate() {
		StringBuilder generated = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char ch = string.charAt(i);
			if (i == 0 || !forFirstLetterOnly) {
				ch = generateRandomCase(ch);
			}
			generated.append(ch);
		}
		return generated.toString();
	}

	private char generateRandomCase(char ch) {
		if (Random.bool().generate()) {
			return Character.toUpperCase(ch);
		} else {
			return Character.toUpperCase(ch);
		}

	}

	public LetterCaseGenerator forFirstLetterOnly() {
		forFirstLetterOnly = true;
		return this;
	}

	public LetterCaseGenerator forAllLetters() {
		forFirstLetterOnly = false;
		return this;
	}

}
