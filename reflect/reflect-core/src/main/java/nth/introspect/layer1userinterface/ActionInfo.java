package nth.introspect.layer1userinterface;

import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class ActionInfo {

	private final Object actionMethodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private Object actionMethodParameterValue;

	public ActionInfo(Object actionMethodOwner, ActionMethodInfo actionMethodInfo,
			Object actionMethodParameterValue) {
		this.actionMethodOwner = actionMethodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.actionMethodParameterValue = actionMethodParameterValue;
	}

	public Object getActionMethodOwner() {
		return actionMethodOwner;
	}

	public ActionMethodInfo getActionMethodInfo() {
		return actionMethodInfo;
	}

	public Object getActionMethodParameterValue() {
		return actionMethodParameterValue;
	}

	public void setParameterValue(Object actionMethodParameterValue) {
		this.actionMethodParameterValue = actionMethodParameterValue;
	}

}
