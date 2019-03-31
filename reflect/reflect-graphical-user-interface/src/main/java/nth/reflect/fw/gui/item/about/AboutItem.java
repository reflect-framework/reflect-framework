package nth.reflect.fw.gui.item.about;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer1userinterface.controller.UserInterfaceController;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.about.AboutProvider;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.filter.MethodNameFilter;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

public class AboutItem extends Item {
	private static final String ABOUT = "About";

	public AboutItem(final UserInterfaceController userInterfaceController, final ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider, final AboutProvider aboutProvider) {
		super(languageProvider);
		setText(ABOUT);
		setDescription(ABOUT);
		try {
			setIconURL(new URL(FontAwesomeUrl.INFO_CIRCLE));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		setAction(new Action() {
			@Override
			public void run() {
				MethodNameFilter methodFilter = new MethodNameFilter(ABOUT.toLowerCase());
				DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(aboutProvider.getClass());
				List<ActionMethodInfo> actionMethodInfos = domainClassInfo.getActionMethodInfos(methodFilter);
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
