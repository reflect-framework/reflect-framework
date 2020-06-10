package nth.reflect.fw.vaadin.layer5provider.actionmethod.execution;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

public class ActionMethodExecutionProvider
		implements nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@SuppressWarnings("serial")
	@Override
	public void executeOnUiThread(Runnable methodProcessing) {

		UI ui = UI.getCurrent();
		ui.access(new Command() {
			@Override
			public void execute() {
				methodProcessing.run();
			}
		});
	}

}
