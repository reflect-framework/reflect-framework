package nth.reflect.fw.generic.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import nth.reflect.fw.generic.exception.ReflectTranslatableException;
import nth.reflect.fw.layer5provider.language.LanguageProvider;


public class ExceptionUtil {
	public static Throwable getRootCause(Throwable throwable, LanguageProvider languageProvider) {
		Throwable cause = throwable;
		while (cause.getCause()!=null) {
			cause=cause.getCause();
			if (cause instanceof ReflectTranslatableException) {
				ReflectTranslatableException translatableCause=(ReflectTranslatableException) cause;
				translatableCause.setLanguageProvider(languageProvider);
			}
		}
		return cause;
	}
	
//	public static String getStackTrace(Throwable throwable) {
//		StringWriter sw = new StringWriter();
//		PrintWriter pw = new PrintWriter(sw);
//		throwable.printStackTrace(pw);
//		return sw.toString();
//	}
	
	public static String getRootCauseStackTrace( Throwable throwable, LanguageProvider languageProvider) {
		Throwable rootCause = getRootCause(throwable, languageProvider);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		rootCause.printStackTrace(pw);
		return sw.toString();
	}
}
