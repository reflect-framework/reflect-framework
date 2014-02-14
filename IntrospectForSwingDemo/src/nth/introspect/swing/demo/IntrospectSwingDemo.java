package nth.introspect.swing.demo;
import nth.introsepect.ui.swing.SwingIntrospectInitializer;
import nth.introspect.Introspect;
import nth.introspect.domain.classdiagram.ClassDiagramService;
import nth.introspect.domain.test.TestsService;


public class IntrospectSwingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		SwingIntrospectInitializer initializer=new SwingIntrospectInitializer(new IntrospectSwingDemo());
		initializer.addServiceClass(TestsService.class);
		initializer.addServiceClass(ClassDiagramService.class);
		Introspect.init(initializer);
	}

}
