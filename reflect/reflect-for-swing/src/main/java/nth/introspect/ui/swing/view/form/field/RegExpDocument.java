package nth.introspect.ui.swing.view.form.field;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class RegExpDocument extends PlainDocument {
	private static final int NO_LENGTH_LIMIT = -1;
	protected final Pattern pattern;
	private final int maxLength;

	
	public RegExpDocument(final String regex) {
		this(regex, NO_LENGTH_LIMIT);
	}

	
	/** * Contructor used to construct a document object which pattern matches * strings as typed. * * @param regex * pattern to match on typed strings * @param maxLength * maximum length of full string */
	public RegExpDocument(final String regex, final int maxLength) {
		this.maxLength = maxLength;
		this.pattern = Pattern.compile(regex);
	}

	@Override
	public void insertString(final int offset, final String str, final AttributeSet attr) throws BadLocationException {
		String newText = getText(0,offset) + str + getText(offset,(getLength() - offset));
		final Matcher matcher = pattern.matcher(newText);
		if (matcher.matches() && maxLength==NO_LENGTH_LIMIT || getLength()<=maxLength) {
			super.insertString(offset, str, attr);
		}
	}
}