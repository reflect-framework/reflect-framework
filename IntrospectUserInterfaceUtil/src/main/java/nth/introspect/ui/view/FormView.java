package nth.introspect.ui.view;

import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;

public interface FormView extends MethodView {

	public ReadOnlyValueModel getDomainValueModel();

	public FormMode getFormMode();

	public Object getDomainObject();
	
	public UserInterfaceContainer getuserInterfaceContainer();//TODO can we get rid of this? We do not want to have a dual dependency between the userInterfaceContainer and sub classes of the UserInterfaceController

}
