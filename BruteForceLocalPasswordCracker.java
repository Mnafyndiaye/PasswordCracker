import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BruteForceLocalPasswordCracker implements PasswordCracker {

    @Override
    public String crack(String hash) {
        // Définir la longueur maximale du mot de passe à essayer
        int maxLength = 8;

        // Définir les caractères possibles dans le mot de passe
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        // Boucler sur toutes les longueurs possibles de mot de passe
        for (int length = 1; length <= maxLength; length++) {
            // Créer un tableau de caractères pour stocker le mot de passe en cours d'essai
            char[] password = new char[length];
            // Appeler la méthode récursive pour essayer toutes les combinaisons de mots de
            // passe de cette longueur
            if (bruteForceCracker(hash, password, characters, 0))
                return new String(password); // Retourner le mot de passe si trouvé
        }

        // Retourner null si le mot de passe n'est pas trouvé pour toutes les longueurs
        // possibles
        return null;
    }

    private boolean bruteForceCracker(String hash, char[] password, String characters, int position) {
        // Si nous avons atteint la fin du mot de passe en cours d'essai
        if (position == password.length) {
            // Vérifier si le hachage du mot de passe correspond au hachage fourni
            String hashedPassword = hashFunction(new String(password)); // Supposons qu'il y ait une fonction de hachage
                                                                        // définie
            return hashedPassword.equals(hash);
        }

        // Sinon, parcourir tous les caractères possibles pour la position actuelle du
        // mot de passe
        for (int i = 0; i < characters.length(); i++) {
            password[position] = characters.charAt(i);
            // Récursivement essayer toutes les combinaisons de mots de passe possibles
            if (bruteForceCracker(hash, password, characters, position + 1))
                return true; // Retourner true dès qu'un mot de passe valide est trouvé
        }
        return false; // Retourner false si aucun mot de passe valide n'est trouvé pour cette position
    }

    private String hashFunction(String password) {
        try {
            // Créer une instance de MessageDigest avec l'algorithme MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            // Calculer le hachage du mot de passe
            byte[] bytes = md.digest(password.getBytes());
            // Convertir les bytes du hachage en une représentation hexadécimale
            BigInteger bigInt = new BigInteger(1, bytes);
            String hashedPassword = bigInt.toString(16);
            // Ajouter des zéros non significatifs au début si nécessaire
            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }
            return hashedPassword;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Gérer l'exception NoSuchAlgorithmException selon vos besoins
            return null;
        }
    }
}
