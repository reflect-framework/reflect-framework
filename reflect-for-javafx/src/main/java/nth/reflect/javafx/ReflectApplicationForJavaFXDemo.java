package nth.reflect.javafx;

import java.util.Arrays;
import java.util.List;

public class ReflectApplicationForJavaFXDemo extends ReflectApplicationForJavaFX {

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(RfxView.class);//TODO
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
