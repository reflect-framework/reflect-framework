package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

public interface ParameterFactoryModel {

	public Object createNewMethodParameter(Object methodOwner) throws InstantiationException, IllegalAccessException;
}
