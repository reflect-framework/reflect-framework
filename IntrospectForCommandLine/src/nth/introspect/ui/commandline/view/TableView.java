package nth.introspect.ui.commandline.view;

import java.util.Collection;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.layer5provider.domain.info.DomainInfoProvider;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;
import nth.introspect.layer5provider.domain.info.property.PropertyInfo;
import nth.introspect.layer5provider.domain.info.property.TableOrderComparator;
import nth.introspect.layer5provider.domain.info.property.TableVisibleFilter;
import nth.introspect.ui.commandline.domain.table.Row;
import nth.introspect.ui.commandline.domain.table.Table;

public class TableView extends CommandLineView {

	private final Table table;

	public TableView(DomainInfoProvider domainInfoProvider, MethodInfo methodInfo, Collection<?> collection) {
		//get propertyInfos
		TableVisibleFilter propertyInfoFilter = new TableVisibleFilter();
		TableOrderComparator propertyInfoComparator = new TableOrderComparator();
		Class<?> returnClass = methodInfo.getReturnType().getTypeOrGenericCollectionType();
		List<PropertyInfo> propertyInfos = domainInfoProvider.getPropertyInfos(returnClass, propertyInfoFilter, propertyInfoComparator);

		table = new Table();
		
		// empty row
		Row row = table.addRow();
		row.addCell("", Table.MAX_WIDTH_IN_COLS);

		if (propertyInfos.size() > 0) {
			// create table header
			row = table.addRow();
			int columnWidth = (Table.MAX_WIDTH_IN_COLS - propertyInfos.size() + 1) / propertyInfos.size();
			for (PropertyInfo propertyInfo : propertyInfos) {
				row.addCell(propertyInfo.getText(), columnWidth);
			}
			// add line
			StringBuffer line = new StringBuffer();
			for (int i = 0; i < Table.MAX_WIDTH_IN_COLS; i++) {
				line.append("-");
			}
			row = table.addRow();
			row.addCell(line.toString(), Table.MAX_WIDTH_IN_COLS);

			// add all objects
			for (Object object : collection) {
				row = table.addRow();
				for (PropertyInfo propertyInfo : propertyInfos) {
					String value = propertyInfo.getFormatedValue(object);
					row.addCell(value, columnWidth);
				}
			}
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
