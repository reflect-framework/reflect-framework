package nth.reflect.all.feature.commandline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nth.reflect.all.feature.commandline.dom.AllFeatureServiceObject;
import nth.reflect.fw.ui.commandline.ReflectApplicationForCommandLine;

public class AllFeatureForCommandLine extends
		ReflectApplicationForCommandLine {

	public static void main(String[] arguments) {
		launch(arguments);;
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(AllFeatureServiceObject.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return new ArrayList<Class<?>>();
	}

}
