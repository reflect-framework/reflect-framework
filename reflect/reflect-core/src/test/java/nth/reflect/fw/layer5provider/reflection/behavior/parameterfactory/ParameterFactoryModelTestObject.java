package nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory;

import nth.reflect.fw.layer5provider.reflection.behavior.parameterfactory.ParameterFactory;


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
