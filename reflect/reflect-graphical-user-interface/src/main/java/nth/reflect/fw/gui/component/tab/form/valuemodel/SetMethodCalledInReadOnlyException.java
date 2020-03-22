package nth.reflect.fw.gui.component.tab.form.valuemodel;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;

public class SetMethodCalledInReadOnlyException extends TranslatableException {

	private static final long serialVersionUID = 2803061755878220619L;

	private static final TranslatableString MESSAGE = new TranslatableString(
			SetMethodCalledInReadOnlyException.class.getCanonicalName() + ".message",
			"The %s.setValue() method may not be called in read only mode!");

	public SetMethodCalledInReadOnlyException() {
		super(MESSAGE.withParameters(PropertyValueModel.class.getSimpleName()));
	}

}
