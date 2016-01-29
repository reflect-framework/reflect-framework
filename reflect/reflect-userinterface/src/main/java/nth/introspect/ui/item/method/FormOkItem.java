package nth.introspect.ui.item.method;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;

public class FormOkItem extends MethodItem {

	public FormOkItem( final FormView formView, final Object methodOwner, final ActionMethodInfo actionMethodInfo, final BufferedDomainValueModel domainValueModel) {
		super (formView.getuserInterfaceContainer(), methodOwner, actionMethodInfo, domainValueModel);
		final GraphicalUserinterfaceController userInterfaceController = formView.getuserInterfaceContainer().get(GraphicalUserinterfaceController.class);
		setHotKey(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_MASK));
		setAction(new Action() {
			@Override
			public void run() {
				if (FormMode.EDIT_MODE== formView.getFormMode()) {
					domainValueModel.commit();
				}
				userInterfaceController.processActionMethodExecution(methodOwner, actionMethodInfo, domainValueModel.getValue());
				userInterfaceController.getViewContainer().removeView(formView);
			}

		});
	}

	
}
