package nth.introspect.layer5provider.domain.info.classinfo;

import java.net.URI;

import nth.introspect.generic.valuemodel.ValueModels;
import nth.introspect.layer5provider.domain.info.DomainInfoProvider;
import nth.introspect.layer5provider.domain.info.IntrospectionInfo;
import nth.introspect.layer5provider.domain.info.valuemodel.factories.MethodValueModelFactory;
import nth.introspect.layer5provider.domain.info.valuemodel.impl.SimpleValue;
import nth.introspect.layer5provider.domain.info.valuemodel.impl.TextValue;
import nth.introspect.layer5provider.domain.info.valuemodel.impl.TitleValue;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.path.PathProvider;
import nth.introspect.layer5provider.path.id.ClassIconID;

/**
 * Provides information on a bean.<br>
 * This class is inspired by the BeanInfo class, which can not be use because it is not implemented by Android
 * 
 * @author nilsth
 * 
 */
public class ClassInfo implements IntrospectionInfo {

	public final static String TEXT = "text";//TODO rename to typeName?
	public final static String DESCRIPTION = "description";
	public final static String VISIBLE = "visible";
	public static final String TITLE = "title";
	public final String[] ANNOTATION_NAMES = new String[] {TEXT, DESCRIPTION, VISIBLE};
	public final static String[] METHOD_NAMES = new String[] {VISIBLE, TITLE};
	private static final String ICON = "icon";
	private static final String REG_EXP_TO_REMOVE_SERVICE_SUFFIX = "Service$";
	private ValueModels valueModels;
	private final String name;
	private final String namePath;
	private final Class<?> introspectedClass;
	private final PathProvider pathProvider;

	public ClassInfo(DomainInfoProvider domainInfoProvider, PathProvider pathProvider, LanguageProvider languageProvider, Class<?> introspectedClass) {
		this.pathProvider = pathProvider;
		this.name = introspectedClass.getSimpleName();
		this.namePath = introspectedClass.getCanonicalName();
		this.introspectedClass = introspectedClass;

		valueModels = new ValueModels();

		// create default value getters
		valueModels.put(TEXT, new TextValue(this, languageProvider,  TEXT, REG_EXP_TO_REMOVE_SERVICE_SUFFIX));
		valueModels.put(DESCRIPTION, new TextValue(this, languageProvider, DESCRIPTION, REG_EXP_TO_REMOVE_SERVICE_SUFFIX));
		// valueModels.put(ICON, new IconValue(this));
		valueModels.put(ICON, new SimpleValue(new ClassIconID(pathProvider, introspectedClass)));
		valueModels.put(VISIBLE, new SimpleValue(true));
		valueModels.put(TITLE, new TitleValue(domainInfoProvider, languageProvider));

		// create value getters from annotations
		// TODO when needed valueModels.putAll(AnnotationValueModelFactory.create(this, ANNOTATION_NAMES));

		// create method value getters
		valueModels.putAll(MethodValueModelFactory.create(this, METHOD_NAMES));

		// create xml value getters
		// TODO valueModels.putAll(XmlValueModelFactory.create( this));
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getNamePath() {
		return namePath;
	}

	public Class<?> getBeanClass() {
		return introspectedClass;
	}

	public String getText() {
		return valueModels.getStringValue(TEXT);
	}

	public String getDescription() {
		return valueModels.getStringValue(DESCRIPTION);
	}

	public CharSequence getIconID(Object introspectedObject) {
		Object value = valueModels.getValue(ICON, introspectedObject);
		if (value == null) {
			return null;
		}
		return (CharSequence) value;
	}

	public URI getIconURI(Object introspectedObject) {
		return pathProvider.getImagePath(getIconID(introspectedObject));
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
		return namePath;
	}

}
