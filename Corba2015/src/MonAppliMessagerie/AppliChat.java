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
	
	public static org.omg.CORBA.ORB objDistantORB = null;
	public static org.omg.CosNaming.NamingContext objDistantNamingService = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        try {
		
        // ##########################################
        // # Intialisation de l'environnement CORBA #
        // ##########################################
        	
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
        // Recuperation du naming service
		NamingContext nameRoot=org.omg.CosNaming.NamingContextHelper.narrow(orb.resolve_initial_references("NameService"));

		
        // ############################################
        // # Intialisation des objets Porteur et User #
        // ############################################

		AppliPorteur porteur = new AppliPorteur();
		AppliUser user = new AppliUser();
		
		// Saisie du nom de l'objet (si utilisation du service de nommage)
        System.out.println("AppliChat::main() : Mode du programme (serveur) (client) ?");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String idObj = null; 
			idObj = in.readLine();
		
        if (idObj.equals("serveur"))
		//porteur.initServer();
        	user.initServer(args);
        else {
        	porteur.init(args);
		//user.initClient();
        }
		
	}
        catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
