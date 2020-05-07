package nth.reflect.web.shop.swing;

import java.util.List;

import com.acme.web.shop.WebShopColorProvider;
import com.acme.web.shop.WebShopInfrastructureClasses;
import com.acme.web.shop.WebShopServiceClasses;

import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.fw.ui.swing.ReflectApplicationForSwing;

@DisplayName(defaultEnglish = "ACME Web Shop")
@Description(defaultEnglish = "ACME Web Shop for everything you need...")
public class WebShopForSwing extends ReflectApplicationForSwing {

	public static void main(String[] commandLineArguments) {
		launch();
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return WebShopServiceClasses.get();
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return WebShopInfrastructureClasses.get();
	}

	@Override
	public ColorProvider getColorProvider() {
		return WebShopColorProvider.get();
	}
}
