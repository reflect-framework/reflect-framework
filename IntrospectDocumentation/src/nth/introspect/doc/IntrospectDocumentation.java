package nth.introspect.doc;

import nth.software.doc.generator.SoftwareDocumentationGenerator;

import nth.introspect.documentation.IntrospectFramework;

/**
 * <p>
 * The documentation of the {@link IntrospectFramework} is made with help of
 * JavaDoc and the {@link SoftwareDocumentationGenerator} project. This
 * {@link SoftwareDocumentationGenerator} will parse this JavaDoc and generate
 * web pages and wiki pages on the GitHub server. Please read the javaDoc of the
 * {@link SoftwareDocumentationGenerator} itself to see the advantages of this
 * approach.
 * </p>
 * <h3>Updating the Introspect Documentation</h3>
 * <p>
 * The documentation needs to be republished once the JavaDoc of the
 * {@link IntrospectFramework} has been updated. To do this, run the
 * {@link SoftwareDocumentationGenerator} application with the following
 * parameters:
 * <ul>
 * <li>createGitHubHtmlDocumentation "M:/My Git/Introspect-Framework"
 * "IntrospectDocumentation" "ntenhoeve" "GithuB66^" "M:\My
 * Git\ntenhoeve.github.io"</li>
 * <li>createGitHubWikiDocumentation "M:/My Git/Introspect-Framework"
 * "IntrospectDocumentation" "ntenhoeve" "GithuB66^" "M:\My
 * Git\Introspect-Framework.wiki"</li>
 * </ul>
 * Or run the {@link SoftwareDocumentationGenerator} with the
 * UpdateIntrospectDocumentation.commands file as parameter (located in this
 * package). Thise file does the same as the above.
 * </p>
 * <p>
 * The {@link SoftwareDocumentationGenerator} will then parse the JavaDoc of the
 * {@link IntrospectFramework} and generate web pages and Wiki pages into the
 * local GitHub repositories. These GitHub repositories will ten be committed
 * and pushed onto the GitHub server (effectively publishing the documentation).
 * </p>
 * <h3>Viewing the Introspect Documentation</h3>
 * <p>
 * The GitHubPageGenerator then commit and push these repositories to the GitHub
 * server, effectively updating the web site and the Wiki:
 * </p>
 * <ul>
 * <li>Web page: <a
 * href="http://ntenhoeve.github.io">http://ntenhoeve.github.io</a></li>
 * <li>Wiki: <a
 * href="https://github.com/ntenhoeve/Introspect-Framework/wiki">https
 * ://github.com/ntenhoeve/Introspect-Framework/wiki</a></li>
 * </ul>
 * </p>
 * 
 * @author nilsth
 *
 */
public interface IntrospectDocumentation {

}
