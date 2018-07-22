package nth.reflect.fw.ui.view;

import nth.reflect.fw.generic.valuemodel.ReadOnlyValueModel;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;

public interface TableView extends MethodView{

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();

	public UserInterfaceContainer getUserInterfaceContainer();

}
