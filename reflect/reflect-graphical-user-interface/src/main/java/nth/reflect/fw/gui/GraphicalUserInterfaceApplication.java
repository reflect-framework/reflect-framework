package nth.reflect.fw.gui;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.gui.component.tab.form.propertypanel.field.factory.PropertyFieldProvider;
import nth.reflect.fw.gui.proider.actionmethodexecution.result.DomainObjectResultHandler;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer5provider.actionmethodexecution.ActionMethodExecutionProvider;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.NoResultHandler;
import nth.reflect.fw.layer5provider.actionmethodexecution.result.depricated.DeprecatedActionMethodResultHandler;

public interface GraphicalUserInterfaceApplication extends ReflectApplication {

	public ColorProvider getColorProvider();

	public PropertyFieldProvider getPropertyFieldProvider();

	@Override
	default ActionMethodExecutionProvider getActionMethodExecutionProvider() {
		return new ActionMethodExecutionProvider(new NoResultHandler(), new DomainObjectResultHandler(),
				new DeprecatedActionMethodResultHandler(this));
	}
}
