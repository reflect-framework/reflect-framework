package nth.introspect.ui.commandline.view;

import java.util.List;

import nth.introspect.generic.filter.Filter;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfoComparator;
import nth.introspect.ui.commandline.domain.table.Row;
import nth.introspect.ui.commandline.domain.table.Table;

public class FormView extends CommandLineView {

	private Table table;

	public FormView(ReflectionProvider reflectionProvider, ActionMethodInfo actionMethodInfo, Object domainObject) {
		table = new Table();
		// empty row
		Row row = table.addRow();
		row.addCell("", Table.MAX_WIDTH_IN_COLS);

		// get propertyInfos
		Filter<PropertyInfo> propertyInfoFilter = null; // TODO only show visible properties;
		PropertyInfoComparator propertyInfoComparator = new PropertyInfoComparator();
		Class<?> returnClass = actionMethodInfo.getReturnType().getTypeOrGenericCollectionType();
		List<PropertyInfo> propertyInfos = reflectionProvider.getPropertyInfos(returnClass, propertyInfoFilter, propertyInfoComparator);

		// add properties to form
		for (PropertyInfo propertyInfo : propertyInfos) {
			row = table.addRow();
			row.addCell(propertyInfo.getDisplayName(), 18);
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
