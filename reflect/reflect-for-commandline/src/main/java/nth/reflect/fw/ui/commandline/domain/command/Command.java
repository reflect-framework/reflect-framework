package nth.reflect.fw.ui.commandline.domain.command;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.NoParameterFactoryException;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;
import nth.reflect.fw.layer5provider.reflection.info.type.TypeInfo;

public class Command {
	private String name;
	private Object serviceObject;
	private ActionMethodInfo actionMethodInfo;
	private List<Parameter> parameters;

	public Command(ReflectionProvider reflectionProvider, Object serviceObject, ActionMethodInfo actionMethodInfo,
			boolean shortCommand) throws ReflectCommandLineException {
		this.serviceObject = serviceObject;
		this.actionMethodInfo = actionMethodInfo;
		// name
		this.name = createName(serviceObject, actionMethodInfo, shortCommand);
		// parameters
		parameters = createParameters(reflectionProvider, actionMethodInfo);

	}

	private List<Parameter> createParameters(ReflectionProvider reflectionProvider, ActionMethodInfo actionMethodInfo)
			throws ReflectCommandLineException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		TypeInfo typeInfo = actionMethodInfo.getFirstParameterTypeInfo();

		if (!typeInfo.isVoid() && typeInfo.isDomainClass()) {

			Class<?> returnClass = typeInfo.getType();
			DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(returnClass);
			List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSorted();
			List<PropertyInfo> editableSimplePropertyInfos = propertyInfos.stream()
					.filter(propertyInfo -> propertyInfo.isVisibleInTable() && !propertyInfo.isReadOnly())
					.collect(Collectors.toList());

			for (PropertyInfo propertyInfo : editableSimplePropertyInfos) {
				Parameter parameter = new Parameter(propertyInfo);
				parameters.add(parameter);
			}
		}
		return parameters;
	}

	private String createName(Object serviceObject, ActionMethodInfo actionMethodInfo, boolean shortCommand) {
		StringBuffer name = new StringBuffer();
		if (!shortCommand) {
			name.append(serviceObject.getClass().getName());
			name.append(".");
		}
		name.append(actionMethodInfo.getSimpleName());
		return name.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getServiceObject() {
		return serviceObject;
	}

	public void setServiceObject(Object serviceObject) {
		this.serviceObject = serviceObject;
	}

	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	public void setMethodInfo(ActionMethodInfo actionMethodInfo) {
		this.actionMethodInfo = actionMethodInfo;
	}

	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	public String getUsage() {
		StringBuffer usage = new StringBuffer();

		String jarName = getJarName();
		usage.append(jarName);
		usage.append(" ");
		usage.append(name);

		if (parameters.size() > 0) {
			for (Parameter parameter : parameters) {
				usage.append(" ");
				usage.append(parameter.getUsage());
			}
		}

		return usage.toString();
	}

	private String getJarName() {
		try {
			File jarFile = new File(Command.class.getProtectionDomain().getCodeSource().getLocation().toURI());
			if ("bin".equals(jarFile.getName())) {// fix debug issue (when not
													// executed from a jar.i.e.
													// during debugging)
				return "<Jar name>";
			}
			return jarFile.getName();
		} catch (URISyntaxException e) {
			return null;
		}

	}

	public Object createMethodParameter() throws ReflectCommandLineException {
		try {
			try {
				return actionMethodInfo.createMethodParameter(serviceObject);
			} catch (NoParameterFactoryException e) {
				Class<?> parameterType = actionMethodInfo.getFirstParameterTypeInfo().getType();
				return parameterType.newInstance();
			}
		} catch (Exception e) {
			throw new ReflectCommandLineException("Could not create a new instance of method parameter: "
					+ actionMethodInfo.getFirstParameterTypeInfo().getType().getCanonicalName() + " for method: "
					+ actionMethodInfo.getCanonicalName(), e);
		}
	}

}
