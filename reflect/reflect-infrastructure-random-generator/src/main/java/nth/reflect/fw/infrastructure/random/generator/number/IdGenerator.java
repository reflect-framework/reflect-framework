package nth.reflect.fw.infrastructure.random.generator.number;

import nth.reflect.fw.infrastructure.random.RandomGenerator;

/**
 * A {@link RandomGenerator} that creates integers, that is increased in value
 * every time the {@link #generate()} method is called. It can be used for
 * simulation unique id's. The ugly part: it is not random so should it have a
 * {@link nth.reflect.fw.infrastructure.random.Random#idGenerator()} method???
 * 
 * @author nilsth
 *
 */
public class IdGenerator implements RandomGenerator<Integer> {

	private int integer = 0;

	@Override
	public Integer generate() {
		return integer++;
	}

}
