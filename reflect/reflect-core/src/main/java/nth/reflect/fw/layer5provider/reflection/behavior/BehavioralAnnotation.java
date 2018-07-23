package nth.reflect.fw.layer5provider.reflection.behavior;

import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.documentation.ReflectDocumentationInterface;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer3domain.DomainObjectProperty;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethod;

/**
 * <p>
 * {@link BehavioralAnnotation}s are recognized by the {@link ReflectFramework}
 * and define how {@link DomainObject}s, {@link DomainObjectProperty}s or
 * {@link ActionMethod}s behave (how they act and how they displayed).
 * </p>
 * {@link BehavioralAnnotation}s are normally located before the declaration of
 * the member:
 * <ul>
 * <li>{@link BehavioralAnnotation}s for a class: are located before the class key word
 * </li>
 * <li>{@link BehavioralAnnotation}s for a {@link DomainObjectProperty}: are located
 * before the getter method of a {@link DomainObjectProperty}</li>
 * <li>{@link BehavioralAnnotation}s for an {@link ActionMethod}: are located before the
 * {@link ActionMethod}</li>
 * </ul>
 * </ul>
 * 
 * @author nilsth
 *
 */
public interface BehavioralAnnotation extends ReflectDocumentationInterface {

}
