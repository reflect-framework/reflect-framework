package nth.reflect.util.maven.plugin.language.files;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;

import nth.reflect.fw.ReflectApplication;
import nth.reflect.fw.layer2service.ServiceObject;
import nth.reflect.fw.layer3domain.DomainObject;
import nth.reflect.fw.layer5provider.language.LanguagePropertyFile;
import nth.reflect.util.maven.plugin.ReflectUtilMavenPlugin;
import nth.reflect.util.maven.plugin.language.files.file.LanguageFileService;

/**
 * 
 * The {@link ReflectUtilMavenPlugin}:{@link UpdateLanguageFiles} goal will:
 * <ul>
 * <li>Find all texts within a {@link ReflectApplication} that will need to be
 * translated</li>
 * <li>Adds key and text values for text that where not already translated to
 * the {@link LanguagePropertyFile}. New text values will start with the @(at)
 * symbol to indicate that the English default text (derived from the code or a
 * annotation) needs to be translated to another language.</li>
 * <li>Adds the "deprecated." prefix to the key's for deprecated text that where
 * where already translated but are no longer in use in the code.</li>
 * </ul>
 * <p>
 * A new {@link LanguagePropertyFile} is created by creating a new empty text
 * file (e.g. src/test/resources/language_de.properties for the German
 * language). New or empty {@link LanguagePropertyFile} or existing
 * {@link LanguagePropertyFile} can be updated by executing the
 * {@link UpdateLanguageFiles} goal when when changes to the
 * {@link ServiceObject}s and {@link DomainObject}s have been completed. The
 * files can then be translated.
 * <p>
 * You can execute the {@link UpdateLanguageFiles} goal after installing the
 * {@link ReflectUtilMavenPlugin}. The Maven goal can than be executed:
 * com.github.reflect-framework:reflect-util-maven-plugin:updateLanguageFiles
 *
 * 
 * @author nilsth
 *
 */

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
