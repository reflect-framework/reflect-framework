package nth.reflect.util.english.plural.action;

public class AppendWith implements PluralAction {

	private String appendix;

	public AppendWith(String appendix) {
		this.appendix = appendix;
	}

	@Override
	public String makePlural(String source) {
		return source + appendix;
	}

}
