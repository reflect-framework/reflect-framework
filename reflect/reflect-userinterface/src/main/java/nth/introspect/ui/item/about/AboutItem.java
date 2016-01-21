package nth.introspect.ui.item.about;

import java.net.URISyntaxException;
import java.util.List;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.about.AboutProvider;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.icon.IconUriClassResource;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.ui.images.IntrospectImage;

public class AboutItem extends Item {
	private static final String ABOUT = "About";

	public AboutItem(final UserInterfaceController userInterfaceController, final ReflectionProvider reflectionProvider, LanguageProvider languageProvider, final AboutProvider aboutProvider) {
		super(languageProvider);
		setText(ABOUT);
		setDescription(ABOUT);
		try {
			setIconURI(new IconUriClassResource(IntrospectImage.BUTTON_ROUND_ABOUT).getAbsoluteURI());
		} catch (URISyntaxException e) {
		}
		setAction(new Action() {
			@Override
			public void run() {
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT.toLowerCase());
				ClassInfo classInfo=reflectionProvider.getClassInfo(AboutItem.class);
				List<ActionMethodInfo> actionMethodInfos = classInfo.getActionMethodInfos( methodFilter);
				if (actionMethodInfos.size() == 1) {
					ActionMethodInfo actionMethodInfo = actionMethodInfos.get(0);
					userInterfaceController.processActionMethod(aboutProvider, actionMethodInfo, null);
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
