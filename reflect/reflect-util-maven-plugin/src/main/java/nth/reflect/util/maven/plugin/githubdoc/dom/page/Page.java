package nth.reflect.util.maven.plugin.githubdoc.dom.page;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import nth.reflect.fw.generic.util.StringUtil;

public abstract class Page implements WritableFile {

	private File file;
	private Document contents;
	private final File destinationFolder;
	private final String javaDocClass;
	private final Document javaDoc;

	public Page(File destinationFolder, String javaDocClass, Document javaDoc) {
		this.destinationFolder = destinationFolder;
		this.javaDocClass = javaDocClass;
		this.javaDoc = javaDoc;
	}

	public String getTitle() {
		Element titleElement = javaDoc.select("MaterialAppBarTitle").first();
		if (titleElement != null) {
			return titleElement.html();
		}
		String title = StringUtil.convertToNormalCase(javaDocClass);
		return title;
	}

	protected File createFile(String title) {
		StringBuilder filePath = new StringBuilder();
		filePath.append(getDestinationFolder());
		filePath.append("/");
		filePath.append(createFileName(title));
		File file = new File(filePath.toString());
		return file;
	}

	protected abstract String createFileName(String title);

	/*
	 * (non-Javadoc)
	 * 
	 * @see nth.reflect.app.swdocgen.dom.page.WritableFile#write()
	 */
	@Override
	public void write() throws IOException {
		FileOutputStream fos = new FileOutputStream(getFile());
		OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
		osw.write(getContents().toString());
		osw.flush();
		osw.close();
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("---------------------------------------------------------------\n");
		string.append("file:");
		string.append(getFile().getAbsolutePath());
		string.append("\n");
		string.append(getContents());
		return string.toString();
	}

	public File getFile() {
		if (file == null) {
			String title = getTitle();
			file = createFile(title);
		}
		return file;
	}

	public File getDestinationFolder() {
		return destinationFolder;
	}

	public Document getContents() {
		if (contents == null) {
			contents = createContents();
		}
		return contents;
	}

	public abstract Document createContents();

	public String getJavaDocClass() {
		return javaDocClass;
	}

	public Document getJavaDoc() {
		return javaDoc.clone();
	}

}
