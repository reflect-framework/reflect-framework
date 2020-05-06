package nth.reflect.account.vault.domain.account;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import nth.reflect.account.vault.domain.tag.Tag;
import nth.reflect.fw.gui.style.fontawesome.FontAwesomeUrl;
import nth.reflect.fw.layer5provider.notification.NotificationProvider;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionMode;
import nth.reflect.fw.layer5provider.reflection.behavior.executionmode.ExecutionModeType;
import nth.reflect.fw.layer5provider.reflection.behavior.fonticon.FontIcon;

@FontIcon(fontIconUrl=FontAwesomeUrl.USERS )
public class AccountService {

	private final AccountRepository accountRepository;
	private final NotificationProvider notificationProvider;

	public AccountService(AccountRepository accountRepository, NotificationProvider notificationProvider) {
		this.accountRepository = accountRepository;
		this.notificationProvider = notificationProvider;
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public List<Account> allAccountsOfTag(Tag tagToFind) throws Exception {
		List<Account> filteredAccounts = allAccounts().stream().filter(a -> a.getTags().contains(tagToFind)).collect(Collectors.toList());
		return filteredAccounts;
	}

	@SuppressWarnings("unchecked")
	public List<Account> allAccounts() throws Exception {
		return (List<Account>) accountRepository.getAll(Account.class);
	}

	public void createAccount(Account account) throws Exception {
		accountRepository.persist(account);
	}

	public Account createAccountParameterFactory() {
		Account account = new Account();
		return account;
	}

	public void modifyAccount(Account account) throws Exception {
		accountRepository.persist(account);
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_AFTER_CONFORMATION)
	public void deleteAccount(Account account) throws Exception {
		accountRepository.delete(account);
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public Account viewAccount(Account account) throws Exception {
		return account;
	}

	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveAccountUp(Account account) throws Exception {
		@SuppressWarnings("unchecked")
		List<Account> accounts = (List<Account>) accountRepository.getAll(Account.class);
		int index = accounts.lastIndexOf(account);
		if (index > 0 && accounts.size()>1) {
			Collections.swap(accounts, index, index-1);
		}//TODO does not work item is moved but not persisted
		accountRepository.persistAll();
		notificationProvider.refreshUserInterface();
	}
	
	@ExecutionMode(mode = ExecutionModeType.EXECUTE_METHOD_DIRECTLY)
	public void moveAccountDown(Account account) throws Exception {
		@SuppressWarnings("unchecked")
		List<Account> accounts = (List<Account>) accountRepository.getAll(Account.class);
		int index = accounts.lastIndexOf(account);
		if (index <= accounts.size() && accounts.size()>1) {
			Collections.swap(accounts, index, index+1);
		}//TODO does not work item is moved but not persisted
		accountRepository.persistAll();
		notificationProvider.refreshUserInterface();
	}

}
