package nth.introspect.layer5provider.reflection.behavior.hidden;


public class HiddenTrueModel implements HiddenModel {

	private static HiddenTrueModel instance;

	//Singleton to prevent a lot of objects being created
	public static HiddenTrueModel getInstance() {
		if (instance == null) {
			instance = new HiddenTrueModel();
		}
		return instance;
	}

	private HiddenTrueModel() {
	}
	

	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		return true;
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		return true;
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return true;
	}

}
