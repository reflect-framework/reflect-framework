package nth.reflect.fw.layer5provider.reflection.behavior.title;

import java.util.List;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;
import nth.reflect.fw.layer5provider.reflection.info.property.PropertyInfo;

/**
 * <p>
 * {@link DomainObject}s that have identity (entities) need to have a dynamic
 * title that help users to distinguish objects of the same type (e.g. Type
 * customer versus “John Doe”). This title should exist of all the properties
 * that identify the object. The title is therefore dynamic: it changes when the
 * value of these properties change.
 * </p>
 * 
 * <p>
 * In example: The title of a customer could be a customer number, followed by
 * the given name, followed by the family name. If the customer changes it’s
 * name, than so does the title (but not its identity)
 * </p>
 * 
 * <h3>Title Default</h3>
 * <p>
 * The {@link ReflectFramework} provides a default title based on the properties
 * that are normally displayed in tables. This is a best guess. It is therefore
 * recommended to always implement the toString method.
 * </p>
 * 
 * <h3>Title (toString) method</h3>
 * <p>
 * You can define the title by overriding the the toString() method of your
 * {@link DomainObject}
 * </p>
 * <p>
 * (TODO example with Customer toString and TitleBuilder)
 * </p>
 * 
 * <h3>Title builder</h3>
 * <p>
 * {@insert TitleBuilder}
 * </p>
 * 
 * @author nilsth
 *
 */
public class TitleModel {

	private static final Object TITLE_SEPARATOR = " ";
	private final ReflectionProvider reflectionProvider;

	public TitleModel(ReflectionProvider reflectionProvider) {
		this.reflectionProvider = reflectionProvider;
	}

	public String getTitle(Object obj) {
		String objectToString = obj.toString();
		if (objectToString == null || objectToString.trim().length() == 0
				|| objectToString.equals(defaultToStringValue(obj))) {
			return createTitle(obj);

		} else {
			return obj.toString();
		}
	}

	private String createTitle(Object obj) {
		StringBuffer title = new StringBuffer();
		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(obj.getClass());
		List<PropertyInfo> propertyInfos = domainClassInfo.getPropertyInfosSortedAndVisibleInTable();
		for (PropertyInfo propertyInfo : propertyInfos) {
			String propertyValue = propertyInfo.getStringValue(obj).trim();
			if (!propertyValue.isEmpty())
				if (title.length() > 0) {
					title.append(TITLE_SEPARATOR);
				}
			title.append(propertyValue);
		}
		return title.toString();
	}

	private String defaultToStringValue(Object obj) {
		String defaultToStringImplValue = obj.getClass().getName() + "@" + Integer.toHexString(obj.hashCode());
		return defaultToStringImplValue;
	}
}
