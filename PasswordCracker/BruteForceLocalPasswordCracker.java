package PasswordCracker;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// Concrete Product: BruteForceLocalPasswordCracker
public class BruteForceLocalPasswordCracker implements LocalPasswordCracker {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int PASSWORD_LENGTH = 6; // Longueur maximale du mot de passe à tester

    private String targetHash = ""; // Hachage du mot de passe que vous essayez de craquer

    public BruteForceLocalPasswordCracker(String targetHash) {
        this.targetHash = targetHash;
    }
    @Override
    public String crack(String hash) {
        // Itérer à travers les combinaisons possibles
        char[] password = new char[PASSWORD_LENGTH];
        String foundPassword = null;

        while (foundPassword == null && incrementPassword(password, CHARACTERS)) {
            String passwordStr = new String(password);

            String hashedPassword = hashPassword(passwordStr);

            // Vérifiez si le hachage correspond
            if (hashedPassword.equals(targetHash)) {
                foundPassword = passwordStr;
            }
        }

        return foundPassword;
    }

    // Incrémente le mot de passe suivant dans l'ordre lexicographique
    private boolean incrementPassword(char[] password, String characters) {
        int carry = 1;
        for (int i = password.length - 1; i >= 0; i--) {
            int index = characters.indexOf(password[i]);
            index = (index + carry) % characters.length();
            password[i] = characters.charAt(index);
            carry = index == 0 ? 1 : 0;
            if (carry == 0) {
                break;
            }
        }
        return carry == 0;
    }

    // Fonction de hachage SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));

            // Convertir le tableau de bytes en format hexadécimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}