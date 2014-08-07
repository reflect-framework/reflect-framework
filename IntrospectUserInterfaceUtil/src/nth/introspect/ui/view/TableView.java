package nth.introspect.ui.view;

import nth.introspect.valuemodel.ReadOnlyValueModel;

public interface TableView extends MethodView{

	public ReadOnlyValueModel getSelectedRowModel();

	public ReadOnlyValueModel getAllRowsModel();


}
