package nth.reflect.account.vault.domain;

import java.util.Arrays;
import java.util.List;

import nth.reflect.account.vault.domain.account.AccountService;
import nth.reflect.account.vault.domain.tag.TagService;
import nth.reflect.account.vault.domain.vault.VaultService;

public class AccountVaultServiceClasses {
	private static List<Class<? extends Object>> serviceClasses = Arrays.asList(VaultService.class,
			AccountService.class, TagService.class);

	public static List<Class<? extends Object>> get() {
		return serviceClasses;
	}
}
