package nth.introspect.ui.view;

import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;

public interface TableView extends MethodView{

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();

	public UserInterfaceContainer getuserInterfaceContainer();

}
