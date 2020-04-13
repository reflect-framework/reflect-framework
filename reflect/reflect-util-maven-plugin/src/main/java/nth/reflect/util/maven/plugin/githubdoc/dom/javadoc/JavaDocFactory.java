package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.GitHubDocumentationGoal;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.AttributeName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.ElementName;
import nth.reflect.util.maven.plugin.githubdoc.dom.html.JSoupQuery;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.JavaFile;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;

public class JavaDocFactory {

	public static Document getAllJavaDoc(GitHubDocumentationGoal goal, DocumentationFiles documentationFiles)
			throws IOException {

		goal.getLog().info("Parsing JavaDoc");

		ReferenceName referenceName = new ReferenceName(goal.getDocumentationRootClassName());
		Optional<JavaFile> result = documentationFiles.findJavaFile(referenceName);

		if (!result.isPresent()) {
			throw new RuntimeException(
					"Could not found: " + referenceName.toString() + " in folder:" + goal.getProjectRootFolder());
		}

		JavaFile javaFile = result.get();
		Document javaDoc = javaFile.getJavaDocHtmlOfClassDescriptor().clone();

		return javaDoc;
	}

	public static void updateAllReferences(Document javaDoc, ReferenceFactory referenceFactory) {
		updateHeaderIds(javaDoc, referenceFactory);
		updateInternalReferences(javaDoc, referenceFactory);
		updateImageSources(javaDoc, referenceFactory);
	}

	private static void updateImageSources(Document javaDoc, ReferenceFactory referenceFactory) {
		String query = new JSoupQuery()
				.addElement(ElementName.IMG, AttributeName.SRC + "^=" + ReferenceName.PREFIX)
				.toString();
		Elements imgElements = javaDoc.select(query);
		for (Element imgElement : imgElements) {
			String src = referenceFactory.createImageSrc(imgElement);
			imgElement.attr(AttributeName.SRC, src);
		}
	}

	private static Map<String, Element> findInternalReferencesAndChapterElements(Document javaDoc) {
		Map<String, Element> internalReferencesAndChapterElements = new HashMap<>();
		String query = new JSoupQuery()
				.addElement(ElementName.H1)
				.addElement(ElementName.H2)
				.addElement(ElementName.H3)
				.addElement(ElementName.A, AttributeName.ID + "^=" + ReferenceName.PREFIX)
				.toString();
		Elements elements = javaDoc.select(query);
		Element lastFoundHElement = null;
		for (Element element : elements) {
			if (element.tagName().startsWith("h")) {
				lastFoundHElement = element;
			} else if (element.tagName().equals(ElementName.A) && lastFoundHElement != null) {
				String id = element.attr(AttributeName.ID);
				internalReferencesAndChapterElements.put(id, lastFoundHElement);
			}
		}
		return internalReferencesAndChapterElements;
	}

	private static void updateInternalReferences(Document javaDoc, ReferenceFactory referenceFactory) {
		Map<String, Element> internalReferencesWithChapters = findInternalReferencesAndChapterElements(javaDoc);
		String query = new JSoupQuery()
				.addElement(ElementName.A, AttributeName.HREF + "^=" + ReferenceName.PREFIX)
				.toString();
		Elements aElements = javaDoc.select(query);
		Set<String> internalReferences = internalReferencesWithChapters.keySet();
		for (Element aElement : aElements) {
			String href = aElement.attr(AttributeName.HREF);
			if (internalReferences.contains(href)) {
				Element hElement = internalReferencesWithChapters.get(href);
				String hRef = referenceFactory.createHReference(hElement);
				aElement.attr(AttributeName.HREF, hRef);
			}
		}
	}

	/**
	 * Gives all H1, H2 and H3 elements and unique ID so that they can be used as a
	 * book mark (internal or external reference)
	 * 
	 * @param htmljavaDocHtml
	 */

	private static void updateHeaderIds(Document javaDoc, ReferenceFactory referenceFactory) {
		String query = new JSoupQuery()
				.addElement(ElementName.H1)
				.addElement(ElementName.H2)
				.addElement(ElementName.H3)
				.toString();
		Elements elements = javaDoc.select(query);
		for (Element hElement : elements) {
			String id = referenceFactory.createHId(hElement);
			hElement.attr(AttributeName.ID, id);
		}
	}

}
