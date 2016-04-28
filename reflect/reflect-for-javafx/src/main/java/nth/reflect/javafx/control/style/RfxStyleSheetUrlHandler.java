package nth.reflect.javafx.control.style;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.net.URLConnection;

import nth.introspect.layer5provider.path.url.ReflectUrlConnection;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.control.list.RfxList;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbar;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarMenuButton;
import nth.reflect.javafx.control.toolbar.RfxApplicationToolbarTitle;

public class RfxStyleSheetUrlHandler extends ReflectUrlConnection {

	private String css;

	public RfxStyleSheetUrlHandler() {

		MaterialStyle materialStyle = GraphicalUserinterfaceController.getMaterialStyle();
		RfxStyleSheet styleSheet = new RfxStyleSheet(materialStyle);

		RfxApplicationToolbar.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarTitle.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarButton.appendStyleGroups(styleSheet, materialStyle);
		RfxApplicationToolbarMenuButton.appendStyleGroups(styleSheet, materialStyle);
		RfxList.appendStyleGroups(styleSheet, materialStyle);

		System.out.println(styleSheet.toString());

		css = styleSheet.toString();
	}

	@Override
	public String getProtocol() {
		return RfxStyleSheetUrl.PROTOCOL;
	}

	// private class CssUrlConnection extends URLConnection {
	// private final String css;
	//
	// public CssUrlConnection(URL url, String css) {
	// super(url);
	// this.css = css;
	// }
	//
	// @Override
	// public void connect() throws IOException {
	// }
	//
	// @Override
	// public InputStream getInputStream() throws IOException {
	// }
	// }
	//
	// private class StringURLStreamHandlerFactory implements
	// URLStreamHandlerFactory {
	// private final String css;
	//
	// public StringURLStreamHandlerFactory(String css) {
	// this.css = css;
	// }
	//
	// URLStreamHandler streamHandler = new URLStreamHandler() {
	// @Override
	// protected URLConnection openConnection(URL url) throws IOException {
	// if (url.toString().toLowerCase().endsWith(".css")) {
	// return new StringURLConnection(url, css);
	// }
	// throw new FileNotFoundException();
	// }
	// };
	//
	// }

	@Override
	protected URLConnection openConnection(URL url) throws IOException {
		return new URLConnection(url) {

			@Override
			public void connect() throws IOException {
			}

			@Override
			public InputStream getInputStream() throws IOException {
				return new StringBufferInputStream(css);
			}

		};
	}

}
