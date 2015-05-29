package nth.introspect.util;

import java.util.ArrayList;
import java.util.stream.IntStream;

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

		@Override
		public IntStream chars() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public IntStream codePoints() {
			// TODO Auto-generated method stub
			return null;
		}
		
		

	}

	
	private final ArrayList<Object> titleValues;
	private final String defaultSeperator;

	public TitleBuilder(String defaultSeperator) {
		this.defaultSeperator = defaultSeperator;
		titleValues = new ArrayList<Object>();
	}
	
	/**
	 * Creates a {@link TitleBuilder} with a space as seperator
	 */
	public TitleBuilder() {
		this(" ");
	}

	/**
	 * @return this object, to enable a fluent interface.
	 */
	public TitleBuilder append(Object value) {
		titleValues.add(value);
		return this;
	}

	/**
	 * @return this object, to enable a fluent interface.
	 */
	public TitleBuilder appendSeperator(String seperator) {
		titleValues.add(new Seperator(seperator));
		return this;
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
