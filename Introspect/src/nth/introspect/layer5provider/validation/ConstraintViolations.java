package nth.introspect.layer5provider.validation;

import java.util.ArrayList;

public class ConstraintViolations extends
		ArrayList<BasicConstrainViolation> {

	private static final long serialVersionUID = 1L;

	
	public boolean add(String messageInEnglish, Object invalidValue) {
		BasicConstrainViolation constrainViolation=new BasicConstrainViolation(messageInEnglish, invalidValue);
		return add(constrainViolation);
	}
}
