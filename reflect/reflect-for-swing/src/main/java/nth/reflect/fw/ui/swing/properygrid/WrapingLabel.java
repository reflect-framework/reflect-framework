package nth.reflect.fw.ui.swing.properygrid;

import javax.swing.JLabel;

/**
 *
 */
public class WrapingLabel extends JLabel{

	private static final String NEW_LINE = "\n";
	private static final long serialVersionUID = 1705856204590482862L;
	private static final String HTML_BEGIN = "<html>";
	private static final String HTML_END = "</html>";
	private static final String PARAGRAPH_BEGIN = "<p>";
	private static final String PARAGRAPH_END = "</p>";
	public static final String LINE_BREAK = "<br>";
	
	
	public WrapingLabel() {
	}
	
	public WrapingLabel(String text) {
		setText(text);
	}

	@Override
	public void setText(String text) {
		if (text!=null && text.trim().length()>0 && !text.toLowerCase().contains(HTML_BEGIN)) {
			StringBuffer html=new StringBuffer(HTML_BEGIN);
			html.append(PARAGRAPH_BEGIN);
			html.append(text.replace(NEW_LINE, LINE_BREAK));
			html.append(PARAGRAPH_END);
			html.append(HTML_END);
			text=html.toString();
		} 
		super.setText(text);
	}

}
