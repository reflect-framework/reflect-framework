package nth.introspect.provider.path.id;

import java.net.URI;

public abstract class PathID implements CharSequence, Comparable<String>{

	private String identifierText=null;
	
	public PathID(String identifierText) {
		this.identifierText=this.getClass().getCanonicalName()+":"+identifierText;
	}
	
	@Override
	public int length() {
		return identifierText.length();
	}

	@Override
	public char charAt(int index) {
		return identifierText.charAt(index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		return identifierText.subSequence(start, end);
	}

	@Override
	public int compareTo(String anotherString) {
		return identifierText.compareTo(anotherString);
	}
	
	public abstract URI getPath(); 
	
}
