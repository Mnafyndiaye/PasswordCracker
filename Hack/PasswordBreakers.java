package Hack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;


public class PasswordBreakers {

    //md est une instance de MessageDigest utilisée pour effectuer le hachage
    public static MessageDigest md;
    //in utilisé pour la saisie de l'utilisateur
    public static Scanner in = new Scanner(System.in);
    //Chaine de caractères pour stocker le hash saisi
    public static String hash;
    //Longueur maximal du mot de passe
    public static int pswlength = 8;
    //Une liste d'objet Character contenant les caractères à utiliser
    public static ArrayList<Character> chars;

    public static void AttackDictionMDP(String PasswordAD){
        Scanner y = new Scanner(System.in);
        File file = new File("dictionnaireH.txt");
        Scanner scanner;
        try {
            scanner = new Scanner(file);
            boolean isPasswordFound = false;
            long TempsDebut = System.currentTimeMillis();
            while (scanner.hasNextLine()) {
                String MDP = scanner.nextLine();
                String[] elements = MDP.split(":");
                //Comparaison pour voir si c'est le bon mot de Passe
                if (elements.length == 2 && elements[0].equals(PasswordAD)) {
                    isPasswordFound = true;
                    System.out.println("Mot de passe trouvé !");
                    break;
                }
            }
            //Pour le temps d'éxecution
            long TempsFin = System.currentTimeMillis();
            long TempsMis = TempsFin - TempsDebut;
            if (isPasswordFound) {
                System.out.println("Bye");
            } else {
                System.out.println("Mot de passe non trouvé !");
            }
            System.out.println("Temps d'exécution : " + TempsMis + " ms");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier introuvable !");
        }
    }

    public static void AttackDictionHash(){
        Scanner x = new Scanner(System.in);
        System.out.println("Entrer le Hash du mot de passe que vous voulez décrypter: \n");
        String HashAD = x.nextLine();
        File file = new File("dictionnaireH.txt");
        Scanner scanner;
        //System.out.println("Hash trouvé !");
        try {
            scanner = new Scanner(file);
            boolean isHashFound = false;
            long TempsDebut = System.currentTimeMillis();
            while (scanner.hasNextLine()) {
                String Hash = scanner.nextLine();
                String[] elements = Hash.split(":");
                //Comparaison pour voir si c'est le bon Hash
                if (elements.length == 2 && elements[1].equals(HashAD)) {
                    isHashFound = true;
                    String motDePasse = elements[0];
                    System.out.println("Hash trouvé !");
                    System.out.println("Mot de passe correspondant : " + motDePasse);
                    break;
                }
            }
            //Pour le temps d'éxecution
            long TempsFin = System.currentTimeMillis();
            long TempsFait = TempsFin - TempsDebut;
            if (isHashFound) {
                System.out.println("Bye.");
            } else {
                System.out.println("Hash non trouvé !");
            }
            System.out.println("Temps d'exécution : " + TempsFait + " ms");
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erreur : Fichier introuvable !");
        }
    }

    // méthode brute force pour craquer un mot de passe
    // Elle consiste à créer un tableau nommée chaine[] après saisie du mot de passe. Le tableau aura comme
    // taille password.length(). Dans la méthode ci-dessous, on a comme paramètre la chaine, le mot de passe et la position
    // qui représente la valeur de l'indice de chaque élément du tableau chaine. Après vérification de la position occupée, deux événements peuvent se réaliser
    // 1) La position est égale à la taille du tableau chaine alors nous allons tester toutes les lettres
    // de l'alphabet pour la dernière position, si on trouve un mot égal au password on retourne le mot trouvé, sinon on retourne null
    // 2) Nous n'avons pas encore atteint la dernière position, dans ce cas, pour chaque position on parcourt les 26 lettres
    
