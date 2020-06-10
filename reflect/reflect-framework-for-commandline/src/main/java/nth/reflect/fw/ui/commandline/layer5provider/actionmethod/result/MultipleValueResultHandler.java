package nth.reflect.fw.ui.commandline.layer5provider.actionmethod.result;

import java.util.Collection;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.actionmethod.result.ActionMethodResultHandler;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.commandline.view.TableView;

/**
 * Shows the {@link Collection} as a table
 * 
 * @author nilsth
 *
 */
public class MultipleValueResultHandler implements ActionMethodResultHandler {

	@Override
	public boolean canProcess(ProviderContainer container, ActionMethodInfo methodInfo) {
		boolean isDomainClass = methodInfo.getReturnTypeInfo().isCollection();
		return isDomainClass;
	}

	@Override
	public void process(UserInterfaceContainer container, ActionMethodInfo methodInfo, Object methodOwner,
			Object methodParameter, Object methodResult) {
		ReflectionProvider reflectionProvider = container.get(ReflectionProvider.class);
		Collection<?> collection = (Collection<?>) methodResult;
		TableView tableView = new TableView(reflectionProvider, methodInfo, collection);
		System.out.println(tableView.toString());
	}
}
