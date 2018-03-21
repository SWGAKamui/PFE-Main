import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *@authors ZAMOUCHE & ZEROUALI
 */

/**
  Classe MD5 pour hachage
 */
public class MD5{

    public static String getMD5(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] msgByte = md.digest(pwd.getBytes());
            BigInteger number = new BigInteger(1, msgByte);
            String hashtext = number.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


}
