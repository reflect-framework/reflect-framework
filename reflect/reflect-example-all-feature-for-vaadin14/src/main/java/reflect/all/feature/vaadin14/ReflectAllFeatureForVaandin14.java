package reflect.all.feature.vaadin14;

import java.util.List;

import com.vaadin.flow.router.Route;

import nth.reflect.all.feature.AllFeatureColorProvider;
import nth.reflect.all.feature.AllFeatureInfrastructureClasses;
import nth.reflect.all.feature.AllFeatureServiceClasses;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.ui.vaadin.ReflectApplicationForVaadin14;

@Route("")
public class ReflectAllFeatureForVaandin14 extends ReflectApplicationForVaadin14 {

	private static final long serialVersionUID = 3768830774992325956L;

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
