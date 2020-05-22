package nth.reflect.fw.vaadin.layer5provider.actionmethod.execution;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.Command;

public class ActionMethodExecutionProvider
		extends nth.reflect.fw.gui.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@SuppressWarnings("serial")
	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {

		UI ui = UI.getCurrent();
		ui.access(new Command() {
			@Override
			public void execute() {
				methodExecutionRunnable.run();
			}
		});
	}

}
