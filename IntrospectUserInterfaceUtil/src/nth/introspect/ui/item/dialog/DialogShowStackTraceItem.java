package nth.introspect.ui.item.dialog;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.container.impl.UserInterfaceContainer;
import nth.introspect.controller.userinterface.DialogType;
import nth.introspect.controller.userinterface.UserInterfaceController;
import nth.introspect.provider.language.LanguageProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.util.ExceptionUtil;

//TODO move to UserinterfaceUtil project
public class DialogShowStackTraceItem extends Item {


	public DialogShowStackTraceItem( UserInterfaceContainer userInterfaceContainer, String title, String message, Throwable throwable) {
		super(userInterfaceContainer.getLanguageProvider());
		setText("Show stack trace");
		setDescription("Show more details on error");
		UserInterfaceController<?> userInterfaceController = userInterfaceContainer.getUserInterfaceController();
		LanguageProvider languageProvider=userInterfaceContainer.getLanguageProvider();
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
