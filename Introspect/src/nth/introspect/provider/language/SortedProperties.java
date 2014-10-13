package nth.introspect.provider.language;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class SortedProperties extends Properties {
	private static final long serialVersionUID = -2574534921932813724L;

	@Override
	public synchronized Enumeration<Object> keys() {
		Set<Object> s=new TreeSet<Object>();//ordered
		for (Enumeration<Object> e=super.keys();e.hasMoreElements();) {
			s.add(e.nextElement());
		}
		final Iterator<Object> iter=s.iterator();
		return new Enumeration<Object>() {

			public boolean hasMoreElements() {
				return iter.hasNext();
			}

			public Object nextElement() {
				return iter.next();
			}
		};
	}
}
