package nth.reflect.fw.layer5provider.reflection.behavior.fonticon;

import java.lang.reflect.Method;
import java.util.Optional;

import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.behavior.BehavioralMethods;
import nth.reflect.fw.layer5provider.reflection.behavior.description.Description;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;
import nth.reflect.fw.layer5provider.url.fonticon.FontIconUrl;

/**
 * <p>
 * {@link ServiceObject}s ,{@link DomainObject}s and {@link ActionMethod}s can
 * have <a href=
 * "https://www.sitepoint.com/introduction-icon-fonts-font-awesome-icomoon/">FontIcons</a>
 * that are displayed in graphical user interfaces. These icons help the user to
 * quickly identify what they are looking at.
 * </p>
 * <p>
 * <a href=
 * "https://www.sitepoint.com/introduction-icon-fonts-font-awesome-icomoon/">Fonticons</a>
 * have become very popular in graphical user interface design. ApplicationIcon
 * fonts are just fonts. However, instead of containing letters or numbers, they
 * contain symbols and glyphs that can be used in a graphical user interface.
 * Advantages of font icons over <a href=
 * "https://en.wikipedia.org/wiki/Image_file_formats#Raster_formats">raster
 * format files</a>:
 * 
 * <ul>
 * <li>Browsers are usually faster in rendering fonts than loading image files.
 * </li>
 * <li>Fonts Icons are
 * <a href="https://en.wikipedia.org/wiki/Vector_graphics">vector graphics</a>,
 * so they scale well at any size.</li>
 * <li>As font icons are text characters, all icons can be styled in the same
 * way (size, color, shadow, etc)</li>
 * </ul>
 * </p>
 * <p>
 * There are many free font icons that you can use in your application (e.g.
 * download or include with a build tool like
 * <a href="https://maven.apache.org/">Maven</a>). Examples of free
 * ApplicationIcon fonts are:
 * <ul>
 * <li><a href="https://material.io/tools/icons/">Google Material Icons</a></li>
 * <li><a href="https://fontawesome.com">Font Awesome</a></li>
 * <li><a href="https://speckyboy.com/free-icon-fonts/">and many
 * more...</a></li>
 * </ul>
 * </p>
 * <p>
 * There are free online tools to create your own icon font, e.g by: importing
 * from icons from their (free) database, other (free) icon fonts or
 * <a href="https://en.wikipedia.org/wiki/Scalable_Vector_Graphics"> XML-based
 * vector image format (SVG)</a> from other (free) web sites. Example of web
 * site that help you build your icon font are:
 * <ul>
 * <li><a href="http://fontastic.me">Fontastic</a></li>
 * <li><a href="https://icomoon.io">Icomoon</a></li>
 * <li><a href="https://www.flaticon.com">FlatIcon</a></li>
 * </ul>
 * </p>
 * Note that the icon is also linked to the {@link Description} of that class so
 * that it can be displayed as a
 * <a href="https://en.wikipedia.org/wiki/Tooltip">tooltip</a> or can be used by
 * most
 * <a href="https://en.wikipedia.org/wiki/Computer_accessibility">accessibility
 * tools (e.g. for blind people)</a> when the user hovers over the icon.
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
public class FontIconModelFactory {

	public static FontIconModel create(Method actionMethod) {
		Optional<FontIconMethodModel> fontIconMethodModel = createIconMethodModel(actionMethod);
		if (fontIconMethodModel.isPresent()) {
			return fontIconMethodModel.get();
		}

		Optional<FontIconModel> iconAnnotationModel = createIconAnnotationModel(actionMethod);
		if (iconAnnotationModel.isPresent()) {
			return iconAnnotationModel.get();
		}

		return FontIconNoUrlModel.getInstance();
	}

	public static FontIconModel create(Class<?> objectClass) {
		Optional<FontIconMethodModel> fontIconMethodModel = createIconMethodModel(objectClass);
		if (fontIconMethodModel.isPresent()) {
			return fontIconMethodModel.get();
		}

		Optional<FontIconUrlModel> iconAnnotationModel = createIconAnnotationModel(objectClass);
		if (iconAnnotationModel.isPresent()) {
			return iconAnnotationModel.get();
		}

		return FontIconNoUrlModel.getInstance();
	}

	private static Optional<FontIconModel> createIconAnnotationModel(Method actionMethod) {
		FontIcon iconAnnotation = actionMethod.getAnnotation(FontIcon.class);
		if (iconAnnotation == null) {
			return Optional.empty();
		} else {
			try {
				FontIconUrl iconUrl = new FontIconUrl(iconAnnotation.fontIconUrl());
				return Optional.of(new FontIconUrlModel(iconUrl));
			} catch (Exception e) {
				return Optional.empty();
			}

		}

	}

	private static Optional<FontIconMethodModel> createIconMethodModel(Method actionMethod) {
		Optional<Method> iconMethod = BehavioralMethods.FONT_ICON.findFor(actionMethod);
		if (iconMethod.isPresent()) {
			return Optional.of(new FontIconMethodModel(iconMethod.get()));
		} else {
			return Optional.empty();
		}
	}

	private static Optional<FontIconUrlModel> createIconAnnotationModel(Class<?> objectClass) {
		FontIcon iconAnnotation = objectClass.getAnnotation(FontIcon.class);
		if (iconAnnotation == null) {
			return Optional.empty();
		} else {
			try {
				FontIconUrl iconUrl = new FontIconUrl(iconAnnotation.fontIconUrl());
				return Optional.of(new FontIconUrlModel(iconUrl));
			} catch (Exception e) {
				return Optional.empty();
			}
		}
	}

	private static Optional<FontIconMethodModel> createIconMethodModel(Class<?> objectClass) {
		Optional<Method> iconMethod = BehavioralMethods.FONT_ICON.findFor(objectClass);
		if (iconMethod.isPresent()) {
			return Optional.of(new FontIconMethodModel(iconMethod.get()));
		} else {
			return Optional.empty();
		}
	}

}
