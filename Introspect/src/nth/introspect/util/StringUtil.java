package nth.introspect.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class StringUtil {

	public static String fill(String text, String string, int length) {
		if (text == null) {
			text = "";
		}
		if (text.length() > length) {
			return text.substring(0, length - 3).concat("...");
		} else {
			StringBuffer reply = new StringBuffer(text);
			while (reply.length() < length) {
				reply.append(string);
			}
			return reply.toString();
		}
	}

	public static String fill(String string, int length) {
		return fill(string, " ", length);
	}

	public static String repeat(String string, int length) {
		return fill("", string, length);
	}

	public static String getStackTrace(Throwable throwable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		throwable.printStackTrace(printWriter);
		return result.toString();
	}

	public static String getDescription(Object object) {
		StringBuffer description = new StringBuffer(object.getClass().getSimpleName());
		description.append("(");
		List<Field> fields = findFields(object.getClass());
		boolean firstField = true;
		for (Field field : fields) {
			if (!isConstant(field)) {
				field.setAccessible(true);
				if (!firstField) {
					description.append(", ");
				}
				description.append(field.getName());
				description.append("=");
				try {
					description.append(field.get(object).toString());
				} catch (Exception e) {
					description.append("?");
				}
				firstField = false;
			}
		}
		description.append(")");
		return description.toString();
	}

	/**
	 * Finds a private or public field by fieldname. <br>
	 * It will go trough all super classes unti the field is found.
	 */
	private static List<Field> findFields(Class<?> beanClass) {
		List<Field> fields = new ArrayList<Field>();
		for (Field field : beanClass.getDeclaredFields()) {
			if (!StringUtil.isEliphantCase(field.getName())) {
				// field is not a constant so add it to the list
				fields.add(field);
			}
		}
		// get declared fields of super class
		beanClass = beanClass.getSuperclass();
		if (beanClass != Object.class) {
			// get fields of super classes recursively
			fields.addAll(findFields(beanClass));
		}
		return fields;
	}

	private static boolean isConstant(Field field) {
		String name = field.getName();
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (!Character.isUpperCase(ch) && ch != '_') {
				return false;
			}
		}
		return true;
	}

	public static String convertToNormalCase(String camelCase) {
		StringBuffer reply = new StringBuffer();

		for (int i = 0; i < camelCase.length(); i++) {
			char ch = camelCase.charAt(i);
			if (i == 0) {
				reply.append(Character.toUpperCase(ch));
			} else {
				char previousChar = camelCase.charAt(i - 1);
				if (Character.isLowerCase(previousChar) && Character.isUpperCase(ch)) {
					reply.append(" ");
					boolean nextCharIsUpper = (i < camelCase.length()) ? false : Character.isUpperCase(camelCase.charAt(i + 1));
					if (nextCharIsUpper) {
						reply.append(ch);
					} else {
						reply.append(Character.toLowerCase(ch));
					}
				} else {
					reply.append(ch);
				}
			}
		}
		return reply.toString();
	}

	public static String convertToCamelCase(String text, boolean firstCharIsUpperCase) {
		StringBuffer reply = new StringBuffer();
		boolean nextCharUpperCase = firstCharIsUpperCase;
		boolean nextCharLowerCase = !firstCharIsUpperCase;
		for (byte charByte : text.getBytes()) {
			Character ch;
			if (nextCharLowerCase) {
				ch = Character.toLowerCase((char) charByte);
				nextCharLowerCase = false;
			} else if (nextCharUpperCase) {
				ch = Character.toUpperCase((char) charByte);
				nextCharUpperCase = false;
			} else {
				ch = new Character((char) charByte);
			}
			if (ch.equals(' ') || ch.equals('_')) {
				nextCharUpperCase = true;
			} else {
				reply.append(ch);
			}
		}
		return reply.toString();
	}

	public static boolean isEliphantCase(String text) {
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (!(Character.isUpperCase(ch) || ch == '_')) {
				return false;
			}
		}
		return true;
	}

	public static boolean isCamelCase(String text) {
		return !text.contains(" ");
	}

	public static String eliphantCaseToNormal(String eliphantCase) {
		if (eliphantCase.length() > 1) {
			String reply = eliphantCase.replace("_", " ");
			return reply.substring(0, 1).toUpperCase() + reply.substring(1).toLowerCase();
		}
		return eliphantCase;
	}

	public static String firstCharToUpperCase(String text) {
		if (text == null || text.length() == 0) {
			return text;
		} else {
			StringBuffer result = new StringBuffer(text.substring(0, 1).toUpperCase());
			if (text.length() > 1) {
				result.append(text.substring(1));
			}
			return result.toString();
		}
	}

	public static String firstCharToLowerCase(String text) {
		if (text == null || text.length() == 0) {
			return text;
		} else {
			StringBuffer result = new StringBuffer(text.substring(0, 1).toLowerCase());
			if (text.length() > 1) {
				result.append(text.substring(1));
			}
			return result.toString();
		}
	}

	public static boolean isEmpty(String text) {
		return text == null || text.trim().length()==0;
	}
	
	public static int countMatches(String str, String sub) {
        if (isEmpty(str) || isEmpty(sub)) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != -1) {
            count++;
            idx += sub.length();
        }
        return count;
    }

}
