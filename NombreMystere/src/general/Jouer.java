package general;

import java.util.Scanner;

public class Jouer {
	public static void main(String[] args) {
		int choix1=0;
		do {
			Scanner scanner = new Scanner(System.in);
			System.out.println("Veuillez choisir : 1 jouer contre l'ordinateur,2 l'ordinateur joue contre vous,3 quittez");
			choix1 = scanner.nextInt();
			Partie app = new Partie(choix1);
			if (choix1 == 1) {
				while (app.go) {
					System.out.println("Veuillez choisir un chiffre a tester");
					int choix = scanner.nextInt();
					String Affiche = app.deviner(choix);
					System.out.println(Affiche);
				}
			} else if(choix1==2) {
				
				app.devinerOrdinateur();
			}
		}while (choix1 != 3);
	}
}
