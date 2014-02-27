package nth.introspect.ui.view;

import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface TableView {

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();

	public MethodInfo getMethodInfo();

	public Object getServiceObject();

}
