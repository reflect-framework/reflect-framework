package nth.introspect.ui.commandline.view;

import java.util.List;

import nth.introspect.ui.commandline.domain.command.Command;
import nth.introspect.ui.commandline.domain.command.Parameter;
import nth.introspect.ui.commandline.domain.table.Row;
import nth.introspect.ui.commandline.domain.table.Table;

public class HelpView extends CommandLineView {

	private final Table table;

	public HelpView(String message, List<Command> commands) {
		table = new Table();

		Row row = table.addRow();
		row.addCell(message, Table.MAX_WIDTH_IN_COLS);
		
		row = table.addRow();
		row.addCell("", Table.MAX_WIDTH_IN_COLS);
		
		for (Command command : commands) {
			row = table.addRow();
			row.addCell(command.getUsage(), Table.MAX_WIDTH_IN_COLS);

			String description = command.getMethodInfo().getDescription();
			if (description != null && !description.trim().isEmpty()) {
				row = table.addRow();
				row.addCell("", Table.INDENT);
				row.addCell("Description: " + description, Table.MAX_WIDTH_IN_COLS - Table.INDENT - 1);
			}

			List<Parameter> parameters = command.getParameters();
			if (parameters.size() > 0) {
				if (description != null && !description.trim().isEmpty()) {
					row = table.addRow();
					row.addCell("", Table.INDENT);
					row.addCell("Parameters:", Table.MAX_WIDTH_IN_COLS - Table.INDENT - 1);
				}
			}

			for (Parameter parameter : parameters) {
				row = table.addRow();
				row.addCell("", Table.INDENT * 2);
				row.addCell(parameter.getName(), 35);
				row.addCell(parameter.getDescription(), 35);
			}

			// add empty row
			table.addRow();
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
