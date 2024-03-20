package histoire;
import villagegaulois.Etal; 
import personnages.Gaulois;
public class ScenarioCasDegrade {
	public static void main(String[] args) {
		try {
			Etal etal = new Etal();
			Gaulois acheteur = new Gaulois("Astourix", 10); 
			etal.acheterProduit(-6 , acheteur);
			System.out.println("Fin du test");
		}
		catch(IllegalArgumentException e){
			System.out.println("L'étal doit etre occupé");
		}
		catch(Exception e ) {
			e.printStackTrace();
			
		}
		
	}
}
