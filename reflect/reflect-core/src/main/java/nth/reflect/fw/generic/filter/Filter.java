package nth.reflect.fw.generic.filter;

/**
 * @deprecated With Java 8 update comes streams and lambdas, solving this
 *             problem with a simple one-liner:
 * 
 *             <pre>
 *             List&lt;Person&gt; beerDrinkers = persons.stream().filter(p -&gt; p.getAge() &gt; 16).collect(Collectors.toList());
 *             </pre>
 *
 * @param <T>
 */

public interface Filter<T> {

	public boolean isMatch(T t);

}
