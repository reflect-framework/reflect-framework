package nth.introspect.ui.item.about;

import java.util.List;

import sun.launcher.resources.launcher;
import nth.introspect.Introspect;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.domain.info.DomainInfoProvider;
import nth.introspect.layer5provider.domain.info.method.MethodInfo;
import nth.introspect.layer5provider.domain.info.method.filter.MethodNameFilter;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.ui.images.IntrospectImage;

public class AboutItem extends Item {
	private static final String ABOUT = "About";

	public AboutItem(final UserInterfaceController<?> userInterfaceController, final DomainInfoProvider domainInfoProvider, LanguageProvider languageProvider, final AboutProvider aboutProvider, PathProvider pathProvider) {
		super(languageProvider);
		setText(ABOUT);
		setDescription(ABOUT);
		setIconURI(pathProvider.getImagePath(IntrospectImage.BUTTON_ROUND_ABOUT));
		setAction(new Action() {
			@Override
			public void run() {
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT.toLowerCase());
				List<MethodInfo> methodInfos = domainInfoProvider.getMethodInfos(AboutProvider.class, methodFilter);
				if (methodInfos.size() == 1) {
					MethodInfo methodInfo = methodInfos.get(0);
					userInterfaceController.startExecution(aboutProvider, methodInfo, null);
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
