package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

public abstract class StringGenerator extends RandomGenerator<String> {


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

}
