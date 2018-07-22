package nth.reflect.fw.junit.layer5provider.reflection.behavior.icon.forclass;

import java.net.URISyntaxException;

import nth.reflect.fw.layer5provider.reflection.behavior.icon.IconUriClassResource;

public class IconModelForClass_MethodClassReferenceUriNotExist {
	public String iconModelTestObjectMethodClassReferenceUriIcon() throws URISyntaxException {
		return new IconUriClassResource(getClass(), "bogus.png").toString();
	}
}
