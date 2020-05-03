package nth.reflect.fw.stream;

import java.io.File;

/**
 * Receives a file from the user to the application (e.g. opens a file selection
 * dialog)
 * 
 * @author nilsth
 *
 */
public class UploadStream {

	private final String fileTypeDescription;
	private final String[] fileExtentionFilters;
	private File file;

	public UploadStream(String fileTypeDescription, String... fileExtentionFilters) {
		this.fileTypeDescription = fileTypeDescription;
		this.fileExtentionFilters = fileExtentionFilters;
	}

	public File getFile() {
		return file;
	}

//	public InputStream getInputStream() throws FileNotFoundException {
//		return new FileInputStream(file);
//	}

	public String getFileTypeDescription() {
		return fileTypeDescription;
	}

	// TODO rename to getFileExtentionFilter and fix FormatFactory for String[]
	public String[] fileExtentionFilters() {
		return fileExtentionFilters;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@Override
	public String toString() {
		if (file == null) {
			return fileTypeDescription;
		} else {
			return file.getPath();
		}
	}

}
