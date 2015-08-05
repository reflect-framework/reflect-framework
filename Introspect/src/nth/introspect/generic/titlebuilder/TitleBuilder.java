package nth.introspect.generic.titlebuilder;

import java.util.ArrayList;

import nth.introspect.layer3domain.DomainObject;

/**
 * <p>
 * Often a {@link DomainObject} can be identified by a single property. The
 * toString() method can than simply return this property value to return the
 * title.
 * </p>
 * <p>
 * In other cases a DomainObject is identified by multiple property values. In
 * this case it is recommended to use the StringBuilder (see the example above).
 * The TitleBuilder is an extension of the StringBuilder, but has some
 * additional functionality such as:
 * <ul>
 * <li>The “append” methods will append a separator and a given value. You can
 * use the default separator (a space) or use an “append” method where you give
 * the separator as a first parameter followed by the value.</li>
 * <li>The “contact” methods will append a given value without a separator</li>
 * <li>Both “append” and “contact” methods will ignore null values or reference
 * objects that do not have a toString implementation</li>
 * <li>Both “append” and “contact” methods have methods where you can specify
 * format values (e.g. date, time and numbers)</li>
 * </ul>
 * </p>
 * 
 * TODO example
 * 
 * TODO implement naked object style
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
