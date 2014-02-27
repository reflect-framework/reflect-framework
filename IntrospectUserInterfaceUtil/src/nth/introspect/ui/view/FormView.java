package nth.introspect.ui.view;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.userinterface.view.View;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface FormView extends View {

	public ReadOnlyValueModel getDomainValueModel();

	public FormMode getFormMode();

	public MethodInfo getMethodInfo();

	public Object getServiceObject();
}
