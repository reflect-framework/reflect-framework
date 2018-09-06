package nth.reflect.ui.vaadin.view.form;

import java.net.URL;

import nth.reflect.fw.layer1userinterface.UserInterfaceContainer;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.view.FormMode;
import nth.reflect.ui.vaadin.view.TabView;

public class FormView extends TabView {

	private final UserInterfaceContainer userInterfaceContainer;
	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Object domainObject;
	private final FormMode formMode;

	public FormView(UserInterfaceContainer userInterfaceContainer, Object actionMethodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue, Object domainObject, FormMode formMode) {
				this.userInterfaceContainer = userInterfaceContainer;
				this.actionMethodOwner = actionMethodOwner;
				this.actionMethodInfo = actionMethodInfo;
				this.methodParameterValue = methodParameterValue;
				this.domainObject = domainObject;
				this.formMode = formMode;
	}


	@Override
	public String getViewTitle() {
		return actionMethodInfo.getDisplayName();
	}

	@Override
	public String getViewDescription() {
		return actionMethodInfo.getDescription();
	}

	@Override
	public URL getViewIconURL() {
		return actionMethodInfo.getFontIconUrl(actionMethodOwner);
	}

	@Override
	public void onViewActivate() {
		// TODO Auto-generated method stub

	}

}
