/**
 * 
 */
package MonAppliMessagerie;

/**
 * @author jeremy
 *
 */
public class CertificationNode {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Création des objets
		AppliAE AE = new AppliAE();
		AppliAC AC = new AppliAC();
		AppliAV AV = new AppliAV();

		//Initialisation des clients et des serveurs
		AE.initServer();
		AE.initClient();

		AC.initServer();
		AC.initClient();

		AV.initServer();
		AV.initClient();
	}

}
