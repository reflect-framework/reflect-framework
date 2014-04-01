package nth.introspect.ui.item.method;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.introspect.Introspect;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.method.MethodInfo.ExecutionModeType;
import nth.introspect.provider.userinterface.UserInterfaceProvider;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;

public class FormOkItem extends MethodItem {

	public FormOkItem(final FormView formView, final Object methodOwner, final MethodInfo methodInfo, final BufferedDomainValueModel domainValueModel) {
		super (methodOwner, methodInfo, domainValueModel);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
		setAction(new Action() {
			@Override
			public void run() {
				if (FormMode.EDIT_MODE== formView.getFormMode()) {
					domainValueModel.commit();
				}
				UserInterfaceProvider<?> userinterfacePort = Introspect.getUserInterfaceProvider();
				userinterfacePort.excuteMethod(methodOwner, methodInfo, domainValueModel.getValue());
				userinterfacePort.getViewContainer().removeView(formView);
			}

		});
	}

	
}