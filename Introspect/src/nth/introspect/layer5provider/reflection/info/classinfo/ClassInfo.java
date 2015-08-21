package nth.introspect.layer5provider.reflection.info.classinfo;

import java.net.URI;

import nth.introspect.layer5provider.ProviderContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.description.DescriptionModel;
import nth.introspect.layer5provider.reflection.behavior.displayname.DisplayNameModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModel;
import nth.introspect.layer5provider.reflection.behavior.icon.IconModelFactory;
import nth.introspect.layer5provider.reflection.behavior.title.TitleModel;
import nth.introspect.layer5provider.reflection.info.NameInfo;

/**
 * Provides information on a bean.<br>
 * This class is inspired by the BeanInfo class, which can not be use because it
 * is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class ClassInfo implements NameInfo {

	public final static String VISIBLE = "visible";
	public final String[] ANNOTATION_NAMES = new String[] { VISIBLE };
	public final static String[] METHOD_NAMES = new String[] { VISIBLE };
	private final String simpleName;
	private final String canonicalName;
	private final DescriptionModel descriptionModel;
	private final Class<?> objectClass;
	private final DisplayNameModel displayNameModel;
	private final TitleModel titleModel;
	private final IconModel iconModel;

	public ClassInfo(ProviderContainer providerContainer, Class<?> objectClass) {
		LanguageProvider languageProvider = providerContainer
				.get(LanguageProvider.class);
		ReflectionProvider reflectionProvider = providerContainer
				.get(ReflectionProvider.class);
		PathProvider pathProvider = providerContainer.get(PathProvider.class);
		this.simpleName = objectClass.getSimpleName();
		this.canonicalName = objectClass.getCanonicalName();
		this.objectClass = objectClass;
		this.displayNameModel = new DisplayNameModel(languageProvider,
				objectClass, simpleName, canonicalName);
		this.descriptionModel = new DescriptionModel(languageProvider,
				objectClass, simpleName, canonicalName);
		this.titleModel = new TitleModel(reflectionProvider);
		this.iconModel = IconModelFactory.create(objectClass,
				pathProvider.getImagePath());
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getCanonicalName() {
		return canonicalName;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public String getDisplayName() {
		return displayNameModel.getText();
	}

	public String getDescription() {
		return descriptionModel.getText();
	}

	public URI getIconURI(Object obj) {
		return iconModel.getURI(obj);
	}

	public String getTitle(Object obj) {
		return titleModel.getTitle(obj);
	}

	@Override
	public String toString() {
		return canonicalName;
	}

}
