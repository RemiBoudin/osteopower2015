/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
		
		// Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("AppliChat::main() : Mode du programme (serveur) (client) ?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String idObj = null; 
        try {
			idObj = in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        if (idObj.equals("serveur"))
		//porteur.initServer();
        	user.initServer(args);
        else {
        	porteur.initClient();
        	porteur.envoyerMesssage("FEDEX TV");
		//user.initClient();
        }
		
	}

}
