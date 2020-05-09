package nth.reflect.fw.stream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;

/**
 * Sends a file from the application to the user (e.g. opens a save or download
 * dialog for the user)
 * 
 * @author nilsth
 *
 */
public class DownloadStream {

	private final File file;
	private final InputStream inputStream;

	public DownloadStream(File file, ByteArrayOutputStream outputStream) {
		this.file = file;
		this.inputStream = new ByteArrayInputStream(outputStream.toByteArray());
	}

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
