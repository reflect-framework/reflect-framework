package nth.reflect.fw.gui.component.tab.form;

import java.util.function.Predicate;

import nth.reflect.fw.gui.component.tab.Tab;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class FormTabFilter implements Predicate<Tab> {

	private Object methodOwner;
	private ActionMethodInfo actionMethodInfo;
	private Object methodParameter;
	private Object domainObject;
	private FormMode formMode;

	public FormTabFilter(Object methodOwner, ActionMethodInfo actionMethodInfo, Object methodParameter,
			Object domainObject, FormMode formMode) {
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameter = methodParameter;
		this.domainObject = domainObject;
		this.formMode = formMode;
	}

	@Override
	public boolean test(Tab tab) {
		if (tab instanceof FormTab) {
			FormTab formTab = (FormTab) tab;
			return formTab.getMethodOwner() == methodOwner && formTab.getMethodInfo() == actionMethodInfo
					&& formTab.getMethodParameter() == methodParameter && formTab.getDomainObject() == domainObject
					&& formTab.getFormMode() == formMode;
		} else {
			return false;
		}
	}

}
