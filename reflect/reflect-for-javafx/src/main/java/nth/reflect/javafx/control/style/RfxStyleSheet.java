package nth.reflect.javafx.control.style;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import nth.introspect.ui.style.MaterialStyle;
import nth.reflect.javafx.control.list.RfxList;

public class RfxStyleSheet {

	public static final String FILLED = ":filled";
	public static final String SELECTED = ":selected";
	public static final String FOCUSED = ":focused";
	private final List<RfxStyleGroup> styleGroups;
	private final MaterialStyle materialStyle;

	public RfxStyleSheet(MaterialStyle materialStyle) {
		this.materialStyle = materialStyle;
		styleGroups = new ArrayList<>();
	}

	public RfxStyleGroup addStyleGroup(Class<? extends Control> controlClass, String... selectors) {
		RfxStyleGroup styleGroup = new RfxStyleGroup(controlClass, selectors);
		styleGroups.add(styleGroup);
		return styleGroup;														
	}

	public void addToScene(Scene scene) {
		RfxList.appendStyleGroups(this, materialStyle);

		System.out.println(toString());

		URL.setURLStreamHandlerFactory(new StringURLStreamHandlerFactory(toString()));

		scene.getStylesheets().setAll("internal:stylesheet.css");
	}

	public static String getStyleClass(Class<? extends Control> controlClass) {
		StringBuilder styleClassName = new StringBuilder();
		String controlClassName = controlClass.getSimpleName();
		char previousCh = 0;
		for (char ch : controlClassName.toCharArray()) {
			if (Character.isLowerCase(previousCh) && Character.isUpperCase(ch)) {
				styleClassName.append("-");
			}
			styleClassName.append(Character.toLowerCase(ch));
			previousCh = ch;
		}
		return styleClassName.toString();
	}

	@Override
	public String toString() {
		StringBuilder css = new StringBuilder();
		for (RfxStyleGroup styleGroup : styleGroups) {
			css.append(styleGroup);
			css.append("\n");
		}
		return css.toString();
	}

	private class StringURLConnection extends URLConnection {
		private final String css;

		public StringURLConnection(URL url, String css) {
			super(url);
			this.css = css;
		}

		@Override
		public void connect() throws IOException {
		}

		@Override
		public InputStream getInputStream() throws IOException {
			return new StringBufferInputStream(css);
		}
	}

	private class StringURLStreamHandlerFactory implements URLStreamHandlerFactory {
		private final String css;

		public StringURLStreamHandlerFactory(String css) {
			this.css = css;
		}

		URLStreamHandler streamHandler = new URLStreamHandler() {
			@Override
			protected URLConnection openConnection(URL url) throws IOException {
				if (url.toString().toLowerCase().endsWith(".css")) {
					return new StringURLConnection(url, css);
				}
				throw new FileNotFoundException();
			}
		};

		@Override
		public URLStreamHandler createURLStreamHandler(String protocol) {
			if ("internal".equals(protocol)) {
				return streamHandler;
			}
			return null;
		}
	}
}
