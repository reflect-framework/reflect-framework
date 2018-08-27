package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.net.URL;

public class ApplicationIconUrlModel implements ApplicationIconModel{

	private final URL url;

	public ApplicationIconUrlModel(URL url) {
		this.url = url;
	}


	@Override
	public URL getUrl() {
		return url;
	}

}
