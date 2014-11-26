package nth.introspect.ui.item.about;

import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.about.AboutProvider;
import nth.introspect.provider.domain.info.DomainInfoProvider;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.filter.MethodNameFilter;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.ui.images.IntrospectImage;

public class AboutItem extends Item {
	private static final String ABOUT = "About";

	public AboutItem(final AboutProvider aboutProvider) {
		setText(ABOUT);
		setDescription(ABOUT);
		setIconURI(Introspect.getPathProvider().getImagePath(IntrospectImage.BUTTON_ROUND_ABOUT));
		final AboutItem aboutItem = this;
		setAction(new Action() {
			@Override
			public void run() {
				UserInterfaceProvider<?> userInterfaceProvider = Introspect.getUserInterfaceProvider();
				DomainInfoProvider domainInfoProvider = Introspect.getDomainInfoProvider();
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT.toLowerCase());
				List<MethodInfo> methodInfos = domainInfoProvider.getMethodInfos(AboutProvider.class, methodFilter);
				if (methodInfos.size() == 1) {
					MethodInfo methodInfo = methodInfos.get(0);
					userInterfaceProvider.startExecution(aboutProvider, methodInfo, null);
				} else {
					StringBuffer message = new StringBuffer("Could not find ");
					message.append(ABOUT.toLowerCase());
					message.append(" method in ");
					message.append(aboutProvider.getClass().getCanonicalName());
					throw new RuntimeException(message.toString());
				}
			}
		});
	}

	
}
