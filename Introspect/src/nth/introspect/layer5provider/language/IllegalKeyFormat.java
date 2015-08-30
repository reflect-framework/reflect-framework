package nth.introspect.layer5provider.language;

public class IllegalKeyFormat extends RuntimeException {

	private static final long serialVersionUID = -1564819678667966023L;

	public IllegalKeyFormat(String key) {
		super(createMessage(key));
	}

	private static String createMessage(String key) {
		StringBuilder message = new StringBuilder();
		message.append("Invalid key (contains other characters than letters, numbers or .: ");
		message.append(key);
		return message.toString();
	}
}
