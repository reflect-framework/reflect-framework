package nth.introspect.layer5provider.reflection.behavior.hidden;

import nth.introspect.documentation.IntrospectFramework;

/**
 * If the code has both a "hidden annotation" and a "hidden method", the
 * {@link IntrospectFramework} work will disable the item when on of them is
 * true (if the user is not authorized by the definition of the {@link Hidden}
 * annotation OR if the hidden method returns true).
 * 
 * @author nilsth
 *
 */
public class HiddenOrModel implements HiddenModel {

	private HiddenAnnotationModel hiddenAnnotationModel;
	private HiddenMethodModel hiddenMethodModel;

	public HiddenOrModel(HiddenAnnotationModel hiddenAnnotationModel,
			HiddenMethodModel hiddenMethodModel) {
		this.hiddenAnnotationModel = hiddenAnnotationModel;
		this.hiddenMethodModel = hiddenMethodModel;
	}

	@Override
	public boolean isPropertyHiddenInForm(Object obj) {
		return hiddenAnnotationModel.isPropertyHiddenInForm(obj)
				|| hiddenMethodModel.isPropertyHiddenInForm(obj);
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		return hiddenAnnotationModel.isPropertyHiddenInTable()
				|| hiddenMethodModel.isPropertyHiddenInTable();
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		return hiddenAnnotationModel.isHiddenActionMethod(obj)
				|| hiddenMethodModel.isHiddenActionMethod(obj);
	}

}