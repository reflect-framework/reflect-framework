package nth.reflect.util.maven.plugin.githubdoc.dom.javadoc.tag;

import nth.reflect.util.regex.Regex;


public enum EndTag {
	AUTHOR ,VERSION ,PARAM ,RETURN ,DEPRECATED ,SINCE ,THROWS ,EXCEPTION ,SEE ,SERIAL ,SERIALFIELD ,SERIALDATA;
	
	public Regex asRegex() {
		String name="@"+this.name();
		Regex regex=new Regex().ignoreCase().literal(name);
		return regex;
	}
	
	
}
