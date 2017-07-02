package nth.reflect.javafx.control.style;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.control.list.mainmenu.RfxItemTreeCell;
import nth.reflect.javafx.control.list.mainmenu.RfxItemTreeView;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarTitle;

public class RfxStyleSheetUrlHandler extends UrlProvider {

	private String css;

	public RfxStyleSheetUrlHandler(ReflectApplicationForJavaFX application) {

		MaterialStyle materialStyle = GraphicalUserinterfaceController.getMaterialStyle();
		RfxStyleSheet styleSheet = new RfxStyleSheet();

		appendColorDefinitions(styleSheet, application);
		
		RfxApplicationToolbar.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarTitle.appendStyleGroups(styleSheet, materialStyle);
		RfxItemTreeView.appendStyleGroups(styleSheet);
		RfxItemTreeCell.appendStyleGroups(styleSheet);
		
//		RfxList.appendStyleGroups(styleSheet, materialStyle);
//		RfxMainMenuListCell.appendStyleGroups(styleSheet, materialStyle);

		System.out.println(styleSheet.toString());

		css = styleSheet.toString();
	}

	private void appendColorDefinitions(RfxStyleSheet styleSheet, ReflectApplicationForJavaFX application) {

		RfxStyleGroup colorDefintion = styleSheet.addStyleGroup(RfxStyleSelector.createFor("*"));
		colorDefintion.getProperties().setColorVariables(MaterialColorSet.PRIMARY_COLOR,new MaterialColorSet(application.getPrimaryColor()));
		colorDefintion.getProperties().setColorVariables(MaterialColorSet.SECONDARY_COLOR,new MaterialColorSet(application.getSecondaryColor()));
		colorDefintion.getProperties().setColorVariables(MaterialColorSet.ACCENT_COLOR,new MaterialColorSet(application.getAccentColor()));
		colorDefintion.getProperties().setColorVariables(MaterialColorSet.CONTENT_COLOR, new MaterialColorSet(application.getContentColor().getColor()));
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
