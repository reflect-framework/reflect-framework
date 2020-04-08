package nth.reflect.util.maven.plugin.language.files.texts;

import nth.reflect.fw.container.DependencyInjectionContainer;

public class AllTexts extends Texts {

	private static final long serialVersionUID = 3356006624414126386L;
	private final Texts reflectApplicationTexts;
	private final Texts serviceTexts;
	private final Texts domainTexts;
	private final Texts infrasturctureTexts;
	private final Texts reflectFrameWorkTexts;

	public AllTexts(DependencyInjectionContainer container) {

		reflectApplicationTexts = new ApplicationTexts(container);
		putAll(reflectApplicationTexts);

		serviceTexts = new ServiceTexts(container);
		putAll(serviceTexts);

		domainTexts = new DomainTexts(container);
		putAll(domainTexts);

		infrasturctureTexts = new InfrasturctureTexts(container);
		putAll(infrasturctureTexts);

		reflectFrameWorkTexts = new ReflectFrameworkTexts();
		putAll(reflectFrameWorkTexts);

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

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("ReflectApplicationTexts\n");
		result.append(getReflectApplicationTexts());
		result.append("ServiceTexts\n");
		result.append(getServiceTexts());
		result.append("DomainTexts\n");
		result.append(getDomainTexts());
		result.append("InfrasturctureTexts\n");
		result.append(getInfrasturctureTexts());
		result.append("ReflectFrameWorkTexts\n");
		result.append(getReflectFrameWorkTexts());
		return result.toString();
	}

}
