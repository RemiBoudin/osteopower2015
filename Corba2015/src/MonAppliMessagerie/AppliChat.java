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
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
