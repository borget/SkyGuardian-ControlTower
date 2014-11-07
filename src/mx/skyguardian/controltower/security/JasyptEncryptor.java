package mx.skyguardian.controltower.security;

import mx.skyguardian.controltower.exception.WialonAccessDeniedException;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;

public class JasyptEncryptor {
	public static final String ALGORITHM =  "PBEWithMD5AndTripleDES";
	public static final String PASSWORD =  "SkyGuardianEncryption";
	
	public static void main (String[] args) throws Exception {
		String encrypted = encryptWithPBE("000000");
		System.out.println(encrypted);
		System.err.println(decryptPBEText(encrypted));
		
	}
	
	public static String encryptWithPBE (String plainText) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PASSWORD);
		encryptor.setAlgorithm(ALGORITHM);  
		String encryptedText = encryptor.encrypt(plainText);

		return encryptedText;
	}
	
	public static String decryptPBEText(String encryptedText) {
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(PASSWORD);                   
		encryptor.setAlgorithm(ALGORITHM);  
		
		String decryptedText = "";
		try {
			decryptedText = encryptor.decrypt(encryptedText);
		} catch (EncryptionOperationNotPossibleException opNotPo) {
			throw new WialonAccessDeniedException();
		} catch (EncryptionInitializationException eExp) {
			throw new WialonAccessDeniedException();
		}
		 
		return decryptedText;
	}
}
