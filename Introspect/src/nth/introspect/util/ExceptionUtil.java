package nth.introspect.util;

import java.io.PrintWriter;
import java.io.StringWriter;


public class ExceptionUtil {
	public static Throwable getRootCause(Throwable throwable) {
		Throwable cause = throwable;
		while (cause.getCause()!=null) {
			cause=cause.getCause();
		}
		return cause;
	}
	
	public static String getStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		return sw.toString();
	}
	
	public static String getRootCauseStackTrace(Throwable throwable) {
		Throwable rootCause = getRootCause(throwable);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		rootCause.printStackTrace(pw);
		return sw.toString();
	}
}
