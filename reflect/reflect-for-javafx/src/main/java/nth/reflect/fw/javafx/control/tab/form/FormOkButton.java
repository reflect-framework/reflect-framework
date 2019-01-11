package nth.reflect.fw.javafx.control.tab.form;

import nth.reflect.fw.javafx.control.button.ContentButton;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.ui.component.tab.form.valuemodel.BufferedDomainValueModel;
import nth.reflect.fw.ui.item.method.FormOkItem;

public class FormOkButton extends ContentButton {

	public FormOkButton(FormTab formTab) {
		super(createFormOkItem(formTab));
	}

	private static FormOkItem createFormOkItem(FormTab formTab) {
		Object methodOwner = formTab.getMethodOwner();
		ActionMethodInfo actionMethodInfo = formTab.getMethodInfo();
		BufferedDomainValueModel domainValueModel = formTab.getDomainValueModel();
		FormOkItem okItem = new FormOkItem(formTab, methodOwner, actionMethodInfo, domainValueModel);
		return okItem;
	}
}
