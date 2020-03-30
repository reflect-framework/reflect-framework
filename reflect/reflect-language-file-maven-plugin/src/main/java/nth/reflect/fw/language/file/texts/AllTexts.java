package nth.reflect.fw.language.file.texts;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.ReflectFramework;
import nth.reflect.fw.container.DependencyInjectionContainer;

public class AllTexts {
	private final Texts reflectApplicationTexts;
	private final Texts serviceTexts;
	private final Texts domainTexts;
	private final Texts infrasturctureTexts;
	private final Texts reflectFrameWorkTexts;

	public AllTexts(ReflectApplication application) {
		DependencyInjectionContainer container = ReflectFramework.launch(application);

		reflectApplicationTexts = new ApplicationTexts(container);
		serviceTexts = new ServiceTexts(container);
		domainTexts = new DomainTexts(container);
		infrasturctureTexts = new InfrasturctureTexts(container);
		reflectFrameWorkTexts = new ReflectFrameworkTexts();

		checkForDoubleKeys();
	}

	private void checkForDoubleKeys() {
		new UniqueKeyTest(this);
	}

	public Texts getReflectApplicationTexts() {
		return reflectApplicationTexts;
	}

	public Texts getServiceTexts() {
		return serviceTexts;
	}

	public Texts getDomainTexts() {
		return domainTexts;
	}

	public Texts getInfrasturctureTexts() {
		return infrasturctureTexts;
	}

	public Texts getReflectFrameWorkTexts() {
		return reflectFrameWorkTexts;
	}

}
