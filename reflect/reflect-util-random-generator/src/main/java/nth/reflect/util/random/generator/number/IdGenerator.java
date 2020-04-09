package nth.reflect.util.random.generator.number;

import nth.reflect.util.random.RandomGenerator;

/**
 * A {@link RandomGenerator} that creates integers, that is increased in value
 * every time the {@link #generate()} method is called. It can be used for
 * simulation unique id's. The ugly part: it is not random so should it have a
 * {@link nth.reflect.util.random.Random#id()} method???
 * 
 * @author nilsth
 *
 */
public class IdGenerator extends RandomGenerator<Integer> {

	private int integer = 0;

	@Override
	public Integer generate() {
		return integer++;
	}

}
