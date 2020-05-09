package nth.reflect.fw.layer5provider.reflection.behavior.disabled;

import nth.reflect.fw.ReflectFramework;

/**
 * If the code has both a "disabled annotation" and a "disabled method", the
 * {@link ReflectFramework} work will disable the item when on of them is true (if the user is not
 * authorized by the definition of the {@link Disabled} annotation OR if the
 * disabled method returns true).
 * 
 * @author nilsth
 *
 */
public class DisabledOrModel implements DisabledModel {

	private DisabledAnnotationModel disabledAnnotationModel;
	private DisabledMethodModel disabledMethodModel;

	public DisabledOrModel(DisabledAnnotationModel disabledAnnotationModel,
			DisabledMethodModel disabledMethodModel) {
		this.disabledAnnotationModel = disabledAnnotationModel;
		this.disabledMethodModel = disabledMethodModel;
	}

	@Override
	public boolean isDisabled(Object obj) {
		return  disabledAnnotationModel.isDisabled(obj)|| disabledMethodModel.isDisabled(obj);
	}

}
