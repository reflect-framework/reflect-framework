package nth.introspect.ui.item.dialog;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.generic.util.ExceptionUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.controller.DialogType;
import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer5provider.language.LanguageProvider;

//TODO move to UserinterfaceUtil project
public class DialogShowStackTraceItem extends Item {


	public DialogShowStackTraceItem( UserInterfaceContainer userInterfaceContainer, String title, String message, Throwable throwable) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		setText("Show stack trace");
		setDescription("Show more details on error");
		UserInterfaceController<?> userInterfaceController = userInterfaceContainer.get(UserInterfaceController.class);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		Action action=createAction(userInterfaceController,languageProvider, title,message,throwable);
		setAction(action);
	}

	private Action createAction(final UserInterfaceController<?> userInterfaceController, final LanguageProvider languageProvider, final String title, final String message, final Throwable throwable) {
		return new Action() {

			@Override
			public void run() {
				StringBuffer messageWithStackTrace = new StringBuffer(message);
				// add stack trace
				if (throwable != null) {
					messageWithStackTrace.append("\n\n");
					messageWithStackTrace.append(ExceptionUtil
							.getRootCauseStackTrace(throwable));
				}

				DialogCloseItem dialogCloseItem = new DialogCloseItem(languageProvider);
				List<Item> items = new ArrayList<Item>();
				items.add(dialogCloseItem);

				userInterfaceController.showDialog(DialogType.ERROR, title,
						messageWithStackTrace.toString(), items);
			}
		};
	}
}
