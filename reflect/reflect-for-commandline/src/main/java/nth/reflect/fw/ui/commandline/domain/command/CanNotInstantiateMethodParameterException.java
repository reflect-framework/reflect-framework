package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;

public class CanNotInstantiateMethodParameterException extends TranslatableException {

	private static final long serialVersionUID = 3948875054637127283L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			CanNotInstantiateMethodParameterException.class.getCanonicalName(),
			"Could not create a new instance of method parameter: %s for method: %s.");

	public CanNotInstantiateMethodParameterException(ActionMethodInfo actionMethodInfo, Exception e) {
		super(MESSAGE.withParameters(actionMethodInfo.getFirstParameterTypeInfo().getType().getCanonicalName(),
				actionMethodInfo.getCanonicalName()), e);
	}

}
