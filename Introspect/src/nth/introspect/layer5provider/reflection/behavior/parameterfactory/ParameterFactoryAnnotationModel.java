package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import nth.introspect.layer3domain.DomainContainer;
import nth.introspect.layer3domain.DomainLayer;
import nth.introspect.layer3domain.DomainObject;

/**
 * See {@link ParameterFactory} annotation
 * 
 * @author nilsth
 *
 */
public class ParameterFactoryAnnotationModel implements ParameterFactoryModel {

	private final Class<?> methodParameterType;

	public ParameterFactoryAnnotationModel(Class<?> methodParameterType) {
		this.methodParameterType = methodParameterType;
	}

	/**
	 * TODO to consider: would be nice if we could use the
	 * {@link DomainContainer} to create the parameter, like: Object
	 * domainObject=domainContainer.get(methodParameterType); but this would
	 * violate a important design principle: the Provider Layer should have no
	 * knowledge of the upper layers (in this case the {@link DomainLayer}).
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@Override
	public Object createNewMethodParameter(Object methodOwner) throws InstantiationException, IllegalAccessException {
		Object domainObject =methodParameterType.newInstance();
		return domainObject;
	}
}
