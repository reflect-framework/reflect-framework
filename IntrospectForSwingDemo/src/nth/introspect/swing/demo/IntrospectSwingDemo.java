package nth.introspect.swing.demo;
import nth.introsepect.ui.swing.IntrospectInitializerForSwing;
import nth.introspect.Introspect;
import nth.introspect.domain.classdiagram.ClassDiagramService;
import nth.introspect.domain.test.TestsService;


public class IntrospectSwingDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntrospectInitializerForSwing initializer=new IntrospectInitializerForSwing(new IntrospectSwingDemo());
		initializer.registerFrontEndServiceClass(TestsService.class);
		initializer.registerFrontEndServiceClass(ClassDiagramService.class);
		Introspect.init(initializer);
	}

}
