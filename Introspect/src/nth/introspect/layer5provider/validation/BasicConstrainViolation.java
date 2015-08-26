package nth.introspect.layer5provider.validation;

import javax.validation.ConstraintViolation;

import nth.introspect.layer5provider.reflection.ReflectionProvider;

/**
 * A validation method returns {@link ConstraintViolations}
 * containing messageKey and invalidValue. The ConstrainViolationFactory creates
 * {@link ConstraintViolation}s using the {@link BasicConstrainViolation}
 * and the meta information from the {@link ReflectionProvider}.
 * 
 * @author nilsth
 *
 */
public class BasicConstrainViolation {

	private final String messageTemplateInEnglish;
	private final Object invalidValue;

	public BasicConstrainViolation(String messageTemplateInEnglish, Object invalidValue) {
		this.messageTemplateInEnglish = messageTemplateInEnglish;
		this.invalidValue = invalidValue;
	}

	private String createKey(String messageTemplateInEnglish) {
		StringBuilder key=new StringBuilder();
		key.append("constraint.violation.");
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
