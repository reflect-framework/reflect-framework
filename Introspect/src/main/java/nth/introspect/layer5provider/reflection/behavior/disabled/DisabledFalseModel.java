package nth.introspect.layer5provider.reflection.behavior.disabled;

public class DisabledFalseModel implements DisabledModel {

	private static DisabledFalseModel instance;

	//Singleton to prevent a lot of objects being created
	public static DisabledFalseModel getInstance() {
		if (instance == null) {
			instance = new DisabledFalseModel();
		}
		return instance;
	}

	private DisabledFalseModel() {
	}
	
	@Override
	public boolean isDisabled(Object obj) {
		return false;
	}

}
