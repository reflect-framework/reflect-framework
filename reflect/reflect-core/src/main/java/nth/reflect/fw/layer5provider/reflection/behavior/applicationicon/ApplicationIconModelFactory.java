package nth.reflect.fw.layer5provider.reflection.behavior.applicationicon;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Optional;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;

/**
 * <p>
 * With a {@link ApplicationIcon} the graphical user interface of a
 * {@link ReflectApplication} can easily identified by the user. This icon is a
 * file that contains an image. The type, size and depends on the
 * type(platforms) of your application. In example:
 * <ul>
 * <li><a href=
 * "https://docs.oracle.com/javase/8/javafx/api/javafx/stage/Stage.html#getIcons--">Icon
 * requirements for JavaFX (desktop) applications</a></li>
 * <li><a href=
 * "https://developer.android.com/studio/write/image-asset-studio">Icon
 * requirements for Android (mobile) applications</a></li>
 * <li><a href="https://en.m.wikipedia.org/wiki/Favicon">Icon requirements for
 * Web applications</a></li>
 * </ul>
 * </p>
 * 
 * <h3>ApplicationIcon Default</h3>
 * <p>
 * The default is no {@link ApplicationIcon}
 * </p>
 * 
 * <h3>ApplicationIcon Annotation</h3>
 * <p>
 * {@insert ApplicationIcon}
 * </p>
 * 
 * <h3>ApplicationIcon Method</h3>
 * <p>
 * {@insert ApplicationIconMethod}
 * </p>
 * 
 * 
 * 
 * @author nilsth
 *
 */
public class ApplicationIconModelFactory {

	public static ApplicationIconModel create(ReflectApplication reflectApplication) {
		Optional<ApplicationIconMethodModel> applicationIconMethodModel = createIconMethodModel(
				reflectApplication);
		if (applicationIconMethodModel.isPresent()) {
			return applicationIconMethodModel.get();
		}

		Optional<ApplicationIconUrlModel> iconAnnotationModel = createIconAnnotationModel(reflectApplication.getClass());
		if (iconAnnotationModel.isPresent()) {
			return iconAnnotationModel.get();
		}

		return new ApplicationIconNoUrlModel();
	}

	private static Optional<ApplicationIconUrlModel> createIconAnnotationModel(
			Class<? extends ReflectApplication> reflectApplicationClass) {
		ApplicationIcon iconAnnotation = reflectApplicationClass.getAnnotation(ApplicationIcon.class);
		if (iconAnnotation == null) {
			return Optional.empty();
		} else {
			try {
				URL iconUrl = new URL(iconAnnotation.iconURL());
				return Optional.of(new ApplicationIconUrlModel(iconUrl));
			} catch (Exception e) {
				return Optional.empty();
			}
		}

	}

	private static Optional<ApplicationIconMethodModel> createIconMethodModel(ReflectApplication reflectApplication) {
		Optional<Method> iconMethod = BehavioralMethods.APPLICATION_ICON.findFor(reflectApplication.getClass());
		if (iconMethod .isPresent()) {
			return Optional.of(new ApplicationIconMethodModel(reflectApplication, iconMethod.get()));
		} else {
			return Optional.empty();
		}
	}

}
