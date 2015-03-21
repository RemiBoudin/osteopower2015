/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContext;

/**
 * @author jeremy
 *
 */
public class AppliChat {

	public static org.omg.CORBA.ORB objUserServerORB = null;
	public static org.omg.CORBA.ORB objPorteurServerORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// ##########################################
			// # Intialisation de l'environnement CORBA #
			// ##########################################

			// Initialisation de l'ORB
			AppliChat.objUserServerORB = org.omg.CORBA.ORB.init(args, null);
			AppliChat.objPorteurServerORB = org.omg.CORBA.ORB.init(args, null);
			// Recuperation du naming service
			AppliChat.objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(AppliChat.objUserServerORB.resolve_initial_references("NameService"));

			// ############################################
			// # Intialisation des objets Porteur et User #
			// ############################################

			AppliPorteur porteur = new AppliPorteur();
			AppliUser user = new AppliUser();
			porteur.initServer();
			user.initServer();
			
			System.out.println("## Projet CORBA ##\n");
			System.out.println("-> T'es qui bizu ?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	        String clientName = in.readLine();
	        
			System.out.println("-> Bonjour "+clientName+" et bienvenue dans ce magnifique projet CORBA");
			System.out.println("-> [1] Envoyer un message");
			System.out.println("-> [2] Enregistrer un nouveau certificat");
			System.out.println("-> [3] Révoquer un certificat");
			System.out.println("-> [4] Quitter");
			System.out.println("-> Que veux-tu faire ?\n");

	        String sChoix = in.readLine();
			
	        switch(Integer.parseInt(sChoix)) {
	        	case 1: // Cas envoi de message
	    			System.out.println("-> A qui voulez vous écrire ?");
	        		in = new BufferedReader(new InputStreamReader(System.in));
	    	        String receiverName = in.readLine();
	    	        System.out.println("-> Ecrivez votre message :");
	    	        in = new BufferedReader(new InputStreamReader(System.in));
	    	        String message = in.readLine();
	        		user.repondreToUser(message,receiverName);
	        		break;
	        	case 2: // Cas enregistrement de certificat
	        		porteur.enregistrerCertificat(String usage, String dateExp, String nodeName);
	        		break;
	        	case 3: // Cas révoquer un certificat
	        		
	        		break;
	        	case 4: // Cas quitter
	        		
	        		break;
	        	default:
	        }
	        
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
