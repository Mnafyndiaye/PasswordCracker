import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public abstract class LocalPasswordCracker {
    protected String hash(String input, String algorithm) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(input.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public abstract String crack(String hash);
}
