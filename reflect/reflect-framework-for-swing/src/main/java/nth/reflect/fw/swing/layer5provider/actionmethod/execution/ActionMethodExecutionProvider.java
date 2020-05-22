package nth.reflect.fw.swing.layer5provider.actionmethod.execution;

public class ActionMethodExecutionProvider
		extends nth.reflect.fw.gui.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@Override
	public void executeInThread(Runnable methodExecutionRunnable) {
		Thread methodExecutionThread = new Thread(methodExecutionRunnable);
		methodExecutionThread.start();
	}

}
