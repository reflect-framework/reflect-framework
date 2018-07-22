package nth.reflect.infra.excel.repository.sheetfilter;

import org.apache.poi.ss.usermodel.Sheet;

public class AllSheetsFilter extends SheetFilter {

	@Override
	public boolean test(Sheet sheet) {
		return true;
	}

}
