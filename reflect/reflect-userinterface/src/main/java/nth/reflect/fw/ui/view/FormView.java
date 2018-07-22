package nth.reflect.fw.ui.view;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public interface FormView extends MethodView {

	public ReadOnlyValueModel getDomainValueModel();

	public FormMode getFormMode();

	public Object getDomainObject();
	
	public UserInterfaceContainer getUserInterfaceContainer();//TODO can we get rid of this? We do not want to have a dual dependency between the userInterfaceContainer and sub classes of the UserInterfaceController

}
