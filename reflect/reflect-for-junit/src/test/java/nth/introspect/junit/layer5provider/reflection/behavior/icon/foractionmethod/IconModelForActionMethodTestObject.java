package nth.introspect.junit.layer5provider.reflection.behavior.icon.foractionmethod;

import java.net.URISyntaxException;

import nth.introspect.layer5provider.reflection.behavior.icon.Icon;
import nth.introspect.layer5provider.reflection.behavior.icon.IconUriClassResource;
import nth.introspect.ui.images.IntrospectImage;

public class IconModelForActionMethodTestObject {

	private static final String CLASS_RESOURCE_TO_ICON = IntrospectImage.EDIT_COPY2;
	private static final String CLASS_RESOURCE_URI_TO_NONE_EXISTING_ICON = IconUriClassResource.SCHEMA + "://bogus/bogus.png";
	private static final String BOGUS_PNG = "bogus.png";
	private static final String ICON_PNG = "icon.png";
	private static final String ABSOLUTE_URI_TO_NONE_EXISTING_ICON = "file:/C:/bogus/bogus.png";
	public static final String ABSOLUTE_URI_TO_ICON =  "https://www.google.nl/images/nav_logo242.png";

	public void actionMethodIconDefaultUri() {

	}

	public void actionMethodIconDefaultUriNotExist() {

	}

	@Icon(iconURI = CLASS_RESOURCE_TO_ICON)
	public void actionMethodIconAnnotationClassReferenceUri() {

	}

	@Icon(iconURI = CLASS_RESOURCE_URI_TO_NONE_EXISTING_ICON)
	public void actionMethodIconAnnotationClassReferenceUriNotExist() {
	}

	@Icon(iconURI = ICON_PNG)
	public void actionMethodIconAnnotationRelativeUri() {

	}

	@Icon(iconURI = BOGUS_PNG)
	public void actionMethodIconAnnotationRelativeUriNotExist() {

	}

	@Icon(iconURI = ABSOLUTE_URI_TO_ICON)
	public void actionMethodIconAnnotationAbsoluteUri() {

	}

	@Icon(iconURI = ABSOLUTE_URI_TO_NONE_EXISTING_ICON)
	public void actionMethodIconAnnotationAbsoluteUriNotExist() {

	}

	public void actionMethodIconMethodClassReferenceUri() {

	}

	public String actionMethodIconMethodClassReferenceUriIcon() {
		return CLASS_RESOURCE_TO_ICON;
	}

	public void actionMethodIconMethodClassReferenceUriNotExist() {

	}

	public String actionMethodIconMethodClassReferenceUriNotExistIcon() {
		return CLASS_RESOURCE_URI_TO_NONE_EXISTING_ICON;
	}

	public void actionMethodIconMethodRelativeUri() {

	}

	public String actionMethodIconMethodRelativeUriIcon() {
		return ICON_PNG;
	}

	public void actionMethodIconMethodRelativeUriNotExist() {

	}

	public String actionMethodIconMethodRelativeUriNotExistIcon() {
		return BOGUS_PNG;
	}

	public void actionMethodIconMethodAbsoluteUri(){
		
	}

	public String actionMethodIconMethodAbsoluteUriIcon() throws URISyntaxException {
		return ABSOLUTE_URI_TO_ICON; 
		
	}


	public void actionMethodIconMethodAbsoluteUriNotExist(){
		
	}

	public String actionMethodIconMethodAbsoluteUriNotExistIcon() {
		return ABSOLUTE_URI_TO_NONE_EXISTING_ICON; 
	}


}
