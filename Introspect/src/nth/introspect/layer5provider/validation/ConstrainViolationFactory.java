package nth.introspect.layer5provider.validation;

import java.lang.annotation.ElementType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.Path;
import javax.validation.metadata.ConstraintDescriptor;

import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethod;
import nth.introspect.layer5provider.reflection.behavior.validation.ValidationMethodFactory;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;

import org.apache.bval.jsr303.util.PathImpl;

/**
 * Creates {@link ConstraintViolation}s from {@link ValidationMethod}s, for the
 * {@link DefaultValidationProvider}
 * 
 * @author nilsth
 *
 */
public class ConstrainViolationFactory {
	

	public static List<ConstraintViolation<Object>> create(
			ReflectionProvider reflectionProvider, Object domainObject) {

		ClassInfo classInfo = reflectionProvider.getClassInfo(domainObject
				.getClass());
		List<Method> validationMethods = classInfo.getAllValidationMethods();

		ArrayList<ConstraintViolation<Object>> constraintViolations = new ArrayList<ConstraintViolation<Object>>();
		for (Method validationMethod : validationMethods) {
			List<BasicConstrainViolation> basicConstrainViolations = executeValidationMethod(
					validationMethod, domainObject);
			if (basicConstrainViolations!=null) {
				constraintViolations.addAll(createConstraintViolations(classInfo, domainObject,basicConstrainViolations ));
			}
		}

		return constraintViolations;
	}

	private static List<ConstraintViolation<Object>> createConstraintViolations(
			ClassInfo classInfo, Object domainObject, List<BasicConstrainViolation> basicConstrainViolations) {
		List<ConstraintViolation<Object>> constraintViolations=new ArrayList<>();
		for (BasicConstrainViolation basicConstrainViolation : basicConstrainViolations) {
			constraintViolations.add(createConstraintViolations(
			classInfo, domainObject, basicConstrainViolation));
		}
		return constraintViolations;
	}

	private static ConstraintViolation<Object> createConstraintViolations(
			ClassInfo classInfo, Object domainObject,
			BasicConstrainViolation basicConstrainViolation) {
		LanguageProvider languageProvider;
		String messageTemplateKey=basicConstrainViolation.getMessageTemplateKey();
		String messageTemplateDefault=basicConstrainViolation.getMessageTemplateInEnglish();
		//TODO String messageTemplate=languageProvider.getText(messageTemplateKey,messageTemplateDefault);
		String messageTemplate=messageTemplateDefault;
		Object invalidValue=basicConstrainViolation.getInvalidValue();
		String message=String.format(messageTemplate, invalidValue);
		Path path=PathImpl.create(classInfo.getSimpleName());
		Class<Object> rootBeanClass = (Class<Object>) domainObject.getClass();
		ConstraintDescriptor<?> constraintDescriptor=null;
		ElementType elementType=null;
		ConstraintViolationImpl constraintViolation = new ConstraintViolationImpl<Object>(messageTemplate, message, domainObject, domainObject, path, domainObject, constraintDescriptor, rootBeanClass, elementType);
		return constraintViolation;
	}

	/**
	 * Class casting should be no problem because the {@link ValidationMethodFactory} should not return methods that do not return the correct type
	 * @param validationMethod
	 * @param domainObject
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static ConstraintViolations executeValidationMethod(
			Method validationMethod, Object domainObject) {
		try {
			Object[] NO_PARAMETERS = new Object[0];
			return  (ConstraintViolations) validationMethod.invoke(domainObject, NO_PARAMETERS);
		} catch (Exception e) {
			throw new ValidationMethodInvokenationException(validationMethod,e);
		}
	}
}
