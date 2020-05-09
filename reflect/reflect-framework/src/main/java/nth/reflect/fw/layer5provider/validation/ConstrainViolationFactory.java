package nth.reflect.fw.layer5provider.validation;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import org.hibernate.validator.internal.engine.path.PathImpl;

import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethod;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethodFactory;
import nth.reflect.fw.layer5provider.reflection.info.classinfo.DomainClassInfo;

/**
 * Creates {@link ConstraintViolation}s from {@link ValidationMethod}s, for the
 * {@link DefaultValidationProvider}
 * 
 * @author nilsth
 *
 */
public class ConstrainViolationFactory {

	public static List<ConstraintViolation<Object>> create(ReflectionProvider reflectionProvider,
			LanguageProvider languageProvider, Object domainObject) {

		DomainClassInfo domainClassInfo = reflectionProvider.getDomainClassInfo(domainObject.getClass());
		List<Method> validationMethods = domainClassInfo.getAllValidationMethods();

		ArrayList<ConstraintViolation<Object>> constraintViolations = new ArrayList<ConstraintViolation<Object>>();
		for (Method validationMethod : validationMethods) {
			List<ValidationViolation> validationViolations = executeValidationMethod(validationMethod, domainObject);
			if (validationViolations != null) {
				constraintViolations.addAll(createConstraintViolations(languageProvider, domainClassInfo, domainObject,
						validationViolations));
			}
		}

		return constraintViolations;
	}

	private static List<ConstraintViolation<Object>> createConstraintViolations(LanguageProvider languageProvider,
			DomainClassInfo domainClassInfo, Object domainObject, List<ValidationViolation> validationViolations) {
		List<ConstraintViolation<Object>> constraintViolations = new ArrayList<>();
		for (ValidationViolation validationViolation : validationViolations) {
			constraintViolations.add(
					createConstraintViolations(languageProvider, domainClassInfo, domainObject, validationViolation));
		}
		return constraintViolations;
	}

	private static ConstraintViolation<Object> createConstraintViolations(LanguageProvider languageProvider,
			DomainClassInfo domainClassInfo, Object domainObject, ValidationViolation validationViolation) {
		String messageTemplateKey = validationViolation.getMessageTemplateKey();
		String messageTemplateDefault = validationViolation.getMessageTemplateInEnglish();
		String messageTemplate = languageProvider.getText(messageTemplateKey, messageTemplateDefault);
		Object invalidValue = validationViolation.getInvalidValue();
		String message = String.format(messageTemplate, invalidValue);
		Path path = PathImpl.createPathFromString(domainClassInfo.getSimpleName());
		@SuppressWarnings("unchecked")
		Class<Object> rootBeanClass = (Class<Object>) domainObject.getClass();
		ConstraintDescriptor<?> constraintDescriptor = null;
		ElementType elementType = null;
		ConstraintViolationImpl<Object> constraintViolation = new ConstraintViolationImpl<Object>(messageTemplate,
				message, domainObject, domainObject, path, domainObject, constraintDescriptor, rootBeanClass,
				elementType);
		return constraintViolation;
	}

	/**
	 * Class casting should be no problem because the
	 * {@link ValidationMethodFactory} should not return methods that do not
	 * return the correct type
	 * 
	 * @param validationMethod
	 * @param domainObject
	 * @return See {@link ValidationViolations}
	 */
	private static ValidationViolations executeValidationMethod(Method validationMethod, Object domainObject) {
		try {
			Object[] NO_PARAMETERS = new Object[0];
			return (ValidationViolations) validationMethod.invoke(domainObject, NO_PARAMETERS);
		} catch (Exception e) {
			throw new ValidationMethodInvokenationException(validationMethod, e);
		}
	}
}
