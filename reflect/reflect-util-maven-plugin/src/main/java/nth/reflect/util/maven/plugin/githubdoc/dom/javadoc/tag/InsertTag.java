package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.DocumentationFiles;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.JavaFile;
import nth.reflect.util.maven.plugin.githubdoc.dom.javafile.ReferenceName;

public class InsertTag extends InlineTag {

	private final DocumentationFiles documentationFiles;
	private final ReferenceName relativeReferenceName;

	public InsertTag(DocumentationFiles documentationFiles, ReferenceName referenceName) {
		this.documentationFiles = documentationFiles;
		this.relativeReferenceName = referenceName;
	}

	@Override
	protected String getName() {
		return "insert";
	}

	@Override
	protected Element getElement(String nameToFind) {
		ReferenceName referenceName = getReferenceName(nameToFind);
		Optional<JavaFile> result = documentationFiles.findJavaFile(referenceName);
		if (!result.isPresent()) {
			throw new RuntimeException("Could not find a java file with name:" + nameToFind);
		}
		JavaFile javaFile = result.get();
		String javaDoc = javaFile.getJavaDocHtmlOfClassDescriptor().toString();
		return new Element(Tag.valueOf("p"), "").html(javaDoc);
	}

	private ReferenceName getReferenceName(String nameToFind) {
		if (includesPackages(nameToFind)) {
			return new ReferenceName(nameToFind.replace(".", "_"));
		} else {
			return relativeReferenceName.withFileName(nameToFind);
		}
	}

	private boolean includesPackages(String nameToFind) {
		return StringUtils.countMatches(nameToFind, ".") > 1;
	}

}
