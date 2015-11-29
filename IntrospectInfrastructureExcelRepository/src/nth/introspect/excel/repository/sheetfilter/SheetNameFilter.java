package nth.introspect.excel.repository.sheetfilter;

import org.apache.poi.ss.usermodel.Sheet;

public class SheetNameFilter extends SheetFilter {

	private String sheetNameInLowerCase;

	public SheetNameFilter(String sheetName) {
		this.sheetNameInLowerCase = sheetName.toLowerCase();
	}

	@Override
	public boolean test(Sheet sheet) {
		return sheet.getSheetName().toLowerCase().equals(sheetNameInLowerCase);
	}

}
