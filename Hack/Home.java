package Hack;

import java.util.Scanner;

public class Home{

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choice;

        System.out.println("*******************************");
        System.out.println("Bienvenue dans notre plateforme");
        System.out.println("*******************************");
        System.out.println("");

        do{

            System.out.println("Que voulais vous faire. Choissisez selon le numéro: ");
            System.out.println("1. Cracker un mot de passe \n2. Cracker un Hash");
            System.out.print("Option: ");
            choice = scanner.nextInt();
            
        } while (choice != 1 && choice != 2);

        //appel de la classe PassWordCrackers
        Factory.getChoix(choice);
       
    }

}
