package nth.reflect.fw.javafx.layer5provider.actionmethod.execution;

public class ActionMethodExecutionProvider
		implements nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	/**
	 * JavaFX does not allow executing threads on the event thread, so we run it
	 * later.
	 */

	@Override
	public void executeOnUiThread(Runnable methodExecutionRunnable) {
		javafx.application.Platform.runLater(methodExecutionRunnable);
	}

}
