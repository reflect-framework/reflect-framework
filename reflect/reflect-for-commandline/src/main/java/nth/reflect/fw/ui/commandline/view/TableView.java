package nth.reflect.fw.ui.commandline.view;

import java.util.Collection;
import java.util.List;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.ui.commandline.domain.table.Row;
import nth.reflect.fw.ui.commandline.domain.table.Table;

public class TableView extends CommandLineView {

	private final Table table;

	public TableView(ReflectionProvider reflectionProvider, ActionMethodInfo actionMethodInfo, Collection<?> collection) {
		//get propertyInfos
		Class<?> returnClass = actionMethodInfo.getGenericReturnType();
		ClassInfo classInfo = reflectionProvider.getClassInfo(returnClass);
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSortedAndVisibleInTable();

		table = new Table();
		
		// empty row
		Row row = table.addRow();
		row.addCell("", Table.MAX_WIDTH_IN_COLS);

		if (propertyInfos.size() > 0) {
			// create table header
			row = table.addRow();
			int columnWidth = (Table.MAX_WIDTH_IN_COLS - propertyInfos.size() + 1) / propertyInfos.size();
			for (PropertyInfo propertyInfo : propertyInfos) {
				row.addCell(propertyInfo.getDisplayName(), columnWidth);
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


}
