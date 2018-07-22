package nth.reflect.fw.layer5provider.reflection.behavior.hidden;


public class HiddenFalseModel implements HiddenModel {

	private static HiddenFalseModel instance;

	//Singleton to prevent a lot of objects being created
	public static HiddenFalseModel getInstance() {
		if (instance == null) {
			instance = new HiddenFalseModel();
		}
		return instance;
	}

	private HiddenFalseModel() {
	}
	

	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		return false;
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		return false;
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return false;
	}

}
