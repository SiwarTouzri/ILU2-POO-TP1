package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche m1 ; 
	
	public Village(String nom, int nbVillageoisMaximum,int nbEtals ) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		m1 =new Marche(30) ; 

	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit, int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		int trouve = m1.trouverEtalLibre();
		chaine.append(vendeur.getNom() + " cherche un endroit pour vendre " + nbProduit + " " + produit + ".\n");
		if (trouve == -1) {
			chaine.append("Tous les étals sont occupés !");
		} else {
			m1.utiliserEtal(trouve, vendeur, produit, nbProduit);
			chaine.append(
					"Le vendeur " + vendeur.getNom() + " vend des " + produit + " à l'étal n° " + (trouve + 1) + ".");
		}

		return chaine.toString() + "\n";
	}

	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des " + produit + " sont: \n");
		Etal[] etalsProduit = m1.trouverEtals(produit);
		if (etalsProduit != null) {
			for (int i = 0; i < etalsProduit.length; i++) {
				chaine.append("-" + etalsProduit[i].getVendeur().getNom() + "\n");
			}

		}
		return chaine.toString();

	}

	public Etal rechercherEtal(Gaulois vendeur) {
		return m1.trouverVendeur(vendeur);
	}
	
	

	public String partirVendeur(Gaulois vendeur) {
		Etal etal = m1.trouverVendeur(vendeur);
		StringBuilder chaine = new StringBuilder();
		if (etal != null) {
			chaine.append(etal.libererEtal());
		}
		return chaine.toString();
	}

	public String afficherMarche() {
		String intro = "Le marché du village <<" + nom + ">> possède plusieurs étals :\n";
		return intro + m1.afficherMarche();
	}

	
	private class Marche{
		private Etal[] etal ; 
		public Marche(int nombreEtal) {
			etal=new Etal[nombreEtal] ; 
		}
		private void utiliserEtal (int indiceEtal , Gaulois vendeur , String produit , int nbProduit ) {
			if (indiceEtal >= 0 &&  indiceEtal< etal.length) {
				etal[indiceEtal] = new Etal( ) ; 
			}
			else {
				System.out.println("l'indice de l'état est invalide ");
			}
		}
		public int trouverEtalLibre() {
		    for (int i = 0; i < etal.length; i++) {
		        if (etal[i] != null && !etal[i].isEtalOccupe()) {
		            return i; // Retourne l'index de l'étal libre trouvé
		        }
		    }
		    return -1; // Aucun étal libre trouvé, retourne -1
		}
		private Etal[] trouverEtals(String produit) {
			int nbEtal = 0;
			for (Etal etal : etal) {
				if (etal.isEtalOccupe() && etal.contientProduit(produit)) {
					nbEtal++;
				}
			}
			Etal[] etalsProd = null;
			if (nbEtal > 0) {
				etalsProd = new Etal[nbEtal];
				int nbEtalTrouve = 0;
				for (int i = 0; i < etal.length && nbEtalTrouve < nbEtal; i++) {
					if (etal[i].isEtalOccupe() && etal[i].contientProduit(produit)) {
						etalsProd[nbEtalTrouve] = etal[i];
						nbEtalTrouve++;
					}
				}
			}
			return etalsProd;
		}
		private Etal trouverVendeur(Gaulois gaulois) {
			Etal  etals = new Etal();
			boolean vendeurConnu = false;
			for (int i = 0; i < etal.length || vendeurConnu; i++) {

				if (etal[i].getVendeur() == gaulois) {
					etals = etal[i];
					vendeurConnu = true;
				}
			}
			return etals;
		}

		private String afficherMarche() {
			StringBuilder trouve = new StringBuilder();
			int nbEtalsVides = 0;
			for (int i = 0; i < etal.length; i++) {
				if (etal[i].isEtalOccupe()) {
					trouve.append(etal[i].afficherEtal());
				} else {
					nbEtalsVides++;
				}
			}
			if (nbEtalsVides != 0) {
				trouve.append("Il reste " + nbEtalsVides + " étal(s) non utilisé(s) dans le marché.");
			}
			return trouve.toString();
		}
			
	}
	

}