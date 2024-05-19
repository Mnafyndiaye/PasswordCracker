package PasswordCracker;

public class BruteForceOnlinePasswordCracker implements OnlinePasswordCracker {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int PASSWORD_LENGTH = 6; // Longueur maximale du mot de passe à tester

    @Override
    public String crack(String url, String login) {
        // Itérer à travers les combinaisons possibles
        char[] password = new char[PASSWORD_LENGTH];
        String foundPassword = null;

        while (foundPassword == null && incrementPassword(password, CHARACTERS)) {
            String passwordStr = new String(password);

            boolean success = tryPassword(url, login, passwordStr);

            if (success) {
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

    // Simule une tentative de connexion avec le mot de passe donné
    private boolean tryPassword(String url, String login, String password) {
        // Vous devrez implémenter votre propre logique pour envoyer la tentative à l'API ou au formulaire web en ligne
        // Cet exemple simule simplement une réponse réussie si le mot de passe est "password"
        return password.equals("password");
    }
}
