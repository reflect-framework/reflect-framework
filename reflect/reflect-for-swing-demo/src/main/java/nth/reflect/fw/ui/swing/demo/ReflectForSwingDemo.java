package nth.reflect.fw.ui.swing.demo;
import java.util.ArrayList;
import java.util.List;

import nth.reflect.example.domain.classdiagram.ClassDiagramService;
import nth.reflect.example.domain.test.TestsService;
import nth.reflect.fw.ui.swing.ReflecttApplicationForSwing;


public class ReflectForSwingDemo extends ReflecttApplicationForSwing {

	/**
	 * @param args
	 */
	public static void main(String[] arguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		List<Class<?>> serviceClasses=new ArrayList<Class<?>>();
		serviceClasses.add(TestsService.class);
		serviceClasses.add(ClassDiagramService.class);
		return serviceClasses;
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

}
