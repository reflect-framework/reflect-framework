package nth.introsepect.ui.swing;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.provider.userinterface.DialogType;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.provider.userinterface.item.Item;
import nth.introspect.util.ExceptionUtil;

//TODO move to UserinterfaceUtil project
public class ShowStackTraceItem extends Item {

	private String title;
	private String message;
	private Throwable throwable;

	public ShowStackTraceItem(String title, String message, Throwable throwable) {
		this.title = title;
		this.message = message;
		this.throwable = throwable;
		setText("Show stack trace");
		setDescription("Show more details on error");
		setAction(createAction());
	}

	private Action createAction() {
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

				setAction(createAction());
				UserInterfaceProvider<?> userInterfaceProvider = Introspect
						.getUserInterfaceProvider();
				userInterfaceProvider.showDialog(DialogType.ERROR, title,
						messageWithStackTrace.toString(), items);
			}
		};
	}
}
