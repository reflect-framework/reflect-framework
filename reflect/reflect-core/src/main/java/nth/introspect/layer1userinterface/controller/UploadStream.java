package nth.introspect.layer1userinterface.controller;

import java.io.File;
import java.io.InputStream;

public class UploadStream {

	
	private final File file;
	private final InputStream inputStream;
	
	public UploadStream(File file, InputStream inputStream) {
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
