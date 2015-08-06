package nth.introspect.layer5provider.reflection.behavior.title;

import java.text.Format;
import java.util.List;

import nth.introspect.documentation.IntrospectFramework;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.layer5provider.reflection.info.type.TypeCategory;

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
 * The {@link IntrospectFramework} provides a default title based on the
 * properties that are normally displayed in tables. This is a best guess. It is
 * therefore recommended to always implement the toString method.
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
				|| objectToString.equals(dafaultToStringValue(obj))) {
			return createTitle(obj);

		} else {
			return obj.toString();
		}
	}

	private String createTitle(Object obj) {
		StringBuffer title = new StringBuffer();
		List<PropertyInfo> propertyInfos = reflectionProvider
				.getOrderedAndVisiblePropertyInfos(obj.getClass());
		for (PropertyInfo propertyInfo : propertyInfos) {
			Object propertyValue = propertyInfo.getValue(obj);
			Format format = propertyInfo.getFormat();
			TypeCategory typeCatagory = propertyInfo.getPropertyType()
					.getTypeCategory();
			
			
			if (propertyValue != null
					&& typeCatagory != TypeCategory.COLLECTION_TYPE) {
				StringBuffer propertyText = new StringBuffer();
				String propertyValueText = format.format(propertyValue);
				propertyText.append(propertyValueText);
				if (propertyText.toString().trim().length() > 0) {
					if (title.length() > 0) {
						title.append(TITLE_SEPARATOR);
					}
					title.append(propertyText);
				}
			}
		}
		return title.toString();
	}

	private String dafaultToStringValue(Object obj) {
		String defaultToStringImplValue = obj.getClass().getName() + "@"
				+ Integer.toHexString(obj.hashCode());
		return defaultToStringImplValue;
	}
}
