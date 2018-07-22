package nth.reflect.fw.junit.layer5provider.reflection.behavior.icon;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import nth.reflect.fw.junit.layer5provider.reflection.behavior.icon.forclass.IconModelForClass_Default;
import nth.reflect.fw.layer5provider.reflection.behavior.icon.IconUriClassResource;

import org.junit.Test;

public class IconUriClassResourceTest {

	private static final String ICON_PNG = "icon.png";

	@Test
	public void iconURIClassResourceFromClassAndResourceName() throws URISyntaxException {
		IconUriClassResource iconUriClassResource= new IconUriClassResource(IconModelForClass_Default.class,ICON_PNG);
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, IconModelForClass_Default.class.getCanonicalName(), ICON_PNG);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}
	
	private String getIconUriString(String schema, String canonicalClassname, String resourceName) {
		StringBuilder iconUri=new StringBuilder();
		iconUri.append(schema);
		iconUri.append("://");
		iconUri.append(canonicalClassname);
		iconUri.append("/");
		iconUri.append(resourceName);
		return iconUri.toString();
	}

	@Test
	public void iconURIClassResourceFromStringtoString() throws URISyntaxException {
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, IconModelForClass_Default.class.getCanonicalName(), ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}


	@Test (expected=URISyntaxException.class)
	public void iconURIClassResourceFromStringInvalidSchemaName() throws URISyntaxException {
		String iconUriString=getIconUriString( "wrong", IconModelForClass_Default.class.getCanonicalName(), ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}

	@Test (expected=URISyntaxException.class)
	public void iconURIClassResourceFromStringNoClassName() throws URISyntaxException {
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, null, ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}



	@Test (expected=URISyntaxException.class)
	public void iconURIClassResourceFromStringEmptyClassName() throws URISyntaxException {
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, " ", ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}
	


	@Test (expected=URISyntaxException.class)
	public void iconURIClassResourceFromStringInvalidClassName() throws URISyntaxException {
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, "wrong", ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		assertEquals(iconUriString,iconUriClassResource.toString());
	}

	@Test 
	public void iconURIClassResourceToAbsolutePath() throws URISyntaxException {
		String iconUriString=getIconUriString( IconUriClassResource.SCHEMA, getClass().getCanonicalName(), ICON_PNG);
		IconUriClassResource iconUriClassResource= new IconUriClassResource(iconUriString);
		URI absoluteIconUri = iconUriClassResource.getAbsoluteURI();
		URI expectedIconUri=getClass().getResource(ICON_PNG).toURI();
		assertEquals(expectedIconUri, absoluteIconUri);
		assertTrue(absoluteIconUri.toString().endsWith(ICON_PNG));
		assertTrue(new File(absoluteIconUri).exists());
	}

	

}
