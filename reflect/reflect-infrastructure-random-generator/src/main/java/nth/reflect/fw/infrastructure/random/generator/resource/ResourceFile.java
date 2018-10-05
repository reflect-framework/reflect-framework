package nth.reflect.fw.infrastructure.random.generator.resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ResourceFile {

	private final Class<?> ownerClass;

	public ResourceFile(Class<?> ownerClass) {
		this.ownerClass = ownerClass;
	}

	private String createFileName(Class<?> ownerClass) {
		StringBuilder fileName=new StringBuilder();
		fileName.append(ownerClass.getSimpleName());
		fileName.append(".txt");
		return fileName.toString();
	}


	public List<String> readLines() {
		String fileName = createFileName(ownerClass);
		InputStream inputStream = ownerClass.getResourceAsStream(fileName);
		List<String> lines=new ArrayList<>();
		if (fileName != null && !fileName.isEmpty() && inputStream != null) {
			lines=readLines(inputStream);
		}
		if (lines.size()==0) {
			throw new RuntimeException("Could not read resource file: "+ fileName);
		}
		return lines;
	}

	private List<String> readLines(InputStream inputStream) {
		List<String> lines = new ArrayList<>();
		if (inputStream != null) {
			Scanner scanner = new Scanner(inputStream, "UTF-8");
			scanner.useDelimiter(Pattern.compile("[\\r\\n]"));
			while (scanner.hasNext()) {
				String line = scanner.next();
				if (!line.isEmpty()) {
					lines.add(line);
				}
			}
			scanner.close();
		}
		return lines;
	}



}
