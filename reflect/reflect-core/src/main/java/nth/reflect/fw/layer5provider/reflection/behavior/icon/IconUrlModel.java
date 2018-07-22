package nth.reflect.fw.layer5provider.reflection.behavior.icon;

import java.net.URL;

public class IconUrlModel implements IconModel{

	private final URL url;

	public IconUrlModel(URL url) {
		this.url = url;
	}


	@Override
	public URL getURL(Object obj) {
		return url;
	}

}
