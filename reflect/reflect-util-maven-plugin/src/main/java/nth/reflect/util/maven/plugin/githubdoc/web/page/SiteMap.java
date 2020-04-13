package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import nth.reflect.util.maven.plugin.githubdoc.GitHubWebDocumentationInfo;
import nth.reflect.util.maven.plugin.githubdoc.dom.page.WritableFile;

public class SiteMap implements WritableFile {

	private static final String XML_ENCODING = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private static final String FILE_NAME = "sitemap.xml";
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String ELEMENT_URLSET_BEGIN = "<urlset xsi:schemaLocation=\"http://www.sitemaps.org/schemas/sitemap/0.9 http://www.sitemaps.org/schemas/sitemap/0.9/sitemap.xsd\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
	private static final String ELEMENT_URLSET_END = "</urlset>";
	private static final String ELEMENT_URL_BEGIN = "<url>";
	private static final String ELEMENT_URL_END = "</url>";
	private static final String ELEMENT_LOC_BEGIN = "<loc>";
	private static final String ELEMENT_LOC_END = "</loc>";
	private static final String ELEMENT_LASTMOD_BEGIN = "<lastmod>";
	private static final String ELEMENT_LASTMOD_END = "</lastmod>";

	private final GitHubWebDocumentationInfo info;
	private final File mainPage;

	public SiteMap(GitHubWebDocumentationInfo info, File mainPage) {
		this.info = info;
		this.mainPage = mainPage;
	}

	@Override
	public void write() throws IOException {
		File file = new File(info.getLocalGitHubWebRepository(), FILE_NAME);
		PrintWriter writer = new PrintWriter(file, UTF_8_ENCODING);
		writer.println(XML_ENCODING);
		writer.println(ELEMENT_URLSET_BEGIN);

		writer.println(ELEMENT_URL_BEGIN);

		writer.print(ELEMENT_LOC_BEGIN);
		writer.print("http://");
		writer.print(info.getGitHubUserName());
		writer.print(".github.io/");
		writer.print(mainPage.getName());
		writer.println(ELEMENT_LOC_END);

		writer.print(ELEMENT_LASTMOD_BEGIN);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+00:00'");
		String now = sdf.format(new Date());
		writer.print(now);
		writer.println(ELEMENT_LASTMOD_END);

		writer.println(ELEMENT_URL_END);

		writer.println(ELEMENT_URLSET_END);
		writer.flush();
		writer.close();

	}

}
