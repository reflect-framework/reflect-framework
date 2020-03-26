package nth.reflect.fw.layer5provider.reflection.info.classinfo;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer5provider.ProviderContainer;
import nth.reflect.fw.layer5provider.language.LanguageProvider;
import nth.reflect.fw.layer5provider.language.translatable.TranslatedString;
import nth.reflect.fw.layer5provider.reflection.ReflectionProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.description.TranslatedServiceClassDescription;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModel;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIconModelFactory;
import nth.reflect.fw.layer5provider.reflection.behavior.title.TitleModel;
import nth.reflect.fw.layer5provider.reflection.behavior.validation.ValidationMethodFactory;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfo;
import nth.reflect.fw.layer5provider.reflection.info.actionmethod.ActionMethodInfoFactory;

/**
 * Provides information on a {@link ServiceObject}.<br>
 * 
 * @author nilsth
 * 
 */
public class ServiceClassInfo extends ClassInfo {

	private final TitleModel titleModel;
	private final FontIconModel fontIconModel;
	private final List<Method> validationMethods;
	private final List<ActionMethodInfo> actionMethodInfosSorted;
	private final TranslatedString desciption;

	public ServiceClassInfo(ProviderContainer providerContainer, Class<?> serviceClass) {
		super(providerContainer, serviceClass);
		LanguageProvider languageProvider = providerContainer.get(LanguageProvider.class);
		ReflectionProvider reflectionProvider = providerContainer.get(ReflectionProvider.class);
		this.titleModel = new TitleModel(reflectionProvider);
		this.desciption = new TranslatedServiceClassDescription(languageProvider, serviceClass, this);
		this.fontIconModel = FontIconModelFactory.create(serviceClass);
		this.validationMethods = ValidationMethodFactory.create(serviceClass);
		this.actionMethodInfosSorted = ActionMethodInfoFactory.createSorted(providerContainer, serviceClass);
	}

	public URL getFontIconUrl(Object obj) {
		return fontIconModel.getFontIconUrl(obj);
	}

	public String getTitle(Object obj) {
		return titleModel.getTitle(obj);
	}

	public List<Method> getAllValidationMethods() {
		return validationMethods;
	}

	public List<ActionMethodInfo> getActionMethodInfosSorted() {
		return actionMethodInfosSorted;
	}

	public ActionMethodInfo getActionMethodInfo(String methodName) {
		for (ActionMethodInfo actionMethodInfo : actionMethodInfosSorted) {
			if (actionMethodInfo.getSimpleName().equals(methodName)) {
				return actionMethodInfo;
			}
		}
		return null;
	}

	public List<ActionMethodInfo> getActionMethodInfos(Predicate<ActionMethodInfo> filter) {
		List<ActionMethodInfo> filteredActionMethods = actionMethodInfosSorted.stream().filter(filter)
				.collect(Collectors.toList());
		return filteredActionMethods;
	}

	@Override
	public TranslatedString getDescription() {
		return desciption;
	}
}
