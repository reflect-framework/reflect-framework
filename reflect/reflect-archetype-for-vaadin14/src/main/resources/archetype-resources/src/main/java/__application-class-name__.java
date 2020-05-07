package ${package};

import java.util.List;

import com.acme.web.shop.WebShopColors;
import com.acme.web.shop.WebShopInfrastructureClasses;
import com.acme.web.shop.WebShopServiceClasses;
import com.vaadin.flow.router.Route;

import nth.reflect.fw.gui.style.ReflectColors;
import nth.reflect.ui.vaadin.ReflectApplicationForVaadin14;

@Route("")
public class ${application-class-name} extends ReflectApplicationForVaadin14 {

	@Override
	public List<Class<?>> getServiceClasses() {
		return WebShopServiceClasses.get();
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return WebShopInfrastructureClasses.get();
	}
	
	@Override
	public ReflectColors getColors() {
		return WebShopColors.get();
	}
}
