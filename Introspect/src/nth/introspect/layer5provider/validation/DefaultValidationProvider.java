package nth.introspect.layer5provider.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import nth.introspect.layer5provider.reflection.ReflectionProvider;

public class DefaultValidationProvider implements ValidationProvider {

	private Validator validator;
	private final ReflectionProvider reflectionProvider;

	// TODO create Unit tetsts, See
	// http://docs.jboss.org/hibernate/validator/5.2/reference/en-US/html_single/#_validating_constraints
	// TODO add results from introspect validation methods (e.g. via
	// ConstraintViolationFactory.create(reflectionProvider.getClassInfo(obj.getClass()).getAllValidationMethods()).
	// A validation method returns a IntrospectConstraintViolation containing
	// messageKey and invalidValue (ConstraintViolation can be created from this
	// and info data from felectionProvider)

	public DefaultValidationProvider(ReflectionProvider reflectionProvider) {
		this.reflectionProvider = reflectionProvider;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Override
	public Set<ConstraintViolation<Object>> validate(Object domainObject) {
		Set<ConstraintViolation<Object>> constraintViolationsFromJSR303 = validator
				.validate(domainObject);
		List<ConstraintViolation<Object>> constraintViolationsFromValidationMethods = ConstrainViolationFactory
				.create(reflectionProvider, domainObject);
		HashSet<ConstraintViolation<Object>> allConstraints = new HashSet<>();
		allConstraints.addAll(constraintViolationsFromJSR303);
		allConstraints.addAll(constraintViolationsFromValidationMethods);
		return allConstraints;
	}
}
