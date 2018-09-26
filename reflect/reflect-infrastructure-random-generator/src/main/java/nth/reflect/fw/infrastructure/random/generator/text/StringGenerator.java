package nth.reflect.fw.infrastructure.random.generator.text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * 
 * Generates a string from a given values of strings
 */
public class StringGenerator extends RandomGenerator<String> {

	private final List<String> values;

	public StringGenerator() {
		this.values = new ArrayList<>();
	}

	/**
	 * @param resourceFile
	 *            that is translated to a list of strings to generate from
	 */
	public StringGenerator(ResourceFile resourceFile) {
		this.values = resourceFile.getValues();
	}

	/**
	 * @param values
	 *            of items to generate from
	 */
	public StringGenerator(List<String> values) {
		this.values = values;
	}

	/**
	 * @param textWithMultipleLines
	 *            Gets a values from a {@link String}. This {@link String} can
	 *            be from a text (file) where each line is a new values item to
	 *            generate from.
	 */
	public StringGenerator(String textWithMultipleLines) {
		Scanner scanner = new Scanner(textWithMultipleLines);
		values = new ArrayList<>();
		while (scanner.hasNextLine()) {
			values.add(scanner.nextLine());
		}
		scanner.close();
	}

	/**
	 * @param inputStream
	 *            Gets a values from a {@link InputStream} (only during the
	 *            creation of the {@link StringGenerator}!). This
	 *            {@link InputStream} can be from a text file where each line is
	 *            a new values item to generate from.
	 */
	public StringGenerator(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		values = new ArrayList<>();
		while (scanner.hasNext()) {
			values.add(scanner.next());
		}
		scanner.close();
	}

	public StringGenerator forValues(ResourceFile resourceFile) {
		return new StringGenerator(resourceFile);
	}

	public StringGenerator forValues(String textWithMultipleLines) {
		return new StringGenerator(textWithMultipleLines);
	}

	public StringGenerator forValues(List<String> values) {
		return new StringGenerator(values);
	}

	public StringGenerator forValues(InputStream inputStream) {
		return new StringGenerator(inputStream);
	}

	public RandomGenerator<String> forUpperCase() {
		StringGenerator stringGenerator = this;
		return new RandomGenerator<String>() {
			
			@Override
			public String generate() {
				return stringGenerator.generate().toUpperCase();
			}
		};
	}
	
	public RandomGenerator<String> forLowerCase() {
		StringGenerator stringGenerator = this;
		return new RandomGenerator<String>() {
			
			@Override
			public String generate() {
				return stringGenerator.generate().toLowerCase();
			}
		};
	}

	/**
	 * Generates a string where the first Character is upper case. 
	 * @return
	 */
	public RandomGenerator<String> forFirstCharCapitalCase() {
		StringGenerator stringGenerator = this;
		return new RandomGenerator<String>() {
			
			@Override
			public String generate() {
				String result = stringGenerator.generate();
				return capitalize(result);
			}

		};
	}
	
	public static String capitalize(String result) {
		int strLen;
        if (result == null || (strLen = result.length()) == 0) {
            return result;
        }
        return new StringBuilder(strLen)
            .append(Character.toTitleCase(result.charAt(0)))
            .append(result.substring(1))
            .toString();
	}

	
	@Override
	public String generate() {
		if (values.size() == 0) {
			return "";
		} else {
			int randomIndex = ThreadLocalRandom.current().nextInt(values.size());
			return values.get(randomIndex);
		}
	}
}
