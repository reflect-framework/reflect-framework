package nth.introspect.layer5provider.reflection.behavior.validation;

import nth.introspect.layer3domain.DomainObject;
import nth.introspect.layer3domain.DomainObjectProperty;
import nth.introspect.layer5provider.reflection.behavior.BehavioralMethod;
import nth.introspect.layer5provider.validation.ConstraintViolations;

/**
 * <p>
 * {@link DomainObject}s and {@link DomainObjectProperty}s can also be validated with {@link ValidationMethod}s
 * located in the {@link DomainObject} so that you can do more complicated validation
 * using code.
 * </p>
 * <p>
 * ValidationMethods conventions:
 * <ul>
 * <li>Syntax: public {@link ConstraintViolations} &lt;{@link DomainObjectProperty}Name&gt;&lt;constraint name&gt;Validation.<br>
 * E.g. for a {@link DomainObjectProperty} startDate: public {@link ConstraintViolations}
 * startDateAfterTodayValidation()<br>
 * E.g. for business rule custommerUnique: public {@link ConstraintViolations}
 * customerUniqueValidation()</li>
 * <li>Method may not have a method parameter</li>
 * <li>Method must return a BasicConstrainViolation</li>
 * <li>Method must be public</li>
 * <li>Method may not be static</li>
 * <li>May not change the state of the {@link DomainObject}</li>
 * </ul>
 * 
 * TODO code example
 * 
 * TODO EXPLAIN {@link ConstraintViolations}
 * </p>
 */
public class ValidationMethod extends BehavioralMethod {

	@Override
	public String getBehavioralName() {
		return "Validation";
	}

	@Override
	public Class<?> getReturnType() {
		return ConstraintViolations.class;
	}

}
