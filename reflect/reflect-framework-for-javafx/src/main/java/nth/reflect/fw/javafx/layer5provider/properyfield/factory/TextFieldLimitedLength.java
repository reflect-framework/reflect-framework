package nth.reflect.fw.javafx.layer5provider.properyfield.factory;

import java.util.Objects;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import nth.reflect.fw.gui.component.tab.form.valuemodel.PropertyValueModel;

public class TextFieldLimitedLength extends TextField {

	public TextFieldLimitedLength(PropertyValueModel propertyValueModel) {
		super(propertyValueModel);
		this.maxLength = new SimpleIntegerProperty(-1);
	}
	
    private final IntegerProperty maxLength;


    public IntegerProperty maxLengthProperty() {
        return this.maxLength;
    }

    public final Integer getMaxLength() {
        return this.maxLength.getValue();
    }

    public final void setMaxLength(Integer maxLength) {
        Objects.requireNonNull(maxLength, "Max length cannot be null, -1 for no limit");
        this.maxLength.setValue(maxLength);
    }

    @Override
    public void replaceText(int start, int end, String insertedText) {
        if (this.getMaxLength() <= 0) {
            // Default behavior, in case of no max length
            super.replaceText(start, end, insertedText);
        }
        else {
            // Get the text in the textfield, before the user enters something
            String currentText = this.getText() == null ? "" : this.getText();

            // Compute the text that should normally be in the textfield now
            String finalText = currentText.substring(0, start) + insertedText + currentText.substring(end);

            // If the max length is not excedeed
            int numberOfexceedingCharacters = finalText.length() - this.getMaxLength();
            if (numberOfexceedingCharacters <= 0) {
                // Normal behavior
                super.replaceText(start, end, insertedText);
            }
            else {
                // Otherwise, cut the the text that was going to be inserted
                String cutInsertedText = insertedText.substring(
                        0, 
                        insertedText.length() - numberOfexceedingCharacters
                );

                // And replace this text
                super.replaceText(start, end, cutInsertedText);
            }
        }
    }

}
