package nth.reflect.fw.ui.commandline.domain.command;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.text.Format;
import java.util.Date;
import java.util.Map;

import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

public class Parameter {
	private final PropertyInfo propertyInfo;
	private final Class<?> type;
	private final Map<Class<?>, String> types;

	public Parameter(PropertyInfo propertyInfo) throws ReflectCommandLineException {
		this.propertyInfo = propertyInfo;
		type = propertyInfo.getTypeInfo().getType();

		types = CommandService.getSupportedParameterPropertyTypes();
		if (!types.containsKey(type)) {
			throw new ReflectCommandLineException("Property type " + type.getCanonicalName()
					+ " is not supported for property " + propertyInfo.getSimpleName());
		}

	}

	public String getName() {
		return propertyInfo.getSimpleName();
	}

	public String getDescription() {
		StringBuffer description = new StringBuffer();
		description.append("(");
		description.append(types.get(type));
		description.append(") ");
		description.append(propertyInfo.getDescription());
		return description.toString();
	}

	public String getUsage() {
		StringBuffer usage = new StringBuffer();
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
	 * @param parameter
	 *            value of a service object method which is about to be invoked
	 * @param argument
	 *            command line argument that needs to be parsed to a parameter
	 *            property
	 * @throws ReflectCommandLineException
	 */
	public void parseArgument(Object parameter, String argument) throws ReflectCommandLineException {
		try {
			if (propertyInfo.getTypeInfo().getType() == File.class) {
				File file = new File(argument);
				propertyInfo.setValue(parameter, file);
			} else {
				Format format = propertyInfo.getFormat();
				Object value = format.parseObject(argument);
				propertyInfo.setValue(parameter, value);
			}
		} catch (Throwable e) {
			throw new ReflectCommandLineException("Could not parse '" + argument + "' to a '" + type.getName()
					+ "' for property " + propertyInfo.getSimpleName());
		}
	}

}
