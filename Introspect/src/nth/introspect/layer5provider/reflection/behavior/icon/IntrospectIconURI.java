package nth.introspect.layer5provider.reflection.behavior.icon;


/**
 * Returns a reference to one of the icons in the Introspect Library (images
 * stored in this package). See {@link IconUriClassResource} for the format of
 * the reference
 * 
 * @author nilsth
 *
 */
public class IntrospectIconURI {

//	public static final String CUT = createIconURI("cut.png");

	public static final String ICON = "introspectIconClassResource://nth.introspect.layer5provider.reflection.behavior.icon.IntrospectIconURI/cut.png";

	public static final String TEST = IconUriClassResource.SCHEMA+"://"+IntrospectIconURI.class.getCanonicalName()+"/cut.png";

//	private static String createIconURI(String iconName) {
//		try {
//			return IntrospectIconURI.class.getResource(iconName).toURI()
//					.toString();
//		} catch (URISyntaxException e) {
//			return null; // this should never happen if the imageNames exist in
//							// this package
//		}
//	}

}
