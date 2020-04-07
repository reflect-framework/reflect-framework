package com.reflect.util.java.archive.file;

public class FileNameUtil {
	/**
	 * The extension separator character.
	 * 
	 * @since 1.4
	 */
	public static final char EXTENSION_SEPARATOR = '.';

	/**
	 * The extension separator String.
	 * 
	 * @since 1.4
	 */
	public static final String EXTENSION_SEPARATOR_STR = Character.toString(EXTENSION_SEPARATOR);

	/**
	 * The Unix separator character.
	 */
	private static final char UNIX_SEPARATOR = '/';

	/**
	 * The Windows separator character.
	 */
	private static final char WINDOWS_SEPARATOR = '\\';

	private static final int NOT_FOUND = -1;

	public static int indexOfExtension(final String filename) {
		if (filename == null) {
			return NOT_FOUND;
		}
		final int extensionPos = filename.lastIndexOf(EXTENSION_SEPARATOR);
		final int lastSeparator = indexOfLastSeparator(filename);
		return lastSeparator > extensionPos ? NOT_FOUND : extensionPos;
	}

	public static int indexOfLastSeparator(final String filename) {
		if (filename == null) {
			return NOT_FOUND;
		}
		final int lastUnixPos = filename.lastIndexOf(UNIX_SEPARATOR);
		final int lastWindowsPos = filename.lastIndexOf(WINDOWS_SEPARATOR);
		return Math.max(lastUnixPos, lastWindowsPos);
	}

	public static String getExtension(final String filename) {
		if (filename == null) {
			return null;
		}
		final int index = indexOfExtension(filename);
		if (index == NOT_FOUND) {
			return "";
		} else {
			return filename.substring(index + 1);
		}
	}

	public static String getWithoutExtention(final String filename) {
		if (filename == null) {
			return null;
		}
		final int index = indexOfExtension(filename);
		if (index == NOT_FOUND) {
			return filename;
		} else {
			return filename.substring(0, index);
		}
	}
}
