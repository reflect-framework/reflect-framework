package nth.reflect.fw.layer5provider.validation;

import javax.validation.ConstraintViolation;

import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethod;

/**
 * A {@link ValidationMethod} returns {@link ValidationViolations}
 * containing messageKey and invalidValue. The {@link ConstrainViolationFactory} creates
 * {@link ConstraintViolation}s using the {@link ValidationViolation}
 * and the meta information from the {@link ReflectionProvider}.
 * 
 * @author nilsth
 *
 */
public class ValidationViolation {

	private final String messageTemplateInEnglish;
	private final Object invalidValue;

	public ValidationViolation(String messageTemplateInEnglish, Object invalidValue) {
		this.messageTemplateInEnglish = messageTemplateInEnglish;
		this.invalidValue = invalidValue;
	}

	private String createKey(String messageTemplateInEnglish) {
		StringBuilder key=new StringBuilder();
		key.append("validation.violation.");
		key.append(messageTemplateInEnglish.replace(" ","_").toLowerCase());
		return key.toString();
	}

	public String getMessageTemplateKey() {
		return createKey(getMessageTemplateInEnglish());
	}

	public Object getInvalidValue() {
		return invalidValue;
	}

	public String getMessageTemplateInEnglish() {
		return messageTemplateInEnglish;
	}

}
