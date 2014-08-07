package nth.introspect.ui.item.about;

import nth.introspect.Introspect;
import nth.introspect.provider.version.VersionProvider;

public class About {
	public VersionProvider about() {
		return Introspect.getVersionProvider();
	}
}
