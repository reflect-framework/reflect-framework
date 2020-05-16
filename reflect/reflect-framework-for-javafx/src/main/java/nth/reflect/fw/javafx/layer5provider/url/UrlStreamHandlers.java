package nth.reflect.fw.javafx.layer5provider.url;

import nth.reflect.fw.javafx.control.style.StyleSheetUrlHandler;

public class UrlStreamHandlers extends nth.reflect.fw.layer5provider.url.UrlStreamHandlers {

	private static final long serialVersionUID = -4512066886718237588L;

	public UrlStreamHandlers() {
		super();
		add(StyleSheetUrlHandler.class);
	}
}
