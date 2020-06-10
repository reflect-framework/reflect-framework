package nth.reflect.fw.layer5provider.actionmethod.execution;

import nth.reflect.fw.layer5provider.Provider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

public interface ActionMethodExecutionProvider extends Provider {

	/**
	 * This method is called from the {@link ActionMethodInvoker#run()} method to
	 * ensure that a {@link ActionMethod} is being processed in they way preferred
	 * by user interface. Most user interface frameworks have a specific way to
	 * execute threads so that they work correctly with the life cycle of the user
	 * interface framework.
	 * 
	 * @param methodExecutionRunnable
	 */
	public abstract void executeOnUiThread(Runnable methodProcessing);
}
