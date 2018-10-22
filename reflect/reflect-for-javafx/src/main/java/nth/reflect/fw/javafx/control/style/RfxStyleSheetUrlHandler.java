package nth.reflect.fw.javafx.control.style;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.fw.javafx.control.button.RfxContentButton;
import nth.reflect.fw.javafx.control.button.RfxPrimaryButton;
import nth.reflect.fw.javafx.control.itemtreelist.RfxItemTreeCell;
import nth.reflect.fw.javafx.control.itemtreelist.RfxItemTreePanel;
import nth.reflect.fw.javafx.control.list.RfxList;
import nth.reflect.fw.javafx.control.tab.form.PropertiesPanel;
import nth.reflect.fw.javafx.control.tab.form.RfxContentBottomToolbar;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyLabel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyLabelAndFieldPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyPanel;
import nth.reflect.fw.javafx.control.tab.form.proppanel.PropertyValidationMessages;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.ManyToOneOrManyField;
import nth.reflect.fw.javafx.control.tab.form.proppanel.field.TextField;
import nth.reflect.fw.javafx.control.table.RfxTable;
import nth.reflect.fw.javafx.control.toolbar.RfxToolbar;
import nth.reflect.fw.javafx.control.window.content.RfxContentPane;
import nth.reflect.fw.javafx.control.window.mainmenu.RfxMainMenuPane;
import nth.reflect.fw.layer5provider.url.UrlProvider;
import nth.reflect.fw.ui.style.MaterialColorPalette;
import nth.reflect.fw.ui.style.MaterialColorSetCssName;
import nth.reflect.fw.ui.style.ReflectColors;
import nth.reflect.fw.ui.style.basic.Color;

public class RfxStyleSheetUrlHandler extends UrlProvider {

	private String css;

	public RfxStyleSheetUrlHandler(ReflectApplicationForJavaFX applicationForJavaFX) {
		
		RfxStyleSheet styleSheet = new RfxStyleSheet();

		appendColorDefinitions(styleSheet);
		appendPanes(styleSheet);
		appendControls(styleSheet);
		
		System.out.println(styleSheet.toString());

		css = styleSheet.toString();
	}

	private void appendControls(RfxStyleSheet styleSheet) {
		appendToolbars(styleSheet);
		appendButtons(styleSheet);
		appendPropertyPanel(styleSheet);
		appendFields(styleSheet);
		RfxItemTreePanel.appendStyleGroups(styleSheet);
		RfxItemTreeCell.appendStyleGroups(styleSheet);
	}

	private void appendFields(RfxStyleSheet styleSheet) {
		TextField.appendStyleGroups(styleSheet);
		ManyToOneOrManyField.appendStyleGroups(styleSheet);
	}

	private void appendPropertyPanel(RfxStyleSheet styleSheet) {
		PropertiesPanel.appendStyleGroups(styleSheet);
		PropertyPanel.appendStyleGroups(styleSheet);
		PropertyLabelAndFieldPanel.appendStyleGroups(styleSheet);
		PropertyLabel.appendStyleGroups(styleSheet);
		PropertyValidationMessages.appendStyleGroups(styleSheet);
	}

	private void appendToolbars(RfxStyleSheet styleSheet) {
		RfxToolbar.appendStyleGroups(styleSheet);
		RfxContentBottomToolbar.appendStyleGroups(styleSheet);
	}

	private void appendButtons(RfxStyleSheet styleSheet) {
		RfxContentButton.appendStyleGroups(styleSheet);
		RfxContentBottomToolbarButton.appendStyleGroups(styleSheet);
		RfxPrimaryButton.appendStyleGroups(styleSheet);
	}

	private void appendPanes(RfxStyleSheet styleSheet) {
		RfxContentPane.appendStyleGroups(styleSheet);
		RfxList.appendStyleGroups(styleSheet);
		RfxTable.appendStyleGroups(styleSheet);
		RfxMainMenuPane.appendStyleGroups(styleSheet);
	}

	/**
	 * Fixme: get colors from {@link ReflectionProvider#getApplicationInfo().getColors() with annotaions in ReflectApplication class}
	 * @param styleSheet
	 * @param userInterfaceController
	 */
	private void appendColorDefinitions(RfxStyleSheet styleSheet) {
		//Fixme ReflectColors reflectColors = userInterfaceController.getColors();
		ReflectColors reflectColors = new ReflectColors(MaterialColorPalette.TEAL, MaterialColorPalette.ORANGE,
				Color.WHITE);
		RfxStyleGroup colorDefintion = styleSheet.addStyleGroup(RfxStyleSelector.createFor("*"));
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.PRIMARY,reflectColors.getPrimaryColors());
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.ACCENT,reflectColors.getAccentColors());
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.CONTENT, reflectColors.getContentColors());
	}

	@Override
	public String getProtocol() {
		return RfxStyleSheetUrl.PROTOCOL;
	}

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return new URLConnection(url) {

			@Override
			public void connect() throws IOException {
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return new ByteArrayInputStream(css.getBytes(StandardCharsets.UTF_8));
			}

		};
	}

}
