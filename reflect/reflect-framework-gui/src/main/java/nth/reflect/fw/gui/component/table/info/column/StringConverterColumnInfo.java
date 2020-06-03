package nth.reflect.fw.gui.component.table.info.column;

import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class StringConverterColumnInfo implements ColumnInfo {

	private final StringConverter<Object> stringConverter;

	public StringConverterColumnInfo(StringConverter<Object> stringConverter) {
		this.stringConverter = stringConverter;
	}

	@Override
	public String toString(Object rowValue) {
		String string = stringConverter.toString(rowValue);
		return string;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public boolean hasName() {
		return false;
	}
}
