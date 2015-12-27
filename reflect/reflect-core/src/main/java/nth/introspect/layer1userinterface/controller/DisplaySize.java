package nth.introspect.layer1userinterface.controller;

public enum DisplaySize {
	SMALL, LARGE;

	public static DisplaySize forWidthInInches(int widthInInches) {
		if (widthInInches < 7) {
			return SMALL;
		} else {
			return LARGE;
		}
	}
}
