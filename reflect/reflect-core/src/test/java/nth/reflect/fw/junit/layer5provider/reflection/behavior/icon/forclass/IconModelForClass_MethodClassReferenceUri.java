package nth.reflect.fw.junit.layer5provider.reflection.behavior.icon.forclass;

import java.net.URISyntaxException;

import nth.reflect.fw.layer5provider.reflection.behavior.icon.IconUriClassResource;

public class IconModelForClass_MethodClassReferenceUri {

	public String iconModelForClass_MethodClassReferenceUriIcon() throws URISyntaxException {
		return new IconUriClassResource(getClass(), "icon.png").toString();
	}
	
}
