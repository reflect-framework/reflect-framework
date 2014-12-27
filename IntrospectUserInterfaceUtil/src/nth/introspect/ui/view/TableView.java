package nth.introspect.ui.view;

import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface TableView extends MethodView{

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();

	public UserInterfaceContainer getuserInterfaceContainer();

}
