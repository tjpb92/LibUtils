package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class Md5 to encrypt data
 */
public class Md5
{
    /**
     * @param password string to bez encoded.
     * @return encrypted password.
     */
    public static String encode(String password)
    {
        byte[] uniqueKey = password.getBytes();
        byte[] hash      = null;

        try
        {
            hash = MessageDigest.getInstance("MD5").digest(uniqueKey);
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new Error("No MD5 support in this VM.");
        }

        //StringBuilder hashString = new StringBuilder();
        StringBuffer hashString = new StringBuffer();
        for (int i = 0; i < hash.length; i++)
        {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1)
            {
                hashString.append('0');
                hashString.append(hex.charAt(hex.length() - 1));
            }
            else
                hashString.append(hex.substring(hex.length() - 2));
	    if (i == 3 || i == 5 || i == 7 || i == 9 ) hashString.append('-');
        }
        return hashString.toString();
    }

    /**
     * Main programme to test Md5 encryption.
     * @param args command line arguments.
     */
    public static void main(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("Usage: java Md5 <string_to_encode>");
            return;
        }

        String toEncode = args[0];

        System.out.println("Original string ... " + toEncode);
        System.out.println("String MD5 ........ " + encode(toEncode));
    }
}
