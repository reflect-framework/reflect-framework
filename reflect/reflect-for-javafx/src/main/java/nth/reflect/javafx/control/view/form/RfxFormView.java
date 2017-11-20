package nth.reflect.javafx.control.view.form;

import java.net.URL;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer1userinterface.view.ViewContainer;
import nth.introspect.layer5provider.language.LanguageProvider;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.GraphicalUserinterfaceController;
import nth.introspect.ui.item.method.FormOkItem;
import nth.introspect.ui.item.tab.CancelItem;
import nth.introspect.ui.item.tab.CloseThisTabItem;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.reflect.javafx.control.button.RfxContentButton;

public class RfxFormView extends BorderPane implements FormView {

	private final UserInterfaceContainer userInterfaceContainer;
	private final Object methodOwner;
	private final ActionMethodInfo actionMethodInfo;
	private final Object methodParameterValue;
	private final Object domainObject;
	private final FormMode formMode;
	private final ReflectionProvider reflectionProvider;
	private BufferedDomainValueModel domainValueModel;

	public RfxFormView(UserInterfaceContainer userInterfaceContainer, Object methodOwner,
			ActionMethodInfo actionMethodInfo, Object methodParameterValue, Object domainObject,
			FormMode formMode) {
		this.userInterfaceContainer = userInterfaceContainer;
		this.reflectionProvider=userInterfaceContainer.get(ReflectionProvider.class);
		this.methodOwner = methodOwner;
		this.actionMethodInfo = actionMethodInfo;
		this.methodParameterValue = methodParameterValue;
		this.domainObject = domainObject;
		this.formMode = formMode;


		domainValueModel = new BufferedDomainValueModel(userInterfaceContainer, reflectionProvider,
				domainObject, formMode);

		RfxDomainPropertyPane domainPropertyPane = new RfxDomainPropertyPane(this);
		setCenter(domainPropertyPane);
		
		HBox bottomButtonPane=createBottomButtonBar();
		setBottom(bottomButtonPane);

	}

	//TODO create RfxBottomButtonBar class 
	private HBox createBottomButtonBar() {
//		HBox toolBar = new HBox();
//		toolBar.setMinHeight(RfxAppButtonBar.BAR_HEIGHT);//TODO create constant in RfxBottomButtonBar class
//		toolBar.setPadding(new Insets(1));
//		toolBar.setSpacing(16);
//		toolBar.setBackground(RfxColorFactory.createBackGround(MaterialColors.getSecondaryColorSet().getBackground()));
//		
//		HBox leftSpacer = new HBox();
//		HBox.setHgrow(leftSpacer, Priority.ALWAYS);
//		toolBar.getChildren().add(leftSpacer);
//		
//		switch (formMode) {
//		case READ_ONLY_MODE:
//			toolBar.getChildren().add(createCloseButton());
//			break;
//		case EDIT_MODE:
//			toolBar.getChildren().add(createOkButton());
//			toolBar.getChildren().add(createCancelButton());
//			break;
//		default:
//			break;
//		}
//
//		HBox rightSpacer = new HBox();
//		HBox.setHgrow(rightSpacer, Priority.ALWAYS);
//		toolBar.getChildren().add(rightSpacer);
//
//		
//		return toolBar;
		
		return new RfxFormBottomToolbar();
	}
	
	public RfxContentButton createCloseButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CloseThisTabItem closeItem = new CloseThisTabItem(languageProvider,
				viewContainer, this);
		return new RfxContentButton(closeItem);
	}

	public RfxContentButton createCancelButton() {
		@SuppressWarnings("rawtypes")
		GraphicalUserinterfaceController userInterfaceController = userInterfaceContainer
				.get(GraphicalUserinterfaceController.class);
		ViewContainer viewContainer = userInterfaceController
				.getViewContainer();
		LanguageProvider languageProvider = userInterfaceContainer
				.get(LanguageProvider.class);
		CancelItem cancelItem = new CancelItem(languageProvider, viewContainer,
				this);
		return new RfxContentButton(cancelItem);
	}

	public RfxContentButton createOkButton() {
		FormOkItem okItem = new FormOkItem(this, methodOwner, actionMethodInfo,
				domainValueModel);
		return new RfxContentButton(okItem);
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
	public BufferedDomainValueModel getDomainValueModel() {
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
	public UserInterfaceContainer getUserInterfaceContainer() {
		return userInterfaceContainer;
	}
}
