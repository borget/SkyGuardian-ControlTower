package mx.skyguardian.controltower.security;

import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class StandardPasswordEncoderUtils {
	private static Logger log = Logger.getLogger(StandardPasswordEncoderUtils.class);
	
	private StandardPasswordEncoderUtils() {
		
	}
	
	public static boolean isValidPassword(String rawPassword, String encodedPassword) {
		log.debug("EncodedPassword:"+encodedPassword);
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();		
		
		try {
			return encoder.matches(rawPassword, encodedPassword);
		} catch (IllegalArgumentException ill) {
			return false;
		} catch (ArrayIndexOutOfBoundsException a) {
			return false;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		System.out.println(encoder.encode("sky2013"));
		
		//627d3eb97bfb9bfd21307d84408b5e199aac37ad5065d81e14d1a60c2c48c4e0b4f51d3d6973b92f
		
		System.out.println(encoder.matches("sky2013", "55a40d14d7088e95f8ee753f23352785447e5a784fd4818dbef85592de9d99c7039dab5dbfc82a56"));
		
	}
}
