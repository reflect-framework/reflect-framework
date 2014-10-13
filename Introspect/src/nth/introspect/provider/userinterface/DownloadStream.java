package nth.introspect.provider.userinterface;

import java.io.File;
import java.io.InputStream;


public class DownloadStream  {

	private final File file;
	private final InputStream inputStream;

	public DownloadStream(File file, InputStream inputStream) {
		this.file = file;
		this.inputStream = inputStream;
	}

	public File getFile() {
		return file;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

}
