package nth.introspect.junit.layer5provider.reflection.behavior.icon.forclass;

import java.net.URISyntaxException;

import nth.introspect.layer5provider.reflection.behavior.icon.IconUriClassResource;

public class IconModelForClass_MethodClassReferenceUriNotExist {
	public String iconModelTestObjectMethodClassReferenceUriIcon() throws URISyntaxException {
		return new IconUriClassResource(getClass(), "bogus.png").toString();
	}
}
