package nth.reflect.fw.infrastructure.random.generator.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * The {@link ProbabilityGenerator} will generate a value of one of the given
 * {@link RandomGenerator}s depending on a given probability per
 * {@link RandomGenerator}.
 * 
 * @author nilsth
 *
 * @param <T>
 */
public class ProbabilityGenerator<T> extends RandomGenerator<T> {

	private final Map<RandomGenerator<T>, Integer> randomGeneratorsAndProbability;
	private int totalProbability;

	public ProbabilityGenerator(Map<RandomGenerator<T>, Integer> randomGeneratorsAndProbability) {
		this.randomGeneratorsAndProbability = new HashMap<>(randomGeneratorsAndProbability);
		totalProbability = randomGeneratorsAndProbability.values().stream().mapToInt(i -> i.intValue()).sum();
		if (totalProbability==0) throw new RuntimeException("The total probability must > 0.");
	}

	@Override
	public T generate() {
		int index = ThreadLocalRandom.current().nextInt(totalProbability);
		int probabilitySum = 0;
		RandomGenerator<T> lastRandomGenerator = null;
		for (RandomGenerator<T> randomGenerator : randomGeneratorsAndProbability.keySet()) {
			probabilitySum += randomGeneratorsAndProbability.get(randomGenerator);
			if (index < probabilitySum) {
				return randomGenerator.generate();
			} else {
				lastRandomGenerator = randomGenerator;
			}
		}
		return lastRandomGenerator.generate();
	}

}
