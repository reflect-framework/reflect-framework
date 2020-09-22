package nth.reflect.util.random.generator.text;

public enum WhiteSpace {
	SPACE(' '), HORIZONTAL_TABULATION('\t'), LINE_FEED('\n'), VERTICAL_TABULATION('\u000B'), FORM_FEED('\f'),
	CARRIAGE_RETURN('\r'), FILE_SEPARATOR('\u001C'), GROUP_SEPARATOR('\u001D'), RECORD_SEPARATOR('\u001E'),
	UNIT_SEPARATOR('\u001F');

	private final Character character;

	private WhiteSpace(Character character) {
		this.character = character;
	}

	public Character getCharacter() {
		return character;
	}

	public static String charactersToString() {
		StringBuilder characters = new StringBuilder();
		for (WhiteSpace whiteSpace : values()) {
			characters.append(whiteSpace.getCharacter());
		}
		return characters.toString();
	}

}
