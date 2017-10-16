package nth.reflect.javafx.demo;

import java.util.Arrays;
import java.util.List;

import nth.introspect.generic.xml.XmlConverter;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayName;
import nth.introspect.ui.style.ContentColor;
import nth.introspect.ui.style.MaterialColorPalette;
import nth.introspect.ui.style.basic.Color;
import nth.reflect.example.domain2.account.AccountRepository;
import nth.reflect.example.domain2.account.AccountService;
import nth.reflect.example.domain2.tag.TagService;
import nth.reflect.example.domain2.vault.VaultService;
import nth.reflect.javafx.ReflectApplicationForJavaFX;

@DisplayName(englishName="Reflect for JavaFX Demo")
public class ReflectForJavaFXDemo extends ReflectApplicationForJavaFX {

	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public List<Class<?>> getServiceClasses() {
		return Arrays.asList(VaultService.class, AccountService.class,TagService.class);
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return Arrays.asList(AccountRepository.class, XmlConverter.class);
	}
	
	@Override
	public Color getPrimaryColor() {
		return MaterialColorPalette.TEAL;
	}

	@Override
	public Color getSecondaryColor() {
		return MaterialColorPalette.GREY;
	}

	
	@Override
	public Color getAccentColor() {
		return MaterialColorPalette.ORANGE;
	}

	@Override
	public ContentColor getContentColor() {
		return ContentColor.WHITE;
	}


}
