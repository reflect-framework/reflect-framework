package nth.introspect.ui.item.dialog;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.DialogType;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.util.ExceptionUtil;

//TODO move to UserinterfaceUtil project
public class DialogShowStackTraceItem extends Item {


	public DialogShowStackTraceItem(UserInterfaceProvider<?> userInterfaceProvider, String title, String message, Throwable throwable) {
		setText("Show stack trace");
		setDescription("Show more details on error");
		Action action=createAction(userInterfaceProvider,title,message,throwable);
		setAction(action);
	}

	private Action createAction(final UserInterfaceProvider<?> userInterfaceProvider, final String title, final String message, final Throwable throwable) {
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

				DialogCloseItem dialogCloseItem = new DialogCloseItem();
				List<Item> items = new ArrayList<Item>();
				items.add(dialogCloseItem);

				userInterfaceProvider.showDialog(DialogType.ERROR, title,
						messageWithStackTrace.toString(), items);
			}
		};
	}
}
