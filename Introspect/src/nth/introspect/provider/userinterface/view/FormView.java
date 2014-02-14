package nth.introspect.provider.userinterface.view;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.FormModeType;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface FormView extends View {

	public ReadOnlyValueModel getDomainValueModel();

	public FormModeType getFormMode();

	public MethodInfo getMethodInfo();

	public Object getServiceObject();
}