    public static String BruteForceCracker(String password, char[] chaine, int position) {

        //lorsqu'on arrive à la dernière position de la chaine
        if(position == chaine.length - 1) {

            //On parcourt les différentes lettres de l'alphabet pour la dernière position
            for(int i = 97; i < 123; i++) {
                chaine[position] = decoderLaLettre(i);
                String motTrouve = new String(chaine);
                if(password.equals(motTrouve)) {
                    return motTrouve;
                }
            }
            //si motTrouve != password
            return null;

        //Si la dernière position n'est pas atteinte  
        }else {

            for(int i = 97; i < 124 - 1; i++) {
                chaine[position] = decoderLaLettre(i);
                if(BruteForceCracker(password, chaine, position + 1) != null) {
                    return new String(chaine);
                }
            }
            return null;
        }

    }//fin bruteForceCracker()

    //donne la lettre correspondant au codeAscii
    public static char decoderLaLettre(int codeAscii) {
        return (char) codeAscii;
    }


    // Cette méthode est appelée pour démarrer le processus de craquage du hash.
    // Elle demande à l'utilisateur de saisir le type de hash et tente de créer une instance correspondante de MessageDigest.
    // En cas d'exception NoSuchAlgorithmException, un message d'erreur est affiché et l'utilisateur est invité à saisir à nouveau.
    // Ensuite, l'utilisateur est invité à saisir le hash à craquer.
    // La méthode breakHash() est appelée pour commencer le processus de craquage.
    public static void BruteForce() {

        try {

            md = MessageDigest.getInstance("MD5");
            
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Erreur lors de la création de l'instance MessageDigest.");
            e.printStackTrace();
            return;
        }

        System.out.println("Saisir le hash");
        hash = in.nextLine();

        Instant start = Instant.now();
        breakHash(hash);
        Instant end = Instant.now();
        Duration duration = Duration.between(start, end);
        System.out.println("Le temps mis pour retrouver le mot de passe est de: " + duration.toMillis() + " millisecondes.");
        
    }

    public static void breakHash(String hash) {

        int hashCount = 0;

        //Ce tableau va contenir l'ensemble des caractères
        char[] charsTemp = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 
        'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

        //On crée une nouvelle instance, pouvant stocker jusqu'à charsTemp.length lettres
        chars = new ArrayList<Character>(charsTemp.length);

        //Cette boucle parcourt le tableau charsTemps et recupère chaque caractère qu'il affecte à chars 
        //en utilisant add()
        for(int i =0; i < charsTemp.length; i++) {
            chars.add(charsTemp[i]);
        }

        char[] password = new char[pswlength];
        //La boucle while effectue une boucle infinie pour craquer le hash, si après hachage de la combinaison obtenue
        //avec la méthode incrementString() on trouve que ça correspond au hash saisi, on retourne la combinaison
        //sinon on continue d'appeller incrementString()
        while(true) {

            hashCount++;

            password = incrementString(password, chars).toCharArray();

            String temp = new String(password).trim();
            if(hash.equals(ConvertHash.convertToHexString(md.digest(temp.getBytes())))) {

                System.out.println(new String(password).trim() + " == " + hash);
                return;
            }
        }
    }//fin breakhash()

    //Elle incrémente le mot de passe à partir des caractères disponibles
    public static String incrementString(char[] password, ArrayList<Character> chars) {

        char[] pswTemp = password;

        if(pswTemp[0] == chars.get(chars.size() - 1)) {

            pswTemp = increment(password, chars, 0);
            
        }
        else {

            pswTemp[0] = (char) chars.get((chars.indexOf(pswTemp[0]) + 1) % chars.size());

        }
        return new String(pswTemp);
    }

    //Elle permet de passer à l'indice i+1 du tableau password si avec l'indice i on a parcourut toutes
    //les lettres
    public static char[] increment(char[] password, ArrayList<Character> chars2, int i) {

        password[i] = chars2.get(0);

        if (i + 1 >= password.length ) {

            return password;

        }else if (password[(i + 1)] == chars.get(chars.size() - 1)) {

            return increment(password, chars2, i + 1);

        }
        else {

            password[i + 1] = chars.get(chars.indexOf(password[i+1]) + 1);

        }
        return password;
    }


}