package nth.reflect.fw.generic.util;


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
			String reply = eliphantCase.replace("_", " ").trim();
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

	public static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().length()==0;
	}
	
	public static int countMatches(String str, String sub) {
        if (isNullOrEmpty(str) || isNullOrEmpty(sub)) {
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

	public static boolean containsCharacters(String stringToSearch, String charactersToFind) {
		char[] characters = charactersToFind.toCharArray();
		for (char c : characters) {
			if (stringToSearch.contains(""+c)) {
				return true;
			}
		}
		return false;
	}

}
