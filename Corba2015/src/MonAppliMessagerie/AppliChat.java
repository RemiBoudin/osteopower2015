/**
 * 
 */
package MonAppliMessagerie;


/**
 * @author jeremy
 *
 */
public class AppliChat {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Créer les deux objets
		AppliPorteur porteur = new AppliPorteur();
		AppliUser user = new AppliUser();
		
		// Initialiser les clients et serveurs
		porteur.initServer();
		user.initServer();
		
		porteur.initClient();
		user.initClient();
		
		
		
	}

}
