package nth.introspect.excel.repository.exception;

public class ConversionError extends RuntimeException {

	private static final long serialVersionUID = 5325897750871580412L;

	public ConversionError(String message) {
		super(message);
	}

	public ConversionError(String message, Exception exception) {
		super(message, exception);
	}

}
