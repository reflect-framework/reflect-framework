package nth.reflect.fw.gui.factory;

import java.util.Optional;

/**
 * A {@link DelegatingContractor} is a {@link Contractor} that knows
 * Sub{@link Contractor}'s and delegates the creation of objects to the first
 * Sub{@link Contractor} that can do the job.
 * 
 * @author nilsth
 * @param <MAKE_TYPE>        The type of thing that needs to be created
 * @param <MAKE_INFORMATION> Information needed to create an object or to decide
 *                           if the {@link Contractor} create it
 *
 */
public class DelegatingContractor<MAKE_TYPE, MAKE_INFORMATION> implements Contractor<MAKE_TYPE, MAKE_INFORMATION> {

	private final Contractor<MAKE_TYPE, MAKE_INFORMATION>[] subContractors;

	@SafeVarargs
	public DelegatingContractor(Contractor<MAKE_TYPE, MAKE_INFORMATION>... subContractors) {
		this.subContractors = subContractors;
	}

	@Override
	public boolean canCreate(MAKE_INFORMATION makeInformation) {
		for (Contractor<MAKE_TYPE, MAKE_INFORMATION> contractor : subContractors) {
			if (contractor.canCreate(makeInformation)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Optional<MAKE_TYPE> create(MAKE_INFORMATION makeInformation) {
		for (Contractor<MAKE_TYPE, MAKE_INFORMATION> subContractor : subContractors) {
			if (subContractor.canCreate(makeInformation)) {
				Optional<MAKE_TYPE> result = subContractor.create(makeInformation);
				if (result.isPresent()) {
					return result;
				}
			}
		}
		return Optional.empty();
	}

}
