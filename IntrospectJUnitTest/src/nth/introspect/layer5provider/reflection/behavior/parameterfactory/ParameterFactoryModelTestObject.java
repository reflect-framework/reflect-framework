package nth.introspect.layer5provider.reflection.behavior.parameterfactory;

import nth.introspect.layer5provider.reflection.ReflectionProvider;


public class ParameterFactoryModelTestObject {



	public ParameterFactoryModelTestObject() {
	}

	public void actionMethod1(ParameterTestObject parameter) {
		if (parameter==null) {
			throw new NullPointerException();
		}
	}

	public Object actionMethod1ParameterFactory() {
		ParameterTestObject parameter=new ParameterTestObject();
		return parameter;
	}
	

	@ParameterFactory
	public void actionMethod2(ParameterTestObject parameter) {
		if (parameter==null) {
			throw new NullPointerException();
		}
	}


}
