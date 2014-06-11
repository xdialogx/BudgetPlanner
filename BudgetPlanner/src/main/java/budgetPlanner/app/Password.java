package budgetPlanner.app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

public class Password {
	
	final private String PATERN = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	final private Random rnd = new Random();
	private int lenght = 10;
	
	public String encrypt(String source) {
		String md5 = null;
		try {
		    MessageDigest mdEnc = MessageDigest.getInstance("MD5");
		    mdEnc.update(source.getBytes(), 0, source.length());
		    md5 = new BigInteger(1, mdEnc.digest()).toString(16);
		} catch (Exception ex) {
			new Loger().LogExceptions(ex);
		    return null;
		}
		return md5;
	}
	
	public String getGeneratePassword() {
		StringBuilder sb = new StringBuilder(lenght);
		for (int i = 0; i < lenght; i++) {
			sb.append(PATERN.charAt(rnd.nextInt(PATERN.length())));
		}
		return sb.toString();
	}
	
}
