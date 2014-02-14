package nth.introspect.ui.commandline.domain.command;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import nth.introspect.Introspect;
import nth.introspect.filter.Filter;
import nth.introspect.provider.domain.info.method.MethodInfo;
import nth.introspect.provider.domain.info.property.FormOrderComparator;
import nth.introspect.provider.domain.info.property.PropertyInfo;

public class Command {
	private String name;
	private Object serviceObject;
	private MethodInfo methodInfo;
	private List<Parameter> parameters;

	public Command(Object serviceObject, MethodInfo methodInfo, boolean shortCommand) throws IntrospectCommandLineException {
		this.serviceObject = serviceObject;
		this.methodInfo = methodInfo;
		// name
		this.name = createName(serviceObject, methodInfo, shortCommand);
		// parameters
		parameters = createParameters(methodInfo);

	}

	private List<Parameter> createParameters(MethodInfo methodInfo) throws IntrospectCommandLineException {
		List<Parameter> parameters = new ArrayList<Parameter>();
		Class<?> parameterClass = methodInfo.getParameterType().getType();

		if (parameterClass != null) {
			
			//get propertyInfos
			Filter<PropertyInfo> propertyInfoFilter = null; // TODO only show visible properties;
			FormOrderComparator propertyInfoComparator = new FormOrderComparator();
			Class<?> returnClass = methodInfo.getParameterType().getTypeOrGenericCollectionType();
			List<PropertyInfo> propertyInfos = Introspect.getDomainProvider().getPropertyInfos(returnClass, propertyInfoFilter, propertyInfoComparator);

			
			for (PropertyInfo propertyInfo : propertyInfos) {
				Parameter parameter = new Parameter(propertyInfo);
				parameters.add(parameter);
			}
		}
		return parameters;
	}

	private String createName(Object serviceObject, MethodInfo methodInfo, boolean shortCommand) {
		StringBuffer name = new StringBuffer();
		if (!shortCommand) {
			name.append(serviceObject.getClass().getName());
			name.append(".");
		}
		name.append(methodInfo.getName());
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

	public MethodInfo getMethodInfo() {
		return methodInfo;
	}

	public void setMethodInfo(MethodInfo methodInfo) {
		this.methodInfo = methodInfo;
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
			if ("bin".equals(jarFile.getName())) {//fix debug issue (when not executed from a jar)
				return Introspect.getInfoProvider().getApplication().getClass().getSimpleName();
			}
			return jarFile.getName();
		} catch (URISyntaxException e) {
			return null;
		}

	}

	public Object createMethodParameter() throws IntrospectCommandLineException {
		try {
			return methodInfo.createMethodParameter(serviceObject);
		} catch (Exception e) {
			throw new IntrospectCommandLineException("Could not create a new instance of method parameter: " + methodInfo.getParameterType().getType().getCanonicalName() + " for method: " + methodInfo.getNamePath(), e);
		}
	}

}
