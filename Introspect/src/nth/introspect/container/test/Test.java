package nth.introspect.container.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nth.introspect.container.InstantiationStrategy;
import nth.introspect.container.exception.ClassHasNoUsableConstructorException;
import nth.introspect.container.exception.IntrospectContainerException;

public class Test {

	public static void main(String[] args) throws IntrospectContainerException {
		List<Class<?>> classesToInstantiate=new ArrayList<Class<?>>();
		classesToInstantiate.add(A.class);
		classesToInstantiate.add(B.class);
		classesToInstantiate.add(C.class);
		classesToInstantiate.add(D.class);
		Map<Class<?>, Object> instances= new HashMap<Class<?>, Object>();
		
		try {
			InstantiationStrategy instantiationStrategy=new InstantiationStrategy(classesToInstantiate, instances);
			System.out.println(instantiationStrategy);
			List<Object> objects = instantiationStrategy.createInstances();
			System.out.println(objects);
			
		} catch (ClassHasNoUsableConstructorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
