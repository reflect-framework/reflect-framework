package nth.introspect.ui.commandline.domain.command;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import nth.introspect.provider.domain.info.property.PropertyInfo;

public class Parameter {
	private final PropertyInfo propertyInfo;
	private Class<?> type;
	private Map<Class<?>, String> types;


	public Parameter(PropertyInfo propertyInfo) throws IntrospectCommandLineException {
		this.propertyInfo = propertyInfo;
		type = propertyInfo.getPropertyType().getType();

		types = CommandService.getSupportedParameterPropertyTypes();
		if (!types.containsKey(type)) {
			throw new IntrospectCommandLineException("Property type " + type.getCanonicalName() + " is not supported for property " + propertyInfo.getName());
		}

	}

	public String getName() {
		return propertyInfo.getName();
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
		if (type.isAssignableFrom(String.class) || type.isAssignableFrom(File.class) || type.isAssignableFrom(Date.class)){
			usage.append("\"<");
			usage.append(propertyInfo.getName());
			usage.append(">\"");
		} else  {
			usage.append("<");
			usage.append(propertyInfo.getName());
			usage.append(">");
		}
		return usage.toString();
	}

	/**
	 * 
	 * @param parameter
	 *            value of a service object method which is about to be invoked
	 * @param argument
	 *            command line argument that needs to be parsed to a parameter property
	 * @throws IntrospectCommandLineException
	 */
	public void parseArgument(Object parameter, String argument) throws IntrospectCommandLineException {
		Object value = null;
		try {
			if (type.isAssignableFrom(Boolean.class)) {
				value = Boolean.valueOf(argument);
			} else if (type.isAssignableFrom(Byte.class)) {
				value = Byte.valueOf(argument);
			} else if (type.isAssignableFrom(Short.class)) {
				value = Short.valueOf(argument);
			} else if (type.isAssignableFrom(Integer.class)) {
				value = Integer.valueOf(argument);
			} else if (type.isAssignableFrom(Long.class)) {
				value = Long.valueOf(argument);
			} else if (type.isAssignableFrom(Float.class)) {
				value = Float.valueOf(argument);
			} else if (type.isAssignableFrom(Double.class)) {
				value = Double.valueOf(argument);
			} else if (type.isAssignableFrom(Character.class)) {
				value = argument.charAt(0);
			} else if (type.isAssignableFrom(String.class)) {
				value = argument;
			} else if (type.isAssignableFrom(File.class)) {
				value = new File(argument);
			} else if (type.isAssignableFrom(Date.class)) {
				SimpleDateFormat formatter = new SimpleDateFormat();
				value = formatter.parse(argument);
			}
			
			propertyInfo.setValue(parameter, value);
		} catch (Throwable e) {
			throw new IntrospectCommandLineException("Could not parse '" + argument + "' to a '" + type.getName() + "' for property " + propertyInfo.getName());
		}
	}

}