package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.lang.reflect.Method;
import java.net.URL;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
/**
 * <p>{@link ReflectApplication} objects, {@link ServiceObject}s and
 * {@link DomainObject}s can have icons that are displayed in graphical user
 * interfaces. These icons help the user to quickly identify what they are
 * looking at.</p>
 * 
 * <h3>FontIcon Default</h3>
 * <p>
 * {@insert FontIconNoUrlModel}
 * </p>
 * 
 * <h3>FontIcon Annotation</h3>
 * <p>
 * {@insert FontIcon}
 * </p>
 * 
 * <h3>FontIcon Method</h3>
 * <p>
 * {@insert FontIconMethod}
 * </p>
 * 
 * 
 * 
 * @author nilsth
 *
 */
public class IconModelFactory {

	public static  IconModel create(Method actionMethod) {
		IconMethodModel iconMethodModel=createIconMethodModel(actionMethod);
		if (iconMethodModel!=null) {
			return iconMethodModel;			
		}

		IconUrlModel iconAnnotationModel = createIconAnnotationModel(actionMethod);
		if (iconAnnotationModel!=null) {
			return iconAnnotationModel;			
		}

		IconDefaultModel iconDefaultModel=new IconDefaultModel(actionMethod);
		return iconDefaultModel;
	}

	public static IconModel create(Class<?> objectClass) {
		IconMethodModel iconMethodModel=createIconMethodModel(objectClass);
		if (iconMethodModel!=null) {
			return iconMethodModel;			
		}

		IconUrlModel iconAnnotationModel = createIconAnnotationModel(objectClass);
		if (iconAnnotationModel!=null) {
			return iconAnnotationModel;			
		}

		IconDefaultModel iconDefaultModel=new IconDefaultModel(objectClass);
		return iconDefaultModel;
	}
	
	
	private static IconUrlModel createIconAnnotationModel(Method actionMethod) {
		Icon iconAnnotation = actionMethod.getAnnotation(Icon.class);
		if (iconAnnotation==null) {
			return null;
		} else {
			try {
				URL iconUrl=new URL(iconAnnotation.iconURL());
				return new IconUrlModel(iconUrl);
			} catch (Exception e) {
				return null;
			}
			
		}
		
	}

	private static IconMethodModel createIconMethodModel(Method actionMethod) {
		Method iconMethod = BehavioralMethods.FONT_ICON.findFor(actionMethod);
		if (iconMethod==null) {
			return null;
		} else {
			return new IconMethodModel(iconMethod);
		}
	}

	private static IconUrlModel createIconAnnotationModel(Class<?> objectClass) {
		Icon iconAnnotation = objectClass.getAnnotation(Icon.class);
		if (iconAnnotation==null) {
			return null;
		} else {
			try {
				URL iconUrl=new URL(iconAnnotation.iconURL());
				return new IconUrlModel(iconUrl);
			} catch (Exception e) {
				return null;
			}
			
		}
		
	}

	private static IconMethodModel createIconMethodModel(Class<?> objectClass) {
		Method iconMethod = BehavioralMethods.FONT_ICON.findFor(objectClass);
		if (iconMethod==null) {
			return null;
		} else {
			return new IconMethodModel(iconMethod);
		}
	}

}
