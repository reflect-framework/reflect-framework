package nth.reflect.fw.ui.style;

import nth.reflect.fw.ui.style.basic.Color;
import nth.reflect.fw.ui.style.basic.Font;
import nth.reflect.fw.ui.style.control.ApplicationToolbarStyle;
import nth.reflect.fw.ui.style.control.ApplicationToolbarTitleStyle;
import nth.reflect.fw.ui.style.control.ListStyle;
import nth.reflect.fw.ui.style.control.MenuStyle;
import nth.reflect.fw.ui.style.control.TabContainerStyle;
import nth.reflect.fw.ui.style.control.TabToolbarButtonStyle;
import nth.reflect.fw.ui.style.control.TabToolbarStyle;
import nth.reflect.fw.ui.style.control.ToolbarIconStyle;
/** 
 * @Depricated use RfxUtil
 *
 */
@Deprecated
public class MaterialStyle {

	private final MaterialColorSet primaryColors;
	private final MaterialColorSet primaryLightColors;
	private final MaterialColorSet accentColors;
	private final MaterialColorSet contentColors;
	private final DisplayScale displayScale;
	private final DisplaySize displaySize;

	/**
	 * 
	 * @param primaryColor
	 *            a color that is prominent visible (i.e. background color of
	 *            application toolbar
	 * @param accentColor
	 *            a color that is used for thinks that need attention (i.e.
	 *            important buttons or controls that have focus
	 * @param contentColor
	 *            a color used for the background of the content (either white
	 *            or black)
	 * @param displayType
	 */
	public MaterialStyle(Color primaryColor, Color accentColor, Color contentColor,
			DisplayType displayType) {
		verifyColorNotBlackWhite(primaryColor);
		this.primaryColors = new MaterialColorSet(primaryColor);
		this.primaryLightColors = new MaterialColorSet(primaryColor.deriveDarknes(1.2));
		verifyColorNotBlackWhite(accentColor);
		this.accentColors = new MaterialColorSet(accentColor);
		verifyColorBlackWhite(contentColor);
		this.contentColors = new MaterialColorSet(contentColor);
		this.displayScale = displayType.getScale();
		this.displaySize = displayType.getSize();
	}

	private void verifyColorBlackWhite(Color contentColor) {
		if (!Color.BLACK.equals(contentColor) && !Color.WHITE.equals(contentColor)) {
			throw new RuntimeException("Content color must be black or white");
		}
	}

	private void verifyColorNotBlackWhite(Color color) {
		if (Color.BLACK.equals(color) || Color.WHITE.equals(color)) {
			throw new RuntimeException("Primary color or Accent color can not be black or white");
		}

	}

	public ApplicationToolbarStyle getApplicationToolbarStyle() {
		return new ApplicationToolbarStyle() {

			@Override
			public Color getBackgroundColor() {
				return primaryColors.getBackground();
			}

			@Override
			public int getHeight() {
				return displayScale.scale(56);
			}
		};
	}

	public ToolbarIconStyle getApplicationToolbarIconStyle() {
		return new ToolbarIconStyle() {

			@Override
			public int getPadding() {
				return displayScale.scale(16);
			}

			@Override
			public Color getColor() {
				return primaryColors.getForeground1();
			}

			@Override
			public Color getPressedColor() {
				return primaryColors.getBackgroundHighLighted();
			}

			@Override
			public int getSize() {
				return displayScale.scale(20);
			}

			@Override
			public Font getFont() {
				return MaterialFont.getFontAwesome();
			}

		};
	}

	public ApplicationToolbarTitleStyle getApplicationToolbarTitleStyle() {
		return new ApplicationToolbarTitleStyle() {

			@Override
			public Color getTextColor() {
				return primaryColors.getForeground1();
			}

			@Override
			public Font getFont() {
				return MaterialFont.getTitle(displayScale);
			}

		};
	}

	public MenuStyle getMenuStyle() {
		return new MenuStyle() {

			@Override
			public MenuType getMenuType() {
				return MenuType.getForDisplaySize(displaySize);
			}
		};

	}

	public ListStyle getListSingleLineStyle() {
		return new ListStyle() {

			@Override
			public int getMinHeight() {
				return displayScale.scale(48);
			}


			@Override
			public int getPaddingLeft() {
				return displayScale.scale(16);
			}

			@Override
			public int getPaddingRight() {
				return displayScale.scale(16);
			}

			@Override
			public int getIndent() {
				return displayScale.scale(32);
			}

			@Override
			public Color getBackgroundColor() {
				return contentColors.getBackground();
			}

			

			@Override
			public Color getSelectedBackGroundColor() {
				return contentColors.getBackgroundHighLighted();
			}

			@Override
			public Color getTextColor() {
				return contentColors.getForeground1();
			}


			@Override
			public Font getTitleFont() {
				return MaterialFont.getTitle(displayScale);
			}


			@Override
			public Font getSubHeadingFont() {
				return MaterialFont.getSubHeading(displayScale);
			}

		};
	}

	public TabContainerStyle getTabContainerStyle() {
		return new TabContainerStyle() {

			@Override
			public Color getBackground() {
				return contentColors.getBackground();
			}
		};
	}

	public TabToolbarStyle getTabToolbarStyle() {
		return new TabToolbarStyle() {

			@Override
			public int getHeight() {
				return displayScale.scale(56);
			}

			@Override
			public Color getBackGroundColor() {
				return primaryLightColors.getBackground();
			}

			@Override
			public Color getPressedColor() {
				return primaryLightColors.getBackgroundHighLighted();
			}
		};
	}

	public TabToolbarButtonStyle getTabToolbarButtonStyle() {
		return new TabToolbarButtonStyle() {

			@Override
			public Font getFont() {
				return MaterialFont.getButton(displayScale);
			}

			@Override
			public Color getTextColor() {
				return primaryLightColors.getForeground1();
			}
		};
	}

	public ToolbarIconStyle getTabToolbarIconStyle() {
		return new ToolbarIconStyle() {

			@Override
			public int getPadding() {
				return displayScale.scale(16);
			}

			@Override
			public Color getColor() {
				return primaryLightColors.getForeground1();
			}

			@Override
			public Color getPressedColor() {
				return primaryLightColors.getBackgroundHighLighted();
			}

			@Override
			public int getSize() {
				return displayScale.scale(20);
			}

			@Override
			public Font getFont() {
				return MaterialFont.getFontAwesome();
			}
		};
	}


}
