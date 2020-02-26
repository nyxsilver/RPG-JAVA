import java.util.*;

public class MenuStart {
    private Scanner scanner = new Scanner(System.in);
    private int choix;
    private int choix2;

    public int getChoix2() {
        return this.choix2;
    }

    public void setChoix2(int choix2) {
        this.choix2 = choix2;
    }


    public int getChoix() {
        return this.choix;
    }

    public void setChoix(int choix) {
        this.choix = choix;
    }
    

    public Scanner getScanner() {
        return this.scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }  
     
    public void Menu() {
        int choix = 0;
        System.out.println("1- Solo");
        System.out.println("2- Multijoueurs");
        System.out.println("3- Quitter");
        System.out.print("Votre choix: ");
        choix = scanner.nextInt();
        if (choix < 1 || choix > 3) {System.out.println("Choix incorrect");}
        else{
            this.choix = choix;
        }
    }

    public void Class() {
        int choix2 = 0;
        System.out.println("Veuillez choisir votre classe pour botter des monstres!!!");
        System.out.println("1- Chevalier Noir");
        System.out.println("2- Archer");
        System.out.println("3- Erudit");
        System.out.print("Votre choix: ");
        choix2 = scanner.nextInt();
        while (choix2 < 1 || choix2 > 3)
            System.out.println("Choix incorrect");
        this.choix2 = choix2;
    }


}