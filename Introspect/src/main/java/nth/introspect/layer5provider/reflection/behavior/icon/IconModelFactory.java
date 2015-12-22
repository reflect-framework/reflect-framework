package nth.introspect.layer5provider.reflection.behavior.icon;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;

import nth.introspect.IntrospectApplication;
import nth.introspect.layer2service.ServiceObject;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethods;
/**
 * <p>{@link IntrospectApplication} objects, {@link ServiceObject}s and
 * {@link DomainObject}s can have icons that are displayed in graphical user
 * interfaces. These icons help the user to quickly identify what they are
 * looking at.</p>
 * 
 * <h3>Icon Default</h3>
 * <p>
 * {@insert IconDefaultModel}
 * </p>
 * 
 * <h3>Icon Annotation</h3>
 * <p>
 * {@insert Icon}
 * </p>
 * 
 * <h3>Icon Method</h3>
 * <p>
 * {@insert IconMethod}
 * </p>
 * 
 * 
 * 
 * @author nilsth
 *
 */
public class IconModelFactory {

	public static  IconModel create(Method actionMethod, URI imagePath) {
		IconMethodModel iconMethodModel=createIconMethodModel(actionMethod,imagePath);
		if (iconMethodModel!=null) {
			return iconMethodModel;			
		}

		IconUriModel iconAnnotationModel = createIconAnnotationModel(actionMethod, imagePath);
		if (iconAnnotationModel!=null) {
			return iconAnnotationModel;			
		}

		IconDefaultModel iconDefaultModel=new IconDefaultModel(actionMethod);
		return iconDefaultModel;
	}

	public static IconModel create(Class<?> objectClass, URI imagePath) {
		IconMethodModel iconMethodModel=createIconMethodModel(objectClass,imagePath);
		if (iconMethodModel!=null) {
			return iconMethodModel;			
		}

		IconUriModel iconAnnotationModel = createIconAnnotationModel(objectClass, imagePath);
		if (iconAnnotationModel!=null) {
			return iconAnnotationModel;			
		}

		IconDefaultModel iconDefaultModel=new IconDefaultModel(objectClass);
		return iconDefaultModel;
	}
	
	
	private static IconUriModel createIconAnnotationModel(Method actionMethod, URI imagePath) {
		Icon iconAnnotation = actionMethod.getAnnotation(Icon.class);
		if (iconAnnotation==null) {
			return null;
		} else {
			try {
				URI uri = IconUriFactory.create(iconAnnotation.iconURI(), imagePath);
				return new IconUriModel(uri);
			} catch (URISyntaxException e) {
				return null;
			}
			
		}
		
	}

	private static IconMethodModel createIconMethodModel(Method actionMethod, URI imagePath) {
		Method iconMethod = BehavioralMethods.ICON.findFor(actionMethod);
		if (iconMethod==null) {
			return null;
		} else {
			return new IconMethodModel(iconMethod, imagePath);
		}
	}

	private static IconUriModel createIconAnnotationModel(Class<?> objectClass, URI imagePath) {
		Icon iconAnnotation = objectClass.getAnnotation(Icon.class);
		if (iconAnnotation==null) {
			return null;
		} else {
			try {
				URI uri = IconUriFactory.create(iconAnnotation.iconURI(), imagePath);
				return new IconUriModel(uri);
			} catch (URISyntaxException e) {
				return null;
			}
			
		}
		
	}

	private static IconMethodModel createIconMethodModel(Class<?> objectClass, URI imagePath) {
		Method iconMethod = BehavioralMethods.ICON.findFor(objectClass);
		if (iconMethod==null) {
			return null;
		} else {
			return new IconMethodModel(iconMethod, imagePath);
		}
	}

}
