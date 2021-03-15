package util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Handles hashing-related functionality.
 */
public class HashManager
{
	static MessageDigest md;
	
	static
	{
		try
		{
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the hashed version of the provided string. Uses SHA-512.
	 * @param string
	 * @return
	 */
	public static String hash(String string)
	{
		byte[] hashedPassword = md.digest(string.getBytes(StandardCharsets.UTF_8));
		StringBuffer hashedString = new StringBuffer();
		
		for (int i = 0; i < hashedPassword.length; i++) // Converting bytes array to string
			hashedString.append(Integer.toString((hashedPassword[i] & 0xff) + 0x100, 16).substring(1));
		
		return hashedString.toString();
	}
	
	private HashManager()
	{
	}
}
