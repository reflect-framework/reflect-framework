package nth.introspect.layer5provider.reflection.behavior.validation;

import nth.introspect.layer1userinterface.controller.UserInterfaceController;
import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.introspect.layer5provider.reflection.info.method.ActionMethod;
import nth.introspect.layer5provider.validation.ValidationProvider;

/**
 * <p>
 * The {@link UserInterfaceController} sometimes let’s the user edit an
 * {@link DomainObject} before it is passed as an {@link ActionMethod} parameter
 * (depending on how the {@link ActionMethod} is annotated, see
 * {@link ExecutionMode}). The {@link UserInterfaceController} then validates
 * the edited {@link DomainObject} using the {@link ValidationProvider} before
 * the {@link ActionMethod} is called.The {@link ValidationProvider} will use
 * validation annotations and validation methods that are located in the
 * {@link DomainObject}s them selves to validate the {@link DomainObject}s.
 * </p>
 * 
 * <h3>Validation annotations</h3>
 * <p>
 * A {@link DomainObjectProperty} can be validated by putting validation
 * annotations before the getter method of a property. Validation annotations
 * are often used for the more basic validations. The following sections will
 * explain the specific validation annotations that can be used.
 * </p>
 * <h3>Validation methods</h3>
 * <p>
 * Properties and DomainObjects can also be validated with validation methods
 * located in the domain class so that you can do more complicated validation
 * using code.
 * </p>
 * <p>
 * ValidationMethods conventions:
 * <ul>
 * <li>Syntax: validate&lt;{@link DomainObjectProperty}Name or
 * businessRuleName&gt;. E.g. for a {@link DomainObjectProperty}:
 * validateStartDate() or for business rule: validateCustommerAlreadyExists()</li>
 * <li>Method may not have a method parameter</li>
 * <li>Method must return a ValidationResult</li>
 * <li>Method must be public</li>
 * <li>Method may not be static</li>
 * <li>May not change the state of the {@link DomainObject}</li>
 * </ul>
 * </p>
 * <p>
 * The following sections will explain the specific ValidationMethods that can
 * be used.
 * </p>
 * <h3>Types of validations</h3>
 * <p>
 * There are different types of validation
 * <li>Is the property mandatory?</li>
 * <li>Has the property the correct format?</li>
 * <li>Is the property within the correct range?</li>
 * <li>Does the {@link DomainObject} comply to the defined business rules?</li>
 * </p>
 * <h3>Validating mandatory properties</h3>
 * <p>
 * If a property is mandatory (must have a value) you can add one of the
 * following annotations:
 * </p>
 * <ul>
 * <li>&#64;NotNull</li>
 * <li>&#64;NotEmpty</li>
 * </ul>
 * TODO EXAMPLE <h3>Validating correct format</h3>
 * <p>
 * Some properties require a certain format (such as property types: date, time
 * and currency). This format can be defined by adding the following
 * annotations:
 * </p>
 * <ul>
 * <li>&#64;Regexp</li>
 * <li>&#64;Mask</li>
 * </ul>
 * TODO EXAMPLE
 * 
 * <h3>Validating Range</h3>
 * <p>
 * Add one of the following annotations if a property needs to be within a
 * predefined range:
 * </p>
 * &#64;GreatherThan TODO SEE JSR??? TO FIND ALL ANNOTATIONS TODO EXAMPLE
 * 
 * <h3>Validating business rules</h3>
 * <p>
 * Business rules are needed when:
 * </p>
 * <ul>
 * <li>Validation is more complex and can not be handled with
 * ValidationAnnotations, but needs to be done with code.</li>
 * <li>Validation requires multiple property values (e.g. start date must be
 * before end date)</li>
 * <li>Validation requires a different source in order to validate (e.g. check a
 * repository if a name if unique, or ask the LanguageProvider for the localised
 * format)</li>
 * </ul>
 * BusinessRules are defined by ValidationMethods.
 * 
 * TODO EXAMPLE
 * 
 * TODO SEE JSR AND SEE IF WE ARE MISSING ANNOTATIONS
 * 
 * TODO EXPLAIN VALIDATION RESULT
 * 
 * @author nilsth
 *
 */
public class ValidationModelFactory {

}
