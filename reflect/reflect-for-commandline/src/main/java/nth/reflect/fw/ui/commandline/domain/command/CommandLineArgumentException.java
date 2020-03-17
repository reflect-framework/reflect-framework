package nth.reflect.fw.ui.commandline.domain.command;

import nth.reflect.fw.generic.exception.TranslatableException;
import nth.reflect.fw.layer5provider.language.translatable.TranslatableString;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class CommandLineArgumentException extends TranslatableException {

	private static final long serialVersionUID = -1999227066227643604L;

	public final static TranslatableString MESSAGE = new TranslatableString(
			CommandLineArgumentException.class.getCanonicalName() + ".message",
			"Could not parse '%s' to a '%s' for property :%s");

	public CommandLineArgumentException(String commandLineargument, PropertyInfo propertyInfo, Throwable cause) {
		super(MESSAGE.withParameters(commandLineargument, propertyInfo.getTypeInfo(), propertyInfo.getSimpleName()),
				cause);
	}

	public CommandLineArgumentException(String commandLineargument, PropertyInfo propertyInfo) {
		super(MESSAGE.withParameters(commandLineargument, propertyInfo.getTypeInfo(), propertyInfo.getSimpleName()));
	}

}
