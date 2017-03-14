package nth.reflect.javafx.demo;

import java.util.Arrays;
import java.util.List;

import com.acme.web.shop.product.ProductRepository;
import com.acme.web.shop.product.ProductService;
import com.acme.web.shop.shopingcart.ShoppingCartService;

import nth.introspect.domain.person.PersonService;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.reflect.javafx.ReflectApplicationForJavaFX;
import nth.reflect.javafx.RfxView;

@DisplayName(englishName="Reflect for JavaFX Demo")
public class ReflectForJavaFXDemo extends ReflectApplicationForJavaFX {

	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(ProductService.class, ShoppingCartService.class,  PersonService.class);//TODO
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(ProductRepository.class);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
