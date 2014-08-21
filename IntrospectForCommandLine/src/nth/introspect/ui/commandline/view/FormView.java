package nth.introspect.ui.commandline.view;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.FormOrderComparator;
import nth.introspect.provider.domain.info.property.PropertyInfo;
import nth.introspect.ui.commandline.domain.table.Row;
import nth.introspect.ui.commandline.domain.table.Table;

public class FormView extends CommandLineView {

	private Table table;

	public FormView(MethodInfo methodInfo, Object domainObject) {
		table = new Table();
		// empty row
		Row row = table.addRow();
		row.addCell("", Table.MAX_WIDTH_IN_COLS);

		// get propertyInfos
		Filter<PropertyInfo> propertyInfoFilter = null; // TODO only show visible properties;
		FormOrderComparator propertyInfoComparator = new FormOrderComparator();
		Class<?> returnClass = methodInfo.getReturnType().getTypeOrGenericCollectionType();
		List<PropertyInfo> propertyInfos = Introspect.getDomainInfoProvider().getPropertyInfos(returnClass, propertyInfoFilter, propertyInfoComparator);

		// add properties to form
		for (PropertyInfo propertyInfo : propertyInfos) {
			row = table.addRow();
			row.addCell(propertyInfo.getText(), 18);
			row.addCell(propertyInfo.getValue(domainObject).toString(), 60);
		}
	}

	@Override
	public String toString() {
		return table.toString();
	}

	@Override
	public void onViewActivate() {
		// do nothing
	}

}
