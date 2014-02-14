package nth.introsepect.ui.swing.autocompletion;

import javax.swing.JTextField;
import javax.swing.text.Document;


public class AutoCompletionTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5952182982023718267L;

	public AutoCompletionTextField(CompletionService<?> nameService) {
		// Create the auto completing document model with a reference to the service and the input field.
        Document autoCompleteDocument = new AutoCompleteDocument(nameService,this);

        // Set the auto completing document as the document model on our input
        // field.
        setDocument(autoCompleteDocument);
	}
}
