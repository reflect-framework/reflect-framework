package nth.reflect.util.maven.plugin.githubdoc.web.page;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import nth.reflect.util.maven.plugin.githubdoc.dom.page.WritableFile;

public class RobotsTxt implements WritableFile {

	private final File file;
	private static final String UTF_8_ENCODING = "UTF-8";
	private static final String DISALLOW_NOTHING = "Disallow:";
	private static final String ALL_USER_AGENTS = "UserTestObject-agent: *";
	private static final String ROBOTS_TXT = "robots.txt";
	
	public RobotsTxt(File destinationFolder) {
		file=new File(destinationFolder,ROBOTS_TXT);
	}
	
	@Override
	public void write() throws IOException {
		PrintWriter writer=new PrintWriter(file, UTF_8_ENCODING);
		writer.println(ALL_USER_AGENTS);
		writer.println(DISALLOW_NOTHING);
		writer.flush();
		writer.close();
	}


}
