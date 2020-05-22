package nth.reflect.fw.javafx.layer5provider.actionmethod.execution;

public class ActionMethodExecutionProvider
		extends nth.reflect.fw.gui.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	/**
	 * JavaFX does not allow executing threads on the event thread, so we run it
	 * later.
	 */

	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		javafx.application.Platform.runLater(methodExecutionRunnable);
	}

}
