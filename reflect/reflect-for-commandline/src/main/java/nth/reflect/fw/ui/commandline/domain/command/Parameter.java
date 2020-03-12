package nth.reflect.fw.ui.commandline.domain.command;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Date;

import nth.reflect.fw.layer2service.ServiceObjectActionMethod;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.stringconverter.generic.StringConverter;

public class Parameter {
	private final PropertyInfo propertyInfo;

	public Parameter(PropertyInfo propertyInfo) {
		this.propertyInfo = propertyInfo;
		if (!propertyInfo.getStringConverter().isPresent()) {
			throw new PropertyTypeNotSupported(propertyInfo);
		}
	}

	public String getName() {
		return propertyInfo.getSimpleName();
	}

	public String getDescription() {
		return propertyInfo.getDescription();
	}

	public String getUsage() {
		StringBuffer usage = new StringBuffer();
		Class<?> type = propertyInfo.getTypeInfo().getType();
		if (type.isAssignableFrom(String.class) || type.isAssignableFrom(File.class)
				|| type.isAssignableFrom(Date.class) || type.isAssignableFrom(URI.class)
				|| type.isAssignableFrom(URL.class)) {
			usage.append("\"<");
			usage.append(propertyInfo.getSimpleName());
			usage.append(">\"");
		} else {
			usage.append("<");
			usage.append(propertyInfo.getSimpleName());
			usage.append(">");
		}
		return usage.toString();
	}

	/**
	 * 
	 * @param parameter           value of a {@link ServiceObjectActionMethod}
	 *                            parameter which is about to be invoked
	 * @param commandLineargument command line argument that needs to be parsed to a
	 *                            parameter property
	 * @throws ReflectCommandLineException
	 */
	public void parseArgument(Object parameter, String commandLineargument) {

		try {
			if (propertyInfo.getStringConverter().isPresent()) {
				StringConverter<?> stringConverter = propertyInfo.getStringConverter().get();
				Object propertyValue = stringConverter.fromString(commandLineargument);
				propertyInfo.setValue(parameter, propertyValue);
			} else {
				throw new CommandLineArgumentException(commandLineargument, propertyInfo);
			}
		} catch (Throwable e) {
			throw new CommandLineArgumentException(commandLineargument, propertyInfo, e);
		}
	}

}
