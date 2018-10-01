package nth.reflect.fw.infrastructure.random.generator.text;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.util.StringUtil;

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
				return StringUtil.capitalize(result);
			}

		};
	}

}
