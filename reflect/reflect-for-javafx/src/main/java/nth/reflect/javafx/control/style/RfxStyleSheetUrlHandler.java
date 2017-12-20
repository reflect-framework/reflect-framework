package nth.reflect.javafx.control.style;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColorSetCssName;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.button.RfxContentBottomToolbarButton;
import nth.reflect.javafx.control.button.RfxContentButton;
import nth.reflect.javafx.control.button.RfxPrimaryButton;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeCell;
import nth.reflect.javafx.control.itemtreelist.RfxItemTreeView;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.table.RfxTable;
import nth.reflect.javafx.control.toolbar.RfxToolbar;
import nth.reflect.javafx.control.view.form.RfxContentBottomToolbar;
import nth.reflect.javafx.control.view.form.field.RfxTextField;
import nth.reflect.javafx.control.view.table.RfxTableView;
import nth.reflect.javafx.control.window.content.RfxContentPane;
import nth.reflect.javafx.control.window.mainmenu.RfxMainMenuPane;

public class RfxStyleSheetUrlHandler extends UrlProvider {

	private String css;

	public RfxStyleSheetUrlHandler(ReflectApplicationForJavaFX application) {

		RfxStyleSheet styleSheet = new RfxStyleSheet();

		appendColorDefinitions(styleSheet, application);
		appendPanes(styleSheet);
		appendControls(styleSheet);
		
		System.out.println(styleSheet.toString());

		css = styleSheet.toString();
	}

	private void appendControls(RfxStyleSheet styleSheet) {
		appendToolbars(styleSheet);
		appendButtons(styleSheet);
		RfxTextField.appendStyleGroups(styleSheet);
		RfxItemTreeView.appendStyleGroups(styleSheet);
		RfxItemTreeCell.appendStyleGroups(styleSheet);
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

	private void appendColorDefinitions(RfxStyleSheet styleSheet, ReflectApplicationForJavaFX application) {
		RfxStyleGroup colorDefintion = styleSheet.addStyleGroup(RfxStyleSelector.createFor("*"));
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.PRIMARY,new MaterialColorSet(application.getPrimaryColor()));
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.ACCENT,new MaterialColorSet(application.getAccentColor()));
		colorDefintion.getProperties().setColorVariables(MaterialColorSetCssName.CONTENT, new MaterialColorSet(application.getContentColor().getColor()));
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
