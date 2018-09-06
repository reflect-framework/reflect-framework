package nth.reflect.fw.infrastructure.randomfactory;

/**
 * A {@link Factory} that creates integers, that is increased in value every
 * time the {@link #create()} method is called. It can be used for simulation
 * unique id's
 * 
 * @author nilsth
 *
 */
public class IncIntFactory implements Factory<Integer> {

	private int integer = 0;

	@Override
	public Integer create() {
		return integer++;
	}

}
