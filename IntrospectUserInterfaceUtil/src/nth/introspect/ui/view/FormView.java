package nth.introspect.ui.view;

import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface FormView extends MethodView {

	public ReadOnlyValueModel getDomainValueModel();

	public FormMode getFormMode();

	public Object getDomainObject();

}