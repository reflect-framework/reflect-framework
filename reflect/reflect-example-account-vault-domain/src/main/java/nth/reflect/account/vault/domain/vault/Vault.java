package nth.reflect.account.vault.domain.vault;

public class Vault {
	public String vaultName;
	public Credentials googleCredentials;

	public String getVaultName() {
		return vaultName;
	}

	public void setVaultName(String vaultName) {
		this.vaultName = vaultName;
	}

	public Credentials getGoogleCredentials() {
		return googleCredentials;
	}

	public void setGoogleCredentials(Credentials googleCredentials) {
		this.googleCredentials = googleCredentials;
	}

}
