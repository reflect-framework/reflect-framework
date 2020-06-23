package nth.reflect.fw.ui.commandline.layer5provider.actionmethod.execution;

public class ActionMethodExecutionProvider
		extends nth.reflect.fw.layer5provider.actionmethod.execution.ActionMethodExecutionProvider {

	@Override
	public void executeOnUiThread(Runnable methodProcessing) {
		methodProcessing.run();
	}

}
