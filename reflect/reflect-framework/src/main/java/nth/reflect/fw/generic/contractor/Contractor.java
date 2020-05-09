package nth.reflect.fw.generic.contractor;

/**
 * A {@link Contractor} is an <a href=
 * "https://en.wikipedia.org/wiki/Abstract_factory_pattern">AbstractFactory</a>
 * and can indicate ({@link #canCreate(MAKE_INFORMATION)}) if it can do a job
 * ({@link #create(MAKE_INFORMATION)}). See also {@link DelegatingContractor}
 * 
 * @author nilsth
 * @param <MAKE_TYPE>        The type of thing that needs to be made
 * @param <MAKE_INFORMATION> Information needed to create an object or to decide
 *                           if the {@link Contractor} can create it
 *
 */
public interface Contractor<MAKE_TYPE, MAKE_INFORMATION> {

	public boolean canCreate(MAKE_INFORMATION makeInformation);

	/**
	 * Factory method. Must return a value (not null) or throw an exception.
	 * {@link Contractor#canCreate(Object)} should be called before calling this
	 * method to ensure the implementation of the {@link Contractor} interface can
	 * handle the creation.
	 * 
	 * @param makeInformation
	 * @return something that was created
	 */
	public MAKE_TYPE create(MAKE_INFORMATION makeInformation);
}
