package nth.reflect.fw.infrastructure.random;

import java.util.List;
import java.util.Set;

import nth.reflect.fw.infrastructure.random.generator.collection.ListGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.SetGenerator;
import nth.reflect.fw.infrastructure.random.generator.collection.StringBuilderGenerator;

public abstract class RandomGenerator<T> {

	public abstract T generate();

	public List<T> generateList(int size) {
		return new ListGenerator<T>(this, size).generate();
	}

	public List<T> generateList(int min, int max) {
		return new ListGenerator<T>(this, min, max).generate();
	}

	public Set<T> generateSet(int size) {
		return new SetGenerator<T>(this, size).generate();
	}

	public Set<T> generateSet(int min, int max) {
		return new SetGenerator<T>(this, min, max).generate();
	}

	public String generateString(int generationTimes) {
		return new StringBuilderGenerator<T>(this, generationTimes).generate();
	}

	public String generateString(int generationTimes, String seperator) {
		return new StringBuilderGenerator<T>(this, generationTimes, seperator).generate();
	}

	public String generateString(int generationTimesMin, int generationTimesMax) {
		return new StringBuilderGenerator<T>(this, generationTimesMin, generationTimesMax).generate();
	}

	public String generateString(int generationTimesMin, int generationTimesMax, String seperator) {
		return new StringBuilderGenerator<T>(this, generationTimesMin, generationTimesMax, seperator).generate();
	}

}