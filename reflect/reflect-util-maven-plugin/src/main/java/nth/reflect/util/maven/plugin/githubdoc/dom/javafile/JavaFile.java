package nth.reflect.util.maven.plugin.githubdoc.dom.javafile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag.EndTag;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag.InlineTag;
import nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag.InlineTagFactory;
import nth.reflect.util.regex.Regex;
import nth.reflect.util.regex.Repetition;

/**
 * 
 * @author nilsth
 * 
 */

public class JavaFile {

	private static final String HREF = "href";
	public static final String FILE_EXTENSION = ".java";
	private static final Regex END_OF_COMMENT = new Regex().literal("*/");
	private static final Pattern PACKAGE_LINE = new Regex().beginOfLine().whiteSpace(Repetition.zeroOrMoreTimes())
			.literal("package").whiteSpace(Repetition.oneOrMoreTimes()).literals("a-zA-Z", Repetition.oneOrMoreTimes())
			.anyCharacter(Repetition.zeroOrMoreTimes()).literal(";").whiteSpace(Repetition.zeroOrMoreTimes())
			.toPattern();
	private static final Pattern IMPORT_LINE = new Regex().multiLineMode().beginOfLine()
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("import").whiteSpace(Repetition.oneOrMoreTimes())
			.literals("a-zA-Z", Repetition.oneOrMoreTimes()).anyCharacter(Repetition.zeroOrMoreTimes()).literal(";")
			.whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine().toPattern();
	private static final Pattern SINGLE_LINE_COMMENT = new Regex().multiLineMode().beginOfLine()
			.whiteSpace(Repetition.zeroOrMoreTimes()).literal("//").anyCharacter(Repetition.zeroOrMoreTimes())
			.endOfLine().toPattern();
	private static final Pattern STARTS_WITH_ASTRIX = new Regex().multiLineMode().beginOfLine()
			.whiteSpaceWithoutCrLf(Repetition.zeroOrMoreTimes()).literals("*").toPattern();
	private static final Pattern EMPTY_LINE = new Regex().multiLineMode().beginOfLine()
			.whiteSpace(Repetition.zeroOrMoreTimes()).endOfLine().toPattern();
	private static final String START_JAVADOC_COMMENTS = "/**";
	private static final String SRC = "src";

	private final File javaFile;
	private String contents;
	private final DocumentationFiles documentationFiles;
	private Document javaDocHtml;
	private String javaDocText;
	private final ReferenceName referenceName;

	public JavaFile(DocumentationFiles documentationFiles, File javaFile, ReferenceName referenceName) {
		this.documentationFiles = documentationFiles;
		this.javaFile = javaFile;
		this.referenceName = referenceName;
	}

	public DocumentationFiles getDocumentationFiles() {
		return documentationFiles;
	}

	private String readContent() throws IOException {
		if (contents == null) {
			byte[] encoded = Files.readAllBytes(javaFile.toPath());
			// Charset used by eclipse: Window -> Preferences -> General ->
			// Workspace : Text file encoding CP1252
			Charset DEFAULT_TEXT_ENCODING = Charset.forName("Cp1252");
			contents = new String(encoded, DEFAULT_TEXT_ENCODING);
		}
		return contents;
	}

	private String getJavaDocTextOfClassDescriptor() {
		if (javaDocText == null) {
			String contents;
			try {
				contents = readContent();
			} catch (IOException e) {
				throw new RuntimeException("Could not read javadoc of file:" + javaFile.getAbsolutePath());
			}
			RegexParser regexParser = new RegexParser(contents);
			regexParser.removeAll(PACKAGE_LINE);
			regexParser.removeAll(IMPORT_LINE);
			regexParser.removeAll(SINGLE_LINE_COMMENT);
			regexParser.removeAll(EMPTY_LINE);
			if (regexParser.startsWith("\r\n")) {
				// This line is needed because the previous line does not remove
				// the first crlf
				regexParser.removeFirst("\r\n");
			}
			if (regexParser.startsWith("\n")) {
				// This line is needed because the previous line does not remove
				// the first crlf
				regexParser.removeFirst("\n");
			}

			boolean foundJavaDocText = regexParser.startsWith(START_JAVADOC_COMMENTS);
			if (foundJavaDocText) {
				regexParser.removeFirst(START_JAVADOC_COMMENTS);
				regexParser.removeFrom(END_OF_COMMENT);
				regexParser.removeAll(STARTS_WITH_ASTRIX);

				String reference = createRefferenceElement().toString();
				regexParser.insert(reference, 0);

				removeEndTags(regexParser);

				javaDocText = regexParser.getResult();
			} else {
				javaDocText = "";
			}
		}
		return javaDocText;
	}

	public Document getJavaDocHtmlOfClassDescriptor() {
		if (javaDocHtml == null) {
			String javaDocText = getJavaDocTextOfClassDescriptor();
			try {
				javaDocText = replaceAllInlineTags(javaDocText, documentationFiles);
			} catch (Exception e) {
				throw new RuntimeException("Could not replace all inline tags for: " + javaFile, e);
			}
			javaDocHtml = Jsoup.parse(javaDocText, "UTF-8");
			updateImageSrc(javaDocHtml);
			updateAref(javaDocHtml);
			// TODO fix link referenceName

		}
		return javaDocHtml;
	}

	private void updateAref(Document javaDocHtml) {
		Elements aElements = javaDocHtml.select("a[href^=ReferenceName_]");
		for (Element aElement : aElements) {
			String href = aElement.attr(HREF);
			if (href.startsWith(ReferenceName.PREFIX)) {
				Optional<ReferenceName> result = documentationFiles
						.findJavaFileResourceName(referenceName.withFileName(href));
				if (result.isPresent()) {
					aElement.attributes().put(HREF, result.get().toString());
				}
			}
		}
	}

	private void updateImageSrc(Document javaDocHtml) {
		Elements imageElements = javaDocHtml.select("img[src]");
		for (Element imageElement : imageElements) {
			String src = imageElement.attr(SRC);
			if (!src.startsWith(ReferenceName.PREFIX)) {
				Optional<ReferenceName> result = documentationFiles
						.findResourceFileResourceName(referenceName.withFileName(src));
				if (result.isPresent()) {
					imageElement.attributes().put(SRC, result.get().toString());
				}
			}
		}
	}

	private String replaceAllInlineTags(String javaDoc, DocumentationFiles documentationFiles) throws IOException {

		List<InlineTag> inlineTags = InlineTagFactory.getAllInlineTags(documentationFiles, referenceName);

		for (InlineTag inlineTag : inlineTags) {
			boolean foundMatch = true;
			while (foundMatch) {
				foundMatch = false;
				Matcher matcher = inlineTag.getRegex().toMatcher(javaDoc);
				if (matcher.find()) {
					foundMatch = true;
					int start = matcher.start();
					int end = matcher.end();
					String tag = javaDoc.substring(start, end);
					String replacement = inlineTag.getReplacementText(tag);
					javaDoc = javaDoc.substring(0, start) + replacement + javaDoc.substring(end);
				}
			}
		}

		return javaDoc;

	}

	private Element createRefferenceElement() {
		return new Element(Tag.valueOf("a"), "").attr("id", referenceName.toString());
	}

	private void removeEndTags(RegexParser regexParser) {
		for (EndTag endTag : EndTag.values()) {
			regexParser.removeFrom(endTag.asRegex());
		}
	}

}
