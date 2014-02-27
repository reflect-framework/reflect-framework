package nth.introspect.provider.userinterface.view;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface FormView extends View {

	public ReadOnlyValueModel getDomainValueModel();

	public boolean isFormReadOnly();//FIXME: replace in FormMode getFormMode (FormMode enum {READ_ONLY_MODE, EDIT_MODE})

	public MethodInfo getMethodInfo();

	public Object getServiceObject();
}
