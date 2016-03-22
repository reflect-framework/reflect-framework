package nth.introspect.junit.layer5provider.reflection.behavior.hidden;

import java.util.ArrayList;
import java.util.List;

import nth.introspect.layer5provider.reflection.behavior.hidden.Hidden;

public class HiddenModelForActionMethodTestObject {

	private static final String CORRECT_ROLE = "salesmanager";
	private static final String BOGUS_ROLE = "bogus";

	@Hidden()
	public void actionMethodHidden() {
	}

	@Hidden(exceptForRoleNames = BOGUS_ROLE)
	public void actionMethodHiddenNotInRole() {
	}

	@Hidden(exceptForRoleNames = CORRECT_ROLE)
	public void actionMethodVisibleInRole() {
	}

	public List<String> actionMethodCollection() {
		return new ArrayList<>();
	}

	public String actionMethodHiddenMethod() {
		return "actionMethodHiddenMethod";
	}

	public boolean actionMethodHiddenMethodHidden() {
		return true;
	}

	
	public void actionMethodVisibleMethod() {
	}

	public boolean actionMethodVisibleMethodHidden() {
		return false;
	}

	@Hidden()
	public void actionMethodHiddenAnnotationHiddenMethod() {
	}


	public boolean actionMethodHiddenAnnotationHiddenMethodHidden() {
		return true;
	}
	
	@Hidden()
	public void actionMethodHiddenVisibleMethod() {
	}


	public boolean actionMethodHiddenVisibleMethodHidden() {
		return false;
	}
	
	
}
