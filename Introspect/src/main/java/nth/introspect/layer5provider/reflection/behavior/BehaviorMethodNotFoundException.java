package nth.introspect.layer5provider.reflection.behavior;


public class BehaviorMethodNotFoundException extends Exception {

	private static final long serialVersionUID = -8447500654942721817L;

	public BehaviorMethodNotFoundException(String behavioralMethodName) {
		super(createMessage(behavioralMethodName));
	}

	private static String createMessage(String behavioralMethodName) {
		StringBuilder message=new StringBuilder();
		message.append("Behavioral method: ");
		message.append(behavioralMethodName);
		message.append(" could not be found");
		return message.toString();
	}

	
}
