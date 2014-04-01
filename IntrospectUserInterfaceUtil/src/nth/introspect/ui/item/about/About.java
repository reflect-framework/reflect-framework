package nth.introspect.ui.item.about;

import nth.introspect.Introspect;
import nth.introspect.provider.info.InfoProvider;

public class About {
	public InfoProvider about() {
		return Introspect.getInfoProvider();
	}
}
