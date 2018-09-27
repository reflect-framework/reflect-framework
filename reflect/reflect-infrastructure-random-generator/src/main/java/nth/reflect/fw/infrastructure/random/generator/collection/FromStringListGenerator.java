package nth.reflect.fw.infrastructure.random.generator.collection;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import nth.reflect.fw.infrastructure.random.RandomGenerator;
import nth.reflect.fw.infrastructure.random.generator.text.ResourceFile;
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


	public FromStringListGenerator(ResourceFile resourceFile) {
		values=resourceFile.getValues();
	}


	public FromStringListGenerator forValues(ResourceFile resourceFile) {
		return new FromStringListGenerator(resourceFile);
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