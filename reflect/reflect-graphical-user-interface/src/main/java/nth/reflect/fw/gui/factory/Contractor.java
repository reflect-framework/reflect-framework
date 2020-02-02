package nth.reflect.fw.gui.factory;

import java.util.Optional;

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

	public Optional<MAKE_TYPE> create(MAKE_INFORMATION makeInformation);
}
