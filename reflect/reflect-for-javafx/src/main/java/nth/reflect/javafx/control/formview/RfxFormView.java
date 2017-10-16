package nth.reflect.javafx.control.tabpane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.property.ObjectPropertyBase;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.generic.valuemodel.ReadOnlyValueModel;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.item.Item;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.layer5provider.reflection.info.classinfo.ClassInfo;
import nth.introspect.layer5provider.reflection.info.property.PropertyInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.item.method.FormOkItem;
import nth.introspect.ui.item.tab.CancelItem;
import nth.introspect.ui.item.tab.CloseThisTabItem;
import nth.introspect.ui.style.MaterialColorSet;
import nth.introspect.ui.style.MaterialColors;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.reflect.javafx.RfxView;
import nth.reflect.javafx.control.button.RfxButton;
import nth.reflect.javafx.control.style.RfxColorFactory;

public class RfxFormView extends BorderPane implements RfxView, FormView {

	private final UserInterfaceContainer userInterfaceContainer;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Object domainObject;
	private final FormMode formMode;
	private ReflectionProvider reflectionProvider;
	private BufferedDomainValueModel domainValueModel;

	public RfxFormView(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue, Object domainObject,
			FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;

		reflectionProvider = userInterfaceContainer.get(ReflectionProvider.class);
		ClassInfo classInfo = reflectionProvider.getClassInfo(domainObject.getClass());
		List<PropertyInfo> propertyInfos = classInfo.getPropertyInfosSorted();

		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, reflectionProvider,
				domainObject, formMode);

		// PropertyGrid propertyGrid = new PropertyGrid();
		// add(propertyGrid, BorderLayout.CENTER);
		// Component fieldToGetFocus = null;
		// for (PropertyInfo propertyInfo : propertyInfos) {
		// PropertyRow propertyRow = new PropertyRow(this, reflectionProvider,
		// domainValueModel, propertyInfo, formMode);
		// if (fieldToGetFocus == null && FormMode.EDIT_MODE == formMode
		// && propertyInfo.isEnabled(domainObject)) {
		// // XXX also check for field visibility?
		// fieldToGetFocus = propertyRow.getField();
		// }
		// propertyGrid.addPropertyRow(propertyRow);
		// }
		//
		// add(createButtonBar(), BorderLayout.SOUTH);


		GridPane gridPane = createPropertyGrid();
		setCenter(gridPane);
		
		HBox bottomButtonPane=createBottomButtonBar();
		setBottom(bottomButtonPane);
		

	}

	private HBox createBottomButtonBar() {
		HBox toolBar = new HBox();
		toolBar.setMinHeight(RfxTabBarPane.BAR_HEIGHT);
		toolBar.setPadding(new Insets(1));
		toolBar.setSpacing(16);
		toolBar.setBackground(RfxColorFactory.createBackGround(MaterialColors.getSecondaryColorSet().getBackground()));
		
		HBox leftSpacer = new HBox();
		HBox.setHgrow(leftSpacer, Priority.ALWAYS);
		toolBar.getChildren().add(leftSpacer);
		
		switch (formMode) {
		case READ_ONLY_MODE:
			toolBar.getChildren().add(createCloseButton());
			break;
		case EDIT_MODE:
			toolBar.getChildren().add(createOkButton());
			toolBar.getChildren().add(createCancelButton());
			break;
		default:
			break;
		}

		HBox rightSpacer = new HBox();
		HBox.setHgrow(rightSpacer, Priority.ALWAYS);
		toolBar.getChildren().add(rightSpacer);

		
		return toolBar;
	}
	
	public RfxButton createCloseButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CloseThisTabItem closeItem = new CloseThisTabItem(languageProvider,
				viewContainer, this);
		return new RfxButton(closeItem, MaterialColors.getSecondaryColorSet());
	}

	public RfxButton createCancelButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, viewContainer,
				this);
		return new RfxButton(cancelItem, MaterialColors.getSecondaryColorSet());
	}

	public RfxButton createOkButton() {
		FormOkItem okItem = new FormOkItem(this, methodOwner, actionMethodInfo,
				domainValueModel);
		return new RfxButton(okItem, MaterialColors.getSecondaryColorSet());
	}


	private GridPane createPropertyGrid() {
		GridPane propertyGrid = new GridPane();
		propertyGrid.setHgap(16);
		propertyGrid.setVgap(16);
		propertyGrid.setPadding(new Insets(16));		

		RfxTextField field1 = new RfxTextField();
		field1.setLabelFloat(true);
		field1.setPromptText("Given name");
		RfxTextField validationField = new RfxTextField();
		validationField.setPromptText("With Validation..");
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Input Required");
		// validator.setAwsomeIcon(new
		// Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField.getValidators().add(validator);
		validationField.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				validationField.validate();
		});
		propertyGrid.add(field1, 1, 0);

		RfxTextField field2 = new RfxTextField();
		field2.setLabelFloat(true);
		field2.setPromptText("Family name");
		RfxTextField validationField2 = new RfxTextField();
		validationField2.setPromptText("With Validation..");
		RequiredFieldValidator validator2 = new RequiredFieldValidator();
		validator2.setMessage("Input Required");
		// validator.setAwsomeIcon(new
		// Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField2.getValidators().add(validator2);
		validationField2.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				validationField2.validate();
		});
		propertyGrid.add(field2, 1, 1);

		RfxTextField field3 = new RfxTextField();
		field3.setLabelFloat(true);
		field3.setPromptText("E-mail adres");
		RfxTextField validationField3 = new RfxTextField();
		validationField3.setPromptText("With Validation..");
		RequiredFieldValidator validator3 = new RequiredFieldValidator();
		validator3.setMessage("Input Required");
		// validator.setAwsomeIcon(new
		// Icon(AwesomeIcon.WARNING,"2em",";","error"));
		validationField3.getValidators().add(validator3);
		validationField3.focusedProperty().addListener((o, oldVal, newVal) -> {
			if (!newVal)
				validationField3.validate();
		});
		propertyGrid.add(field3, 1, 2);
		
		return propertyGrid;
	}


	@Override
	public ObjectPropertyBase<List<Item>> getMenuItemsProperty() {
		List<Item> items = new ArrayList<>();
		return new SimpleObjectProperty<List<Item>>(items);
	}

	@Override
	public Node getContent() {
		return this;
	}

	@Override
	public String getViewTitle() {
		 return TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
				domainValueModel.getValue(), false);
	}

	@Override
	public URL getViewIconURL() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onViewActivate() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getViewDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getMethodOwner() {
		return methodOwner;
	}

	@Override
	public ActionMethodInfo getMethodInfo() {
		return actionMethodInfo;
	}

	@Override
	public Object getMethodParameter() {
		return methodParameterValue;
	}

	@Override
	public ReadOnlyValueModel getDomainValueModel() {
		return domainValueModel;
	}

	@Override
	public FormMode getFormMode() {
		return formMode;
	}

	@Override
	public Object getDomainObject() {
		return domainObject;
	}

	@Override
	public UserInterfaceContainer getuserInterfaceContainer() {
		return userInterfaceContainer;
	}
}
