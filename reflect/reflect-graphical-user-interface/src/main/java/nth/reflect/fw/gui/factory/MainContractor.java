package nth.reflect.fw.gui.factory;

/**
 * A {@link MainContractor} is a {@link Contractor} that knows
 * Sub{@link Contractor}'s and delegates the creation of objects to the first
 * Sub{@link Contractor} that can do the job.
 * 
 * @author nilsth
 * @param <MAKE_TYPE>
 *            The type of thing that needs to be made
 * @param <MAKE_INFORMATION>
 *            Information needed to create an object or to decide if the
 *            {@link Contractor} make the
 *
 */
public class MainContractor<MAKE_TYPE, MAKE_INFORMATION> implements Contractor<MAKE_TYPE, MAKE_INFORMATION> {

	private final Contractor<MAKE_TYPE, MAKE_INFORMATION>[] subContractors;

	@SafeVarargs
	public MainContractor(Contractor<MAKE_TYPE, MAKE_INFORMATION>... subContractors) {
		this.subContractors = subContractors;
	}

	@Override
	public boolean canMake(MAKE_INFORMATION makeInformation) {
		for (Contractor<MAKE_TYPE, MAKE_INFORMATION> contractor : subContractors) {
			if (contractor.canMake(makeInformation)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public MAKE_TYPE create(MAKE_INFORMATION makeInformation) {
		for (Contractor<MAKE_TYPE, MAKE_INFORMATION> contractor : subContractors) {
			if (contractor.canMake(makeInformation)) {
				return contractor.create(makeInformation);
			}
		}
		throw new RuntimeException(this.getClass().getCanonicalName() + " can not find a sub-factory to create: "
				+ makeInformation.toString());
	}

}
