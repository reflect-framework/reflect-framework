package nth.reflect.fw.infrastructure.random.generator.text;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * 
 * Generates a string from a given pool of strings
 */
public class StringGenerator extends RandomGenerator<String> {

	private final List<String> pool;

	public StringGenerator() {
		this.pool = new ArrayList<>();
	}

	/**
	 * @param pool of items to generate from
	 */
	public StringGenerator(List<String> pool) {
		this.pool = pool;
	}

	/**
	 * @param textWithMultipleLines Gets a pool from a {@link String}. This {@link String} can be from a text
	 * (file) where each line is a new pool item to generate from.
	 */
	public StringGenerator(String textWithMultipleLines) {
		Scanner scanner = new Scanner(textWithMultipleLines);
		pool = new ArrayList<>();
		while (scanner.hasNextLine()) {
			pool.add(scanner.nextLine());
		}
		scanner.close();
	}

	/**
	 * @param inputStream Gets a pool from a {@link InputStream} (only during the creation of the
	 * {@link StringGenerator}!). This {@link InputStream} can be from a text
	 * file where each line is a new pool item to generate from.
	 */
	public StringGenerator(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream);
		pool = new ArrayList<>();
		while (scanner.hasNext()) {
			pool.add(scanner.next());
		}
		scanner.close();
	}

	public StringGenerator forPoolFromGeneratorResourceFile() {
		String fileName = createGeneratorResourceFileName();
		InputStream inputStream = getClass().getResourceAsStream(fileName);
		return new StringGenerator(inputStream);
	}
	
	public StringGenerator forPool(String textWithMultipleLines) {
		return new StringGenerator(textWithMultipleLines);
	}
	
	public StringGenerator forPool(List<String> pool) {
		return new StringGenerator(pool);
	}
	
	public StringGenerator forPool(InputStream inputStream) {
		return new StringGenerator(inputStream);
	}

	private String createGeneratorResourceFileName() {
		return this.getClass().getSimpleName() + ".txt";
	}

	@Override
	public String generate() {
		int randomIndex = ThreadLocalRandom.current().nextInt(pool.size());
		return pool.get(randomIndex);
	}
}
