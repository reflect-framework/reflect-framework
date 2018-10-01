package nth.reflect.fw.infrastructure.random.util;

import java.util.Collection;

public class StringUtil {

	public static int countMatches(String str, Collection<Character> charactersToFind) {
		int count = 0;
		for (Character character : str.toCharArray()) {
			if (charactersToFind.contains(character)) {
				count++;
			}
		}
		return count;
	}

	private static final int INDEX_NOT_FOUND = -1;

	public static int countMatches(final String str, final String sub) {
		if (isEmpty(str) || isEmpty(sub)) {
			return 0;
		}
		int count = 0;
		int idx = 0;
		while ((idx = indexOf(str, sub, idx)) != INDEX_NOT_FOUND) {
			count++;
			idx += sub.length();
		}
		return count;
	}

	public static boolean isEmpty(final CharSequence cs) {
		return cs == null || cs.length() == 0;
	}

	static int indexOf(final CharSequence cs, final CharSequence searchChar, final int start) {
		return cs.toString().indexOf(searchChar.toString(), start);
	}

	public static String capitalize(String result) {
		int strLen;
	    if (result == null || (strLen = result.length()) == 0) {
	        return result;
	    }
	    return new StringBuilder(strLen)
	        .append(Character.toTitleCase(result.charAt(0)))
	        .append(result.substring(1))
	        .toString();
	}
}
