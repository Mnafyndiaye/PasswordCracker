package PasswordCracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

// Concrete Product: DictionaryLocalPasswordCracker
public class DictionaryLocalPasswordCracker implements LocalPasswordCracker {

    // Dictionnaire de mots de passe potentiels
    private List<String> dictionary = new ArrayList<>();

    public DictionaryLocalPasswordCracker(String dictionaryFile) {
        loadDictionary(dictionaryFile);
    }

    @Override
    public String crack(String hash) {
        // Itérer à travers le dictionnaire
        for (String password : dictionary) {
            String hashedPassword = hashPassword(password);

            // Vérifiez si le hash correspond au hash donné
            if (hashedPassword.equals(hash)) {
                return password; // Mot de passe trouvé
            }
        }
        return null; // Mot de passe non trouvé dans le dictionnaire
    }
    // Charge le dictionnaire à partir du fichier
    private void loadDictionary(String dictionaryFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(dictionaryFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Fonction de hachage MD5 (ou toute autre fonction de hachage)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();

            // Conversion des bytes en format hexadécimal
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

