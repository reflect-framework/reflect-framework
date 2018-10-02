package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.generator.text.StringGenerator;

/**
 * 
 * Generates a string from a given values of strings
 */
public class FromStringListGenerator extends StringGenerator {

	private final List<String> values;

	public FromStringListGenerator() {
		this.values = new ArrayList<>();
	}


	/**
	 * @param values
	 *            of items to generate from
	 */
	public FromStringListGenerator(List<String> values) {
		this.values = values;
	}


	public FromStringListGenerator forValues(List<String> values) {
		return new FromStringListGenerator(values);
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
