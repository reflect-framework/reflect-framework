package nth.introspect.infrastructure.xmlfilerepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class CipherUtil {
	private static final String PBE_WITH_MD5_AND_DES = "PBEWithMD5AndDES";

	public static CipherInputStream createCipherInputStream(String passphrase,
			InputStream inputStream) throws InvalidKeyException,
			InvalidAlgorithmParameterException, Exception {

		Cipher cipher = createCipher(passphrase, Cipher.DECRYPT_MODE);

		return new CipherInputStream(inputStream, cipher);
	}

	public static CipherOutputStream createCipherOutputStream(
			String passphrase, OutputStream outputStream)
			throws InvalidKeyException, InvalidAlgorithmParameterException,
			Exception {

		Cipher cipher = createCipher(passphrase, Cipher.ENCRYPT_MODE);

		return new CipherOutputStream(outputStream, cipher);
	}

	private static Cipher createCipher(String passphrase, int mode)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, Exception {

		PBEKeySpec keySpec = new PBEKeySpec(passphrase.toCharArray());
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(PBE_WITH_MD5_AND_DES);
		SecretKey key = keyFactory.generateSecret(keySpec);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update("input".getBytes());
		byte[] digest = md.digest();
		byte[] salt = new byte[8];
		for (int i = 0; i < 8; ++i)
			salt[i] = digest[i];
		PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 20);
		Cipher cipher = Cipher.getInstance(PBE_WITH_MD5_AND_DES);
		cipher.init(mode, key, paramSpec);
		return cipher;
	}


	public static void main(String[] args) {
		File file = new File("CryptoTest.txt");
		String passphrase = "Passphrase";

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			CipherOutputStream cipherOutputStream = createCipherOutputStream(
					passphrase, fileOutputStream);
			PrintWriter printWriter = new PrintWriter(cipherOutputStream);
			printWriter
					.print("This is an test phrase\nAn this is another phrase.");
			printWriter.close();

			FileInputStream fileInputStream = new FileInputStream(file);
			CipherInputStream cipherInputStream = createCipherInputStream(
					passphrase, fileInputStream);
			String txt = new Scanner(cipherInputStream).useDelimiter("\\Z")
					.next();
			System.out.println(txt);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
