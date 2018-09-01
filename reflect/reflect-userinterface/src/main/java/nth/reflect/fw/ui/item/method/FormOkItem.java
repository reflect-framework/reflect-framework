package nth.reflect.fw.ui.item.method;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.GraphicalUserinterfaceController;
import nth.reflect.fw.ui.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.fw.ui.view.FormView;

public class FormOkItem extends MethodItem {

	public FormOkItem( final FormView formView, final Object methodOwner, final ActionMethodInfo actionMethodInfo, final BufferedDomainValueModel domainValueModel) {
		super (formView.getUserInterfaceContainer(), methodOwner, actionMethodInfo, domainValueModel);
		final GraphicalUserinterfaceController userInterfaceController = formView.getUserInterfaceContainer().get(GraphicalUserinterfaceController.class);
		setAction(new Action() {
			@Override
			public void run() {
				if (FormMode.EDIT_MODE== formView.getFormMode()) {
					domainValueModel.commit();
				}
				userInterfaceController.processActionMethodExecution(methodOwner, actionMethodInfo, domainValueModel.getValue());
				userInterfaceController.getViewController().removeView(formView);
			}

		});
	}

	
}
