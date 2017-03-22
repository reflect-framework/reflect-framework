package nth.reflect.example.domain2.account;

import nth.introspect.generic.filter.Filter;
import nth.reflect.example.domain2.tag.Tag;

public class AccountUserFilter implements Filter<Account> {

	private Tag userToFind;

	public AccountUserFilter(Tag userToFind) {
		this.userToFind = userToFind;
	}
	
	@Override
	public boolean isMatch(Account account) {
		return userToFind==account.getUser();
	}

}
