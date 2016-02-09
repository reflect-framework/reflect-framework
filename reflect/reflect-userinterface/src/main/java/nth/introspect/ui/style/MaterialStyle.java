package nth.introspect.ui.style;

import java.awt.Color;
import java.awt.Font;

public class MaterialStyle {

	private final MaterialColors primaryDarkColors;
	private final MaterialColors primaryLightColors;
	private final MaterialColors primaryMediumColors;
	
	public final DisplayType displayType;
	public final MaterialList list;
	public final MaterialAppBar appBar;
	public final MenuType menuType;

	public MaterialStyle(MaterialColors primaryDarkColors, MaterialColors primaryMediumColors,
			MaterialColors primaryLightColors, MaterialColors accentColors,
			MaterialColors contentColors, DisplayType displayType) {
		this.primaryDarkColors = primaryDarkColors;
		this.primaryMediumColors = primaryMediumColors;
		this.primaryLightColors = primaryLightColors;
		this.displayType = displayType;
		this.list = new MaterialList();
		this.appBar = new MaterialAppBar();
		this.menuType=MenuType.getForDisplaySize(displayType.getSize());

	}

	/**
	 * See
	 * https://www.google.com/design/spec/components/lists.html#lists-actions
	 */

	public final class MaterialList {
		public final MaterialListSingleLine singleLine;

		public MaterialList() {
			singleLine = new MaterialListSingleLine();

		}

		public final int INDENT = 32;

		public final class MaterialListSingleLine {
			public final int HEIGHT = displayType.isDense() ? 40 : 48;
			public final int PADDING_LEFT = 16;
			public final int PADDING_RIGHT = 16;
			public final Font FONT = MaterialFont.SUBHEADING;
		}

	}

	/**
	 * See
	 * https://www.google.com/design/spec/layout/structure.html#structure-app
	 * -bar
	 * 
	 * @author nilsth
	 *
	 */
	public final class MaterialAppBar {
		public final int HEIGHT = (displayType.isDense()) ? 48 : 56;
		public final int leftPadding = 16;
		public final int rightPadding = 16;
		public final Color BACKGROUND = primaryDarkColors.getBackground();
		public final Color FOREGROUND1 = primaryDarkColors.getForeground1();
		public final MaterialAppBarTitle title;
		public final MaterialAppBarIcon icon ;
		public MaterialAppBar() {
			title= new MaterialAppBarTitle();
			icon = new MaterialAppBarIcon();	
		}

		public final class MaterialAppBarTitle {
			public final Font FONT = MaterialFont.TITLE;
			public final Color FOREGROUND = primaryDarkColors.getForeground1();

			public final class padding {
				public final int left = 20;
				public final int right = 20;
				public final int top = 16;
				public final int bottom = 20;
			}
		}

		public final class MaterialAppBarIcon {
			public final int PADDING = 16;
			public final int SIZE = displayType.isDense() ? 20 : 24;
			public final Color FOREGROUND1 = primaryDarkColors.getForeground1();
			public final Color PRESSED = primaryLightColors.getBackground();

		}
	}

}
