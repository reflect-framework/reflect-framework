package nth.reflect.maven.plugin.language.files.texts;

import java.util.List;

import nth.reflect.fw.container.DependencyInjectionContainer;
import nth.reflect.fw.layer4infrastructure.InfrastructureContainer;
import nth.reflect.fw.layer5provider.ProviderContainer;

public class InfrasturctureTexts extends Texts {

	private static final long serialVersionUID = -525197466526876051L;

	public InfrasturctureTexts(DependencyInjectionContainer container) {
		InfrastructureContainer infrastructureContainer = container.get(InfrastructureContainer.class);
		List<Class<?>> infrastructureClasses = infrastructureContainer.getAllClasses();
		infrastructureClasses.remove(ProviderContainer.class);
		for (Class<?> infraStructureClass : infrastructureClasses) {
			putPropertiesFromTranslatableStringsFromStaticFields(infraStructureClass);
		}
	}

}
