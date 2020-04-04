package nth.reflect.fw.language.file;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.language.file.texts.AllTexts;

/**
 * 
 * The createOrUpdate goal of the {@link LanguageFile} is called from
 * the command line (in the LifecyclePhase.INITIALIZE) to create or update
 * language files for an {@link ReflectApplication}
 * 
 * @author nilsth
 *
 */

//To create Maven plugin, see: https://www.youtube.com/watch?v=wHX4j0z-sUU
//
@Mojo(name = "update")
public class LanguageFile extends AbstractMojo {

	private ReflectApplication getApplication() {
		// TODO find single class that extends ReflectApplication and instantiate it.
		return null;
	}

	private void updateLanguageFiles(AllTexts allTexts) {
		// TODO Auto-generated method stub
		// TODO add sections for commented deprecated texts (depending deprecatedText as
		// Maven parameter = enum REMOVE, STAY, COMMENT)
	}

	private void createLanguageFiles(AllTexts allTexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		getLog().info(LanguageFile.class.getSimpleName() + " has started...");

		ReflectApplication application = getApplication();

		AllTexts allTexts = new AllTexts(application);

		createLanguageFiles(allTexts);

		updateLanguageFiles(allTexts);

		getLog().info(LanguageFile.class.getSimpleName() + " has completed...");
	}
}
