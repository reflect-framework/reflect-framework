package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import java.net.URL;

import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrl;

public class FontIconUrlModel implements FontIconModel {

	private final URL fontIconUrl;

	public FontIconUrlModel(FontIconUrl fontIconUrl) {
		this.fontIconUrl = fontIconUrl.toInternalURL();
	}
	
	@Override
	public URL getFontIconUrl(Object obj) {
		return fontIconUrl;
	}

}
