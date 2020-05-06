package nth.reflect.all.feature.swing;

import java.util.List;

import nth.reflect.all.feature.AllFeatureColorProvider;
import nth.reflect.all.feature.AllFeatureInfrastructureClasses;
import nth.reflect.all.feature.AllFeatureServiceClasses;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.ui.swing.ReflectApplicationForSwing;

public class AllFeatureForSwing extends ReflectApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return AllFeatureServiceClasses.get();
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return AllFeatureInfrastructureClasses.get();
	}

	@Override
	public ColorProvider getColorProvider() {
		return AllFeatureColorProvider.get();
	}

}
