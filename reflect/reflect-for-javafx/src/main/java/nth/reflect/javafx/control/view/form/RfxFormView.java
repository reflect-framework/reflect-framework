package nth.reflect.javafx.control.view.form;

import java.net.URL;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import nth.introspect.generic.util.TitleUtil;
import nth.introspect.layer1userinterface.UserInterfaceContainer;
import nth.introspect.layer5provider.reflection.ReflectionProvider;
import nth.introspect.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.introspect.ui.valuemodel.BufferedDomainValueModel;
import nth.introspect.ui.view.FormMode;
import nth.introspect.ui.view.FormView;
import nth.reflect.javafx.control.verticalflingscroller.RfxVerticalFlingScroller;

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
//		setCenter(domainPropertyPane);
		ScrollPane scrollPane=new ScrollPane(domainPropertyPane);
		//TODO new RfxVerticalFlingScroller(scrollPane);
		setCenter(scrollPane);
		
//		VBox vbox=new VBox();
//		vbox.setPrefHeight(javafx.scene.control.Control.USE_COMPUTED_SIZE);
//		vbox.setMaxHeight(Double.POSITIVE_INFINITY);
//		vbox.getChildren().add(scrollPane);
		
		HBox bottomButtonPane=new RfxFormBottomToolBar(this);
		setBottom(bottomButtonPane);

	}

	@Override
	public String getViewTitle() {
		 return TitleUtil.createTitle(reflectionProvider, actionMethodInfo,
				domainValueModel.getValue());
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
