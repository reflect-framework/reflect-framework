package nth.reflect.all.feature.javafx;

import java.util.List;

import nth.reflect.all.feature.AllFeatureColorProvider;
import nth.reflect.all.feature.AllFeatureInfrastructureClasses;
import nth.reflect.all.feature.AllFeatureServiceClasses;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;

@DisplayName(defaultEnglish = "Reflect for JavaFX Demo")
public class AllFeatureForJavaFX extends ReflectApplicationForJavaFX {

	public static void main(String[] args) {
		launch(args);
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
