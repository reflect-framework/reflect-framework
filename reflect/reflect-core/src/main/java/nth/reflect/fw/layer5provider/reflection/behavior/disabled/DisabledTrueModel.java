package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

public class DisabledTrueModel implements DisabledModel {

	private static DisabledTrueModel instance;

	//Singleton to prevent a lot of objects being created
	public static DisabledTrueModel getInstance() {
		if (instance == null) {
			instance = new DisabledTrueModel();
		}
		return instance;
	}

	private DisabledTrueModel() {
	}
	
	@Override
	public boolean isDisabled(Object obj) {
		return true;
	}

}
