package nth.reflect.fw.layer5provider.validation;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer3domain.DomainObjectProperty;

/**
 * A {@link DomainObjectProperty} can be validated by putting validation
 * annotations before the getter method of a property. The
 * {@link ReflectFramework} uses the JSR303 compliant Apache BVal library for
 * validation. Please read the Apache BVal documentation on how to annotate the
 * getter methods of the properties.
 * 
 * @author nilsth
 *
 */
public interface ValidationAnnotation extends ReflectDocumentationInterface {

}
