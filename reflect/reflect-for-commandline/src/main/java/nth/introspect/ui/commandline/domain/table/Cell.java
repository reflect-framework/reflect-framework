package nth.introspect.ui.commandline.domain.table;

import java.util.ArrayList;
import java.util.List;

public class Cell {
	private static final int NO_BREAK_POINTS_FOUND = -1;
	private final String text;
	private final int width;

	public Cell(String text, int width) {
		super();
		this.text = text;
		this.width = width;
	}

	public List<String> getLines() {
		List<String> lines = new ArrayList<String>();
		String text = new String(this.text);
		boolean done = false;
		while (!done) {
			int breakPointPos = findBreakPointPos(text, width);
			if (breakPointPos == NO_BREAK_POINTS_FOUND) {
				String line = fillUpWithSpaces(text, width);
				lines.add(line);
				done = true;
			} else {
				String line = fillUpWithSpaces(text.substring(0, breakPointPos), width);
				lines.add(line);
				text = text.substring(breakPointPos);
			}
		}
		return lines;
	}

	private int findBreakPointPos(String text, int width) {
		if (text.length() < width) {
			return NO_BREAK_POINTS_FOUND;
		}
		for (int pos = width-1; pos > 0; pos--) {
			char ch = text.charAt(pos);
			if (' ' == ch || '-' == ch || '_' == ch || '=' == ch || '\\'== ch || '/'==ch) {
				return pos+1;
			}
		}
		return width;
	}

	private static String fillUpWithSpaces(String text, int width) {
		for (int i = text.length(); i < width; i++) {
			text += " ";
		}
		return text;
	}

	public String getEmptyLine() {
		return fillUpWithSpaces("", width);
	}
}
