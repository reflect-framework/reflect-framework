package nth.reflect.account.vault.javafx;

import java.util.List;

import nth.reflect.account.vault.domain.AccountVaultColorProvider;
import nth.reflect.account.vault.domain.AccountVaultInfrastructureClasses;
import nth.reflect.account.vault.domain.AccountVaultServiceClasses;
import nth.reflect.fw.gui.style.ColorProvider;
import nth.reflect.fw.javafx.ReflectApplicationForJavaFX;
import nth.reflect.fw.layer5provider.reflection.behavior.displayname.DisplayName;

@DisplayName(defaultEnglish = "Account Vault")
public class AccountVaultForJavaFX extends ReflectApplicationForJavaFX {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public List<Class<?>> getServiceClasses() {
		return AccountVaultServiceClasses.get();
	}

	@Override
	public List<Class<?>> getInfrastructureClasses() {
		return AccountVaultInfrastructureClasses.get();
	}

	@Override
	public ColorProvider getColorProvider() {
		return AccountVaultColorProvider.get();
	}

}
