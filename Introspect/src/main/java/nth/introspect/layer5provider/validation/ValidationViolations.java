package nth.introspect.layer5provider.validation;

import java.util.ArrayList;

/**
 * A list of {@link ValidationViolation}'s
 * 
 * @author nilsth
 *
 */
public class ValidationViolations extends ArrayList<ValidationViolation> {

	private static final long serialVersionUID = 1L;

	public boolean add(String messageInEnglish, Object invalidValue) {
		ValidationViolation constrainViolation = new ValidationViolation(
				messageInEnglish, invalidValue);
		return add(constrainViolation);
	}
}
