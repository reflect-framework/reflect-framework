package nth.reflect.account.vault.domain;

import java.util.Arrays;
import java.util.List;

import nth.reflect.account.vault.domain.account.AccountRepository;
import nth.reflect.infra.generic.xml.XmlConverter;

public class AccountVaultInfrastructureClasses {

	private static List<Class<? extends Object>> infrastructureClasses = Arrays.asList(AccountRepository.class,
			XmlConverter.class);

	public static List<Class<? extends Object>> get() {
		return infrastructureClasses;
	}
}
