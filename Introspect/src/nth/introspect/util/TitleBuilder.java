package nth.introspect.util;

import java.util.ArrayList;

/**
 * This helper class can be used to create titles for domain objects (in the
 * toString method or <className>Title method) <br>
 * TODO consider if we should merge this class with {@link StringUtil} to
 * prevent confusion
 * 
 * @author nilsth
 * 
 */
public class TitleBuilder {

	private class Seperator implements CharSequence {

		private String seperator;

		public Seperator(String seperator) {
			this.seperator = seperator;
		}

		@Override
		public char charAt(int index) {
			return seperator.charAt(index);
		}

		@Override
		public int length() {
			return seperator.length();
		}

		@Override
		public CharSequence subSequence(int start, int end) {
			return seperator.subSequence(start, end);
		}

		@Override
		public String toString() {
			return seperator;
		}
		
		

	}

	
	private final ArrayList<Object> titleValues;
	private final String defaultSeperator;

	public TitleBuilder(String defaultSeperator) {
		this.defaultSeperator = defaultSeperator;
		titleValues = new ArrayList<Object>();
	}
	
	public TitleBuilder() {
		this(null);
	}

	public void append(Object value) {
		titleValues.add(value);
	}

	public void appendSeperator(String seperator) {
		titleValues.add(new Seperator(seperator));
	}

	public String getTitle() {
		StringBuilder stringBuilder = new StringBuilder();
		// add title values (in reverse order)
		String storedSeperator = null;
		for (int index = titleValues.size() - 1; index >= 0; index--) {
			Object titleValue = titleValues.get(index);
			if (titleValue != null) {
				String stringValue = titleValue.toString();
				if (stringValue.length() > 0) {
					boolean isSeperator = titleValue instanceof Seperator;
					if (isSeperator) {
						storedSeperator = stringValue;
					} else {
						if (storedSeperator != null
								&& stringBuilder.length() > 0) {
							stringBuilder.insert(0, storedSeperator);
						}
						stringBuilder.insert(0, stringValue);
						storedSeperator = defaultSeperator;
					}
				}

			}
		}
		return stringBuilder.toString();
	}

	@Override
	public String toString() {
		return getTitle();
	}

}
