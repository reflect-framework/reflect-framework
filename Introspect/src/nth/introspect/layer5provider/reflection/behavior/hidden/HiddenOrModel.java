package nth.introspect.layer5provider.reflection.behavior.hidden;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenAnnotationModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenMethodModel;
import nth.introspect.layer5provider.reflection.behavior.hidden.HiddenModel;

/**
 * If the code has both a "hidden annotation" and a "hidden method", the
 * {@link IntrospectFramework} work will disable the item when on of them is true (if the user is not
 * authorized by the definition of the {@link Hidden} annotation OR if the
 * hidden method returns true).
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
		boolean hiddenByAnnotation = hiddenAnnotationModel.isPropertyHiddenInForm(obj);
		boolean hiddenByMethod = hiddenMethodModel.isPropertyHiddenInForm(obj);
		return hiddenByAnnotation || hiddenByMethod;
	}

	@Override
	public boolean isPropertyHiddenInTable() {
		boolean hiddenByAnnotation = hiddenAnnotationModel.isPropertyHiddenInTable();
		boolean hiddenByMethod = hiddenMethodModel.isPropertyHiddenInTable();//should never accure
		return hiddenByAnnotation || hiddenByMethod;
	}

	@Override
	public boolean isHiddenActionMethod(Object obj) {
		boolean hiddenByAnnotation = hiddenAnnotationModel.isHiddenActionMethod(obj);
		boolean hiddenByMethod = hiddenMethodModel.isHiddenActionMethod(obj);
		return hiddenByAnnotation || hiddenByMethod;
	}

}
