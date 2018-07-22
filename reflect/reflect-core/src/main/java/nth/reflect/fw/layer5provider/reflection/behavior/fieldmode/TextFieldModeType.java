package nth.reflect.fw.layer5provider.reflection.behavior.fieldmode;

public enum TextFieldModeType {
	CHAR, PASSWORD, TEXT_AREA, RICH_TEXT_AREA, NUMBER;
	
	public static FieldModeType toFieldMode(TextFieldModeType textFieldModeType) {
		switch (textFieldModeType) {
		case CHAR:
			return FieldModeType.CHAR;
		case PASSWORD:
			return FieldModeType.PASSWORD;
		case TEXT_AREA:
			return FieldModeType.TEXT_AREA;
		case RICH_TEXT_AREA:
			return FieldModeType.RICH_TEXT_AREA;
		case NUMBER:
			return FieldModeType.NUMBER;
		}
		return FieldModeType.TEXT;
	}
}
