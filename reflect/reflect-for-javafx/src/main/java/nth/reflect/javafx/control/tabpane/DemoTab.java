package nth.reflect.javafx.control.tabpane;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringPropertyBase;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import nth.introspect.layer1userinterface.item.Item;

public class DemoTab extends VBox implements RfxTab  {

	private final LocalDateTime creationDateTime;

	public DemoTab() {
		setPadding(new Insets(16));
		
		
		JFXTextField field1 = new JFXTextField();
		field1.setLabelFloat(true);
		field1.setPromptText("Given name");
		JFXTextField validationField = new JFXTextField();
		validationField.setPromptText("With Validation..");
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");
		//validator.setAwsomeIcon(new Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField.getValidators().add(validator);
		validationField.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal) validationField.validate();
		});
		getChildren().add(field1);
		
		
		JFXTextField field2 = new JFXTextField();
		field2.setLabelFloat(true);
		field2.setPromptText("Family name");
		JFXTextField validationField2 = new JFXTextField();
		validationField2.setPromptText("With Validation..");
		RequiredFieldValidator validator2 = new RequiredFieldValidator();
		validator2.setMessage("Input Required");
		//validator.setAwsomeIcon(new Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField2.getValidators().add(validator2);
		validationField2.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal) validationField2.validate();
		});
		getChildren().add(field2);
		
		JFXTextField field3 = new JFXTextField();
		field3.setLabelFloat(true);
		field3.setPromptText("E-mail adres");
		JFXTextField validationField3 = new JFXTextField();
		validationField3.setPromptText("With Validation..");
		RequiredFieldValidator validator3 = new RequiredFieldValidator();
		validator3.setMessage("Input Required");
		//validator.setAwsomeIcon(new Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField3.getValidators().add(validator3);
		validationField3.focusedProperty().addListener((o,oldVal,newVal)->{
		    if(!newVal) validationField3.validate();
		});
		getChildren().add(field3);
		
		creationDateTime=LocalDateTime.now();
//		Label label=new Label(creationDateTime.toString());
//		setCenter(label);
		
	}

	@Override
	public StringPropertyBase getNameProperty() {
		return new SimpleStringProperty("Tab"+creationDateTime.toString());
	}

	@Override
	public ObjectPropertyBase<List<Item>> getMenuItemsProperty() {
		List<Item> items=new ArrayList<>();
		return new SimpleObjectProperty<List<Item>>(items);
	}

	@Override
	public Node getContent() {
		return this;
	}
}
