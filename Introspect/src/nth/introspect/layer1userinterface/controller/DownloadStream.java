package nth.introspect.layer1userinterface.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;


public class DownloadStream  {

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
