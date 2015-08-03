package nth.introspect.layer5provider.reflection.info.classinfo;

import java.net.URI;

import nth.introspect.generic.valuemodel.ValueModels;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.path.id.ClassIconID;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.NameInfo;
import nth.introspect.layer5provider.reflection.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.SimpleValue;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.TextValue;
import nth.introspect.layer5provider.reflection.info.valuemodel.impl.TitleValue;

/**
 * Provides information on a bean.<br>
 * This class is inspired by the BeanInfo class, which can not be use because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class ClassInfo implements NameInfo {

	public final static String TEXT = "text";//TODO rename to typeName?
	public final static String DESCRIPTION = "description";
	public final static String VISIBLE = "visible";
	public static final String TITLE = "title";
	public final String[] ANNOTATION_NAMES = new String[] {TEXT, DESCRIPTION, VISIBLE};
	public final static String[] METHOD_NAMES = new String[] {VISIBLE, TITLE};
	private static final String ICON = "icon";
	private static final String REG_EXP_TO_REMOVE_SERVICE_SUFFIX = "Service$";
	private ValueModels valueModels;
	private final String simpleName;
	private final String conicalName;
	private final Class<?> objectClass;
	private final PathProvider pathProvider;

	public ClassInfo(ReflectionProvider reflectionProvider, PathProvider pathProvider, LanguageProvider languageProvider, Class<?> objectClass)  {
		this.pathProvider = pathProvider;
		this.simpleName = objectClass.getSimpleName();
		this.conicalName = objectClass.getCanonicalName();
		this.objectClass = objectClass;

		valueModels = new ValueModels();

		// create default value getters
		valueModels.put(TEXT, new TextValue(this, languageProvider,  TEXT, REG_EXP_TO_REMOVE_SERVICE_SUFFIX));
		valueModels.put(DESCRIPTION, new TextValue(this, languageProvider, DESCRIPTION, REG_EXP_TO_REMOVE_SERVICE_SUFFIX));
		// valueModels.put(ICON, new IconValue(this));
		valueModels.put(ICON, new SimpleValue(new ClassIconID(pathProvider, objectClass)));
		valueModels.put(VISIBLE, new SimpleValue(true));
		valueModels.put(TITLE, new TitleValue(reflectionProvider, languageProvider));

		// create value getters from annotations
		// TODO when needed valueModels.putAll(AnnotationValueModelFactory.create(this, ANNOTATION_NAMES));

		// create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// create xml value getters
		// TODO valueModels.putAll(XmlValueModelFactory.create( this));
	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	@Override
	public String getCanonicalName() {
		return conicalName;
	}

	public Class<?> getObjectClass() {
		return objectClass;
	}

	public String getText() {
		return valueModels.getStringValue(TEXT);
	}

	public String getDescription() {
		return valueModels.getStringValue(DESCRIPTION);
	}

	public CharSequence getIconID(Object obj) {
		Object value = valueModels.getValue(ICON, obj);
		if (value == null) {
			return null;
		}
		return (CharSequence) value;
	}

	public URI getIconURI(Object obj) {
		return pathProvider.getImagePath(getIconID(obj));
	}

	public Boolean isVisible(Object domainObject) {
		return valueModels.getBooleanValue(VISIBLE, domainObject);
	}

	public String getTitle(Object domainObject) {
		if (domainObject == null) {
			return "";
		} else {
			return valueModels.getStringValue(TITLE, domainObject);
		}
	}

	@Override
	public String toString() {
		return conicalName;
	}

}
