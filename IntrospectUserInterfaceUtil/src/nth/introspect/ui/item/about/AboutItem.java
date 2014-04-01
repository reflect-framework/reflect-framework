package nth.introspect.ui.item.about;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.DomainProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.filter.MethodNameFilter;
import nth.introspect.provider.info.InfoProvider;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.images.IntrospectImage;

public class AboutItem extends Item {
	private static final String ABOUT = "About";

	public AboutItem() {
		setText(ABOUT);
		setDescription(ABOUT);
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.BUTTON_ROUND_ABOUT));
		final AboutItem aboutItem = this;
		setAction(new Action() {
			@Override
			public void run() {
				UserInterfaceProvider<?> userInterfacePort = Introspect.getUserInterfaceProvider();
				DomainProvider domainProvider = Introspect.getDomainProvider();
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT.toLowerCase());
				List<MethodInfo> methodInfos = domainProvider.getMethodInfos(About.class, methodFilter);
				if (methodInfos.size() == 1) {
					MethodInfo methodInfo = methodInfos.get(0);
					userInterfacePort.startExecution(new About(), methodInfo, null);
				} else {
					StringBuffer message = new StringBuffer("Could not find ");
					message.append(ABOUT.toLowerCase());
					message.append(" method in ");
					message.append(this.getClass().getCanonicalName());
					throw new RuntimeException(message.toString());
				}
			}
		});
	}

	
}
