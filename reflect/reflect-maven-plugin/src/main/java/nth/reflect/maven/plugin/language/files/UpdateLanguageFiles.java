package nth.reflect.maven.plugin.language.files;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.maven.plugin.language.files.file.LanguageFileService;

/**
 * 
 * The {@link UpdateLanguageFiles} is called from the command line to create or
 * update language files for an {@link ReflectApplication}
 * 
 * @author nilsth
 *
 */

//To create Maven plugin, see: https://www.youtube.com/watch?v=wHX4j0z-sUU
//
@Mojo(name = UpdateLanguageFiles.GOAL, defaultPhase = LifecyclePhase.COMPILE, requiresDependencyResolution = ResolutionScope.COMPILE)
public class UpdateLanguageFiles extends AbstractMojo {

	public final static String GOAL = "updateLanguageFiles";
	public final static String TITLE = GOAL;

	@Parameter(defaultValue = "${project}", required = true, readonly = true)
	private MavenProject mavenProject;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {

		getLog().info("Started...");

		try {

			LanguageFileService.update(mavenProject, getLog());

			getLog().info("Completed...");

		} catch (Exception e) {
			getLog().error(e);
			String message = "Failed...";
			throw new MojoExecutionException(message, e);
		}
	}
}
