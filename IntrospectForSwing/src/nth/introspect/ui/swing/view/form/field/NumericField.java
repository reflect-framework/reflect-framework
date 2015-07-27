package nth.introspect.ui.swing.view.form.field;

import java.text.Format;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

import nth.introspect.layer1userinterface.controller.Refreshable;
import nth.introspect.ui.swing.properygrid.SwingUtil;
import nth.introspect.ui.valuemodel.PropertyValueModel;

/**
 * @deprecated Use {@link NumericField2}. Remove this class when not needed
 * @author nilsth
 *
 */
public class NumericField extends JFormattedTextField implements Refreshable{

	private static final long serialVersionUID = 2005556164722532623L;

	private PropertyValueModel propertyValueModel;

	public NumericField(final PropertyValueModel propertyValueModel) {
		super(propertyValueModel.getPropertyInfo().getFormat());
		this.propertyValueModel = propertyValueModel;
		

//		Document document = RegExpDocumentFacory.create(propertyValueModel.getPropertyInfo().getPropertyType().getType());
//		setDocument(document);
		
		
//		Document document=createFormatDocument(propertyValueModel.getPropertyInfo().getFormat());
//		setDocument(document);
		
		getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getValue());
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				// text has changed, so update the valueModel
				if (propertyValueModel.canSetValue()) {
					propertyValueModel.setValue(getValue());
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});
		
	}


//	private Document createFormatDocument(final Format format) {
//	
//		return new PlainDocument(){
//
//			@Override
//			public void insertString(int offset, String str, AttributeSet a)
//					throws BadLocationException {
//				
//				String newText = getText(0,offset) + str + getText(offset,(getLength() - offset));
//				
//				Object value = null;
//				try {
//					value = format.parseObject(newText);
//				} catch (ParseException e) {
//				}
//				String formatedText = format.format(value);
//				
//				if (newText.equals(formatedText)) {
//					super.insertString(offset, str, a);
//				}
//			}
//			
//			
//		};
//	}


	@Override
	public void refresh() {
//		SwingUtilities.invokeLater(new Runnable() {
//			
//			@Override
//			public void run() {
//				setValue(propertyValueModel.getValue());
//				setEnabled(propertyValueModel.canSetValue());
//			}
//		});
		
	}
	

	



	

}
