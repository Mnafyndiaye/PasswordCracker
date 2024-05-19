package PasswordCracker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Concrete Product: DictionaryOnlinePasswordCracker
public class DictionaryOnlinePasswordCracker implements OnlinePasswordCracker {
    // Dictionnaire de mots de passe potentiels
    private List<String> dictionary = new ArrayList<>();

    public DictionaryOnlinePasswordCracker(String dictionaryFile) {
        loadDictionary(dictionaryFile);
    }
    @Override
    public String crack(String url, String login) {
        // Itérer à travers le dictionnaire
        for (String password : dictionary) {
            boolean success = tryPassword(url, login, password);

            // Vérifiez si la tentative a réussi
            if (success) {
                return password; // Mot de passe trouvé
            }
        }
        return null; // Mot de passe non trouvé dans le dictionnaire
    }

    // Simule une tentative de connexion avec le mot de passe donné
    private boolean tryPassword(String url, String login, String password) {
        // Vous devrez implémenter votre propre logique pour envoyer la tentative à l'API ou au formulaire web en ligne
        // Cet exemple simule simplement une réponse réussie si le mot de passe est "password"
        return password.equals("password");
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
}