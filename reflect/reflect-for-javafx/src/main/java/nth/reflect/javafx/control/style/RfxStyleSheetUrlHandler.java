package nth.reflect.javafx.control.style;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

import nth.introspect.layer5provider.url.UrlProvider;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.list.RfxMainMenuListCell;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarMenuButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarTitle;

public class RfxStyleSheetUrlHandler extends UrlProvider {

	private String css;

	public RfxStyleSheetUrlHandler() {

		MaterialStyle materialStyle = GraphicalUserinterfaceController.getMaterialStyle();
		RfxStyleSheet styleSheet = new RfxStyleSheet(materialStyle);

		RfxApplicationToolbar.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarTitle.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarButton.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarMenuButton.appendStyleGroups(styleSheet, materialStyle);
		
		RfxList.appendStyleGroups(styleSheet, materialStyle);
		RfxMainMenuListCell.appendStyleGroups(styleSheet, materialStyle);
		
		System.out.println(styleSheet.toString());

		css = styleSheet.toString();
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
