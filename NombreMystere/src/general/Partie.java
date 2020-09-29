package general;

import java.util.Scanner;

public class Partie {
	private int nbessai;
	private int chiffreADeviner;
	private String joueur;
	public boolean go = true;

	public Partie(int e) {
		if (e == 1) {
			int chiffre = 0;
			for (int i = 0; i < 4; i++) {
				chiffre += (((int) (Math.random() * 9)) + 1) * (Math.pow(10, i));
			}
			chiffreADeviner = chiffre;
			joueur = "Tu";

		} else if (e == 2) {
			System.out.println("Chiffre a deviner de l'ordinateur");
			Scanner scanner2 = new Scanner(System.in);
			joueur = "l'Ordinateur";
			int choix = scanner2.nextInt();
			chiffreADeviner = choix;
		}
		nbessai = 0;
		this.go = go;
		this.nbessai = nbessai;
		this.chiffreADeviner = chiffreADeviner;
		this.joueur = joueur;
	}

	public int[] diviser(int a) {
		int b = Math.round((float) (a / 1000));
		int c = Math.round((float) (a / 100)) - 10 * b;
		int d = Math.round((float) (a / 10)) - 10 * c - 100 * b;
		int e = Math.round((float) (a / 1)) - 1000 * b - 100 * c - 10 * d;
		int[] f = new int[4];
		f[0] = b;
		f[1] = c;
		f[2] = d;
		f[3] = e;
		return f;
	}

	public String deviner(int a) {
		String affichage = "";
		boolean trouve = true;
		int[] b = this.diviser(a);
		int[] c = this.diviser(this.chiffreADeviner);
		for (int i = 0; i < 4; i++) {
			if (b[i] > c[i]) {
				affichage += "-";
				trouve = false;
			} else if (b[i] < c[i]) {
				affichage += "+";
				trouve = false;
			} else {
				affichage += "=";
			}
		}
		if (trouve) {
			this.go = false;
			return this.joueur + " as gagné !";

		} else {
			if (this.nbessai < 9) {
				this.nbessai += 1;
				return (affichage);
			} else {
				this.go = false;
				return this.joueur + " as perdu !";
			}

		}
	}

	public void devinerOrdinateur() {
		String r = "";
		int chiffre = 5555;
		int chiffre2 = 0;
		int chiffre3 = 0000;
		int j = 0;
		Boolean[] verif = { false, false, false, false };
		String[] ancienne_liste_comparaisons = { "=", "=", "=", "=" };
		int[] ancienne_liste = { 0, 0, 0, 0 };
		while (this.go) {
			if (j == 0) {
				chiffre3 = chiffre;
				ancienne_liste = this.diviser(chiffre3);
			}
			System.out.println("je suis dans this.go" + chiffre);
			r = this.deviner(chiffre);
			if (r.equals(this.joueur + " as perdu !") || r.equals(this.joueur + " as gagné !")) {
				System.out.println(r);
			} else {
				String[] nouvelle_liste_comparaisons = r.split("");
				int[] nouvelle_liste = this.diviser(chiffre);
				chiffre = 0;
				for (int i = 0; i < 4; i++) {
					System.out.println(nouvelle_liste[i]);
					chiffre2 = 0;
					if (nouvelle_liste_comparaisons[i].equals("+")) {
						if (ancienne_liste_comparaisons[i].equals("+") && verif[i] == false || j == 0 && verif[i] == false) {
							chiffre2 = Math.round((nouvelle_liste[i] + 9) / 2);
							System.out.println(chiffre2 + "/+");
						} else {
							chiffre2 = Math.round((nouvelle_liste[i] + ancienne_liste[i]) / 2);
							verif[i] = true;
							System.out.println(ancienne_liste[i] + "+*");
							System.out.println(chiffre2 + "/+");
						}
					} else if (nouvelle_liste_comparaisons[i].equals("-")) {
						if (ancienne_liste_comparaisons[i].equals("-") && verif[i] == false || j == 0 && verif[i] == false) {
							chiffre2 = Math.round((nouvelle_liste[i] + 0) / 2);
							// System.out.println(al[i]+"-*");
							System.out.println(chiffre2 + "/-");
						} else {
							chiffre2 = Math.round((nouvelle_liste[i] + ancienne_liste[i]) / 2);
							verif[i] = true;
							System.out.println(j + "*/*");
							System.out.println(ancienne_liste[i] + "-*");
							System.out.println(chiffre2 + "/-");
						}
					} else {
						chiffre2 = nouvelle_liste[i];
					}
					System.out.println(chiffre2);
					chiffre += chiffre2 * (Math.pow(10, (3 - i)));
					System.out.println(chiffre + "***");
					if (verif[i]==false) {
						ancienne_liste[i] = nouvelle_liste[i];
					}
				}
//				if (j >= 1) {
//					chiffre3 = chiffre;
//					al = this.diviser(chiffre3);
//				}
				j++;
				ancienne_liste_comparaisons = nouvelle_liste_comparaisons;
			}
		}
	}
}
