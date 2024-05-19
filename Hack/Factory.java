package Hack;

import java.util.Scanner;
import java.time.Duration;
import java.time.Instant;

public class Factory{
    private static String password;

    private static Scanner scanner = new Scanner(System.in);

    public static String getChoix(int choix) {
        
        int method = 0;
        int longueur;
        String motDeviner;

        //Si l'utilisateur choisit de craquer un mot de passe
        if(choix == 1) {
           
            System.out.println("Saisir le mot de passe: ");
            password = scanner.nextLine();
            do{
                
                System.out.println("Comment craquer le mot de passe: \n 1.Brute force \n 2.Attaque par dictionnaire");
                method = scanner.nextInt();

            }while (method != 1 && method != 2);

            //S'il choisit la méthode brute force
            if (method == 1) {
        
                int position = 0;
                longueur = password.length();
                char[] motADeviner = new char[longueur];
                Instant start = Instant.now();
                motDeviner = PasswordBreakers.BruteForceCracker(password, motADeviner, position);
                Instant end = Instant.now();
                Duration duration = Duration.between(start, end);
                System.out.println("Le temps mis pour trouver le mot : " + duration.toMillis() + " millisecondes.");
                System.out.println("Le mot de passe est: "+motDeviner); 
                    
            }
            else {
                PasswordBreakers.AttackDictionMDP(password);
            }
            return null;

        //Si l'utilisateur choisit de craquer un hash
        }
        else {

            do{
                System.out.println("Comment craquer le hash: \n 1.Brute force \n 2.Attaque par dictionnaire");
                method = scanner.nextInt();
            } while (method != 1 && method != 2);

            //S'il choisit la méthode brute force
            if(method == 1) {

                PasswordBreakers.BruteForce();
            }
            else {
                PasswordBreakers.AttackDictionHash();
            }
            return null;
        }
        
    }
    //fin getchoix()
}
//fin PassWordCrackersFactory