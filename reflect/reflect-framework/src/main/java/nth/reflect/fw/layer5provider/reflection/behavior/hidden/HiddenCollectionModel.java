package nth.reflect.fw.layer5provider.reflection.behavior.hidden;

public class HiddenCollectionModel implements HiddenModel {

	private HiddenModel hiddenModel;

	public HiddenCollectionModel(HiddenModel hiddenModel) {
		this.hiddenModel = hiddenModel;
	}

	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		return hiddenModel.isPropertyHiddenInForm(obj);
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		return true;//is hidden in table because property is a collection
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return hiddenModel.isHiddenActionMethod(obj);
	}

}
