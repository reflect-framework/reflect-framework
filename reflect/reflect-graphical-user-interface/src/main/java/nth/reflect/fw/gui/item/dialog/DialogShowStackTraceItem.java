package nth.reflect.fw.gui.item.dialog;

import java.util.ArrayList;
import java.util.List;

import nth.reflect.fw.generic.util.ExceptionUtil;
import nth.reflect.fw.gui.GraphicalUserinterfaceController;
import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer1userinterface.controller.DialogType;
import nth.reflect.fw.layer1userinterface.item.Item;
import nth.reflect.fw.layer5provider.language.LanguageProvider;

//TODO move to UserinterfaceUtil project
public class DialogShowStackTraceItem extends Item {


	public DialogShowStackTraceItem( UserInterfaceContainer userInterfaceContainer, String title, String message, Throwable throwable) {
		super(userInterfaceContainer.get(LanguageProvider.class));
		setText("Show stack trace");
		setDescription("Show more details on error");
		GraphicalUserinterfaceController<?, ?> userInterfaceController = userInterfaceContainer.get(GraphicalUserinterfaceController.class);
		LanguageProvider languageProvider=userInterfaceContainer.get(LanguageProvider.class);
		Action action=createAction(userInterfaceController,languageProvider, title,message,throwable);
		setAction(action);
	}

	private Action createAction(final GraphicalUserinterfaceController<?, ?> userInterfaceController, final LanguageProvider languageProvider, final String title, final String message, final Throwable throwable) {
		return new Action() {

			@Override
			public void run() {
				StringBuffer messageWithStackTrace = new StringBuffer(message);
				// add stack trace
				if (throwable != null) {
					messageWithStackTrace.append("\n\n");
					messageWithStackTrace.append(ExceptionUtil
							.getRootCauseStackTrace(throwable, languageProvider));
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
