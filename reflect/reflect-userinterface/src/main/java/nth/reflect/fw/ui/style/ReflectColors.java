package nth.reflect.fw.ui.style;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.style.basic.Color;

/**
 * {@link ReflectFramework} applications with a graphical user interface use 3
 * main colors:
 * <ul>
 * <li>PrimaryBackgroundColor: A color that is prominent visible, e.g.: the
 * background color of the application tool bar</li>
 * <li>AccentBackGroundColor: A color that is used for thinks that need
 * attention (i.e. important buttons or controls that have focus</li>
 * <li>ContentBackgroundColor: A color used for the background of the content.
 * This color should preferably either be very light (e.g. White) or very dark
 * (e.g.Black)</li>
 * </ul>
 * 
 * <p>
 * <a href="https://material.io/design/">Google's Material Design</a> recommends
 * to use colors from color palettes that they recommend. See <a href=
 * "https://material.io/design/color/the-color-system.html#color-usage-palettes">Google's
 * Material Design Palettes</a> for more information.
 * </p>
 * 
 * <p>
 * Each color can be derived into a {@link ReflectColorSet}.
 * {@insert ReflectColorSet}
 * </p>
 * 
 * <p>
 * You can override the default colors by extending the
 * {@link GraphicalUserinterfaceController} and overriding the
 * {@link GraphicalUserinterfaceController#getColors()} method. You then must
 * return this {@link GraphicalUserinterfaceController} by overriding the
 * {@link ReflectApplication#getUserInterfaceControllerClass()}
 * </p>
 */

public class ReflectColors {

	private final ReflectColorSet primaryColors;
	private final ReflectColorSet accentColors;
	private final ReflectColorSet contentColors;

	/**
	 * Convenience method to get the {@link ReflectColors} form the
	 * {@link UserInterfaceContainer}
	 */
	public static ReflectColors getFrom(UserInterfaceContainer userInterfaceContainer) {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userinterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		return userinterfaceController.getColors();
	}

	/**
	 * @param primaryBackgroundColor
	 *            a color that is prominent visible, e.g.: the background color
	 *            of the application tool bar
	 * @param accentBackGroundColor
	 *            a color that is used for thinks that need attention (i.e.
	 *            important buttons or controls that have focus
	 * @param contentBackgroundColor
	 *            a color used for the background of the content. This color
	 *            should preferably either be very light (e.g. White) or very
	 *            dark (e.g.Black)
	 */
	public ReflectColors(Color primaryBackgroundColor, Color accentBackGroundColor, Color contentBackgroundColor) {
		this.primaryColors = new ReflectColorSet(primaryBackgroundColor);
		this.accentColors = new ReflectColorSet(accentBackGroundColor);
		this.contentColors = new ReflectColorSet(contentBackgroundColor);
	}

	public ReflectColorSet getPrimaryColors() {
		return primaryColors;
	}

	public ReflectColorSet getAccentColors() {
		return accentColors;
	}

	public ReflectColorSet getContentColors() {
		return contentColors;
	}

	// public ApplicationToolbarStyle getApplicationToolbarStyle() {
	// return new ApplicationToolbarStyle() {
	//
	// @Override
	// public Color getBackgroundColor() {
	// return primaryColors.getBackground();
	// }
	//
	// @Override
	// public int getHeight() {
	// return displayScale.scale(56);
	// }
	// };
	// }
	//
	// public ToolbarIconStyle getApplicationToolbarIconStyle() {
	// return new ToolbarIconStyle() {
	//
	// @Override
	// public int getPadding() {
	// return displayScale.scale(16);
	// }
	//
	// @Override
	// public Color getColor() {
	// return primaryColors.getForeground1();
	// }
	//
	// @Override
	// public Color getPressedColor() {
	// return primaryColors.getBackgroundHighLighted();
	// }
	//
	// @Override
	// public int getSize() {
	// return displayScale.scale(20);
	// }
	//
	// @Override
	// public Font getFont() {
	// return MaterialFont.getFontAwesome();
	// }
	//
	// };
	// }
	//
	// public ApplicationToolbarTitleStyle getApplicationToolbarTitleStyle() {
	// return new ApplicationToolbarTitleStyle() {
	//
	// @Override
	// public Color getTextColor() {
	// return primaryColors.getForeground1();
	// }
	//
	// @Override
	// public Font getFont() {
	// return MaterialFont.getTitle(displayScale);
	// }
	//
	// };
	// }
	//
	// public MenuStyle getMenuStyle() {
	// return new MenuStyle() {
	//
	// @Override
	// public MenuType getMenuType() {
	// return MenuType.getForDisplaySize(displaySize);
	// }
	// };
	//
	// }
	//
	// public ListStyle getListSingleLineStyle() {
	// return new ListStyle() {
	//
	// @Override
	// public int getMinHeight() {
	// return displayScale.scale(48);
	// }
	//
	// @Override
	// public int getPaddingLeft() {
	// return displayScale.scale(16);
	// }
	//
	// @Override
	// public int getPaddingRight() {
	// return displayScale.scale(16);
	// }
	//
	// @Override
	// public int getIndent() {
	// return displayScale.scale(32);
	// }
	//
	// @Override
	// public Color getBackgroundColor() {
	// return contentColors.getBackground();
	// }
	//
	// @Override
	// public Color getSelectedBackGroundColor() {
	// return contentColors.getBackgroundHighLighted();
	// }
	//
	// @Override
	// public Color getTextColor() {
	// return contentColors.getForeground1();
	// }
	//
	// @Override
	// public Font getTitleFont() {
	// return MaterialFont.getTitle(displayScale);
	// }
	//
	// @Override
	// public Font getSubHeadingFont() {
	// return MaterialFont.getSubHeading(displayScale);
	// }
	//
	// };
	// }
	//
	// public TabContainerStyle getTabContainerStyle() {
	// return new TabContainerStyle() {
	//
	// @Override
	// public Color getBackground() {
	// return contentColors.getBackground();
	// }
	// };
	// }
	//
	
	//
	// public TabToolbarButtonStyle getTabToolbarButtonStyle() {
	// return new TabToolbarButtonStyle() {
	//
	// @Override
	// public Font getFont() {
	// return MaterialFont.getButton(displayScale);
	// }
	//
	// @Override
	// public Color getTextColor() {
	// return primaryLightColors.getForeground1();
	// }
	// };
	// }
	//
	// public ToolbarIconStyle getTabToolbarIconStyle() {
	// return new ToolbarIconStyle() {
	//
	// @Override
	// public int getPadding() {
	// return displayScale.scale(16);
	// }
	//
	// @Override
	// public Color getColor() {
	// return primaryLightColors.getForeground1();
	// }
	//
	// @Override
	// public Color getPressedColor() {
	// return primaryLightColors.getBackgroundHighLighted();
	// }
	//
	// @Override
	// public int getSize() {
	// return displayScale.scale(20);
	// }
	//
	// @Override
	// public Font getFont() {
	// return MaterialFont.getFontAwesome();
	// }
	// };
	// }

}
