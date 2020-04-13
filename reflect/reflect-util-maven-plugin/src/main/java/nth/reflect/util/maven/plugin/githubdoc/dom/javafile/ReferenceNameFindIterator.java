package nth.reflect.util.maven.plugin.githubdoc.dom.javafile;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * {@link ReferenceNameFindIterator} is used to find a file in the
 * {@link DocumentationFiles} class, using a (partial) {@link ReferenceName}. It
 * iterates trough the {@link ReferenceName}, first returning the full
 * {@link ReferenceName}, than returning the {@link ReferenceName} starting
 * after the first {@link ReferenceName#SEPARATOR}, than returning the
 * {@link ReferenceName} starting after the second
 * {@link ReferenceName#SEPARATOR}, etc. This we it will find the
 * {@link JavaFile} or resource file closest to the source (in case there are
 * multiple files with the same name in multiple folders)
 * 
 * @author nilsth
 *
 */
public class ReferenceNameFindIterator implements Iterator<String> {

	private String referenceName;

	public ReferenceNameFindIterator(ReferenceName referenceName) {
		this.referenceName = addSeperatorSoThatFirstTimeItReturnsTheWholeName(referenceName);
	}

	private String addSeperatorSoThatFirstTimeItReturnsTheWholeName(ReferenceName referenceName) {
		return ReferenceName.SEPARATOR + referenceName.toString();
	}

	@Override
	public boolean hasNext() {
		return referenceName.contains(ReferenceName.SEPARATOR);
	}

	@Override
	public String next() {
		if (hasNext()) {
			int separatorPos = referenceName.indexOf(ReferenceName.SEPARATOR);
			referenceName = referenceName.substring(separatorPos + ReferenceName.SEPARATOR.length());
			return "_" + referenceName;
		}
		throw new NoSuchElementException();
	}

}
