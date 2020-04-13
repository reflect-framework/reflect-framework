package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;

import org.jsoup.nodes.Document;

import nth.reflect.fw.generic.util.StringUtil;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.Page;

public abstract class WebPage extends Page {

	public WebPage(File destinationFolder, String javaDocClass, Document javaDoc) {
		super(destinationFolder, javaDocClass, javaDoc);
	}

	@Override
	protected String createFileName(String title) {
		StringBuilder fileName = new StringBuilder();
		fileName.append(StringUtil.convertToCamelCase(title, true));
		fileName.append(".html");
		return fileName.toString();
	}

}
