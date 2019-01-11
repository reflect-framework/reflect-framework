package nth.reflect.fw.gui.factory;

/**
 * A {@link Contractor} is an <a href=
 * "https://en.wikipedia.org/wiki/Abstract_factory_pattern">AbstractFactory</a>
 * and can indicate ({@link #canMake(MAKE_INFORMATION)}) if he can do a job
 * ({@link #create(MAKE_INFORMATION)}). See also {@link MainContractor}
 * 
 * @author nilsth
 * @param <MAKE_TYPE>
 *            The type of thing that needs to be made
 * @param <MAKE_INFORMATION>
 *            Information needed to create an object or to decide if the
 *            {@link Contractor} make it
 *
 */
public interface Contractor<MAKE_TYPE, MAKE_INFORMATION> {

	public boolean canMake(MAKE_INFORMATION makeInformation);

	public MAKE_TYPE create(MAKE_INFORMATION makeInformation);
}
