package nth.introspect.ui.style;

public enum MenuType {
	/**
	 * The navigation drawer is a panel that displays the app’s main navigation
	 * options on the left edge of the screen. It is hidden most of the time,
	 * but is revealed when the user swipes a finger from the left edge of the
	 * screen or, while at the top level of the app, the user touches the
	 * navigation icon in the action bar. The navigation drawer floats over the
	 * application content and the application content is grayed out and
	 * disabled when the navigation drawer is visible. It is used for narrow
	 * screens
	 */
	DRAWER,
	/**
	 * An embedded menu is a panel that displays the app’s main navigation
	 * options on the left edge of the screen. It is displayed most of the time,
	 * but its visibility can be toggled when the user touches the navigation
	 * icon in the action bar. The menu is visible next to the application
	 * content and the application content is visible and enabled when the menu
	 * panel is visible. It is used for wide screens
	 */
	EMBEDDED;

	public static MenuType getForDisplaySize(DisplaySize displaySize) {
		if (DisplaySize.NARROW == displaySize) {
			return DRAWER;
		} else {
			return EMBEDDED;
		}
	}
}
