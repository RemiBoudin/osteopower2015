/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import outils.EntityName;
import outils.Tools;
import ac.ACimpl;

/**
 * @author jeremy
 *
 */
public class AppliAC {
	public static org.omg.CORBA.ORB objServerORB;
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		try {
			if (args.length == 0) {
				System.out.println("Usage : AppliAC [corbaloc_naming_service]");
				System.exit(-1);
			}
			Tools.corbalocNamingService = args[0];
			
			// Choix du niveau de Logs
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("> Mode verbose [I]nfo / [D]ebug / [E]rr / [entrée] : ");
			Tools.verbose = in.readLine();

			// Initialisation de l'ORB
			objServerORB = org.omg.CORBA.ORB.init(args, null);

			// Recuperation du naming service
			//objDistantNamingService = org.omg.CosNaming.NamingContextHelper.narrow(objServerORB.string_to_object("corbaloc:iiop:1.2@localhost:2001/NameService"));
			Tools.initNamingService(objServerORB);
			
			// Récupération du nom du noeud
			System.out.print("A quel noeud appartient cet AC ? ");
			String nodeName = in.readLine();
			
			// Récupération du nom du noeud parent
			System.out.print("A quel noeud supérieur voulez-vous le rattacher ? (Laissez vide si racine) ");
			String parentNode = in.readLine();
			
			// initialisation du serveur AC
			initServer(nodeName, parentNode);
			

			// Affichage du contenu du service de noms
			System.out.println("\n\n-------------------------");
			System.out.println("CONTENU DU NAMING SERVICE");
			System.out.println("-------------------------");
			Tools.printContext(Tools.objDistantNamingService, "");
			System.out.println("-------------------------");
			
			objServerORB.run();
			
		} catch (Exception e){
			e.printStackTrace();
		}	
	}
	/**
	 * Méthode d'initialisation  du server
	 * @param username
	 * 				 nom de l'utilisateur
	 * @param parentNode 
	 * 					nom du noeud parent
	 */
	public static void initServer(String username,String parentNode) {
		try {
			
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(objServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			ACimpl acLocal = new ACimpl(username, parentNode);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(acLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(Tools.convertNameToId(username, EntityName.AC_SERVER), "");
			
			// Enregistrement du nom dans l'annuaire
			Tools.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(acLocal));
			Tools.showMessage(Tools.MSG_INFO, "AppliAC", "initServer", Tools.convertNameToId(username, EntityName.AC_SERVER) + " est enregistre dans le service de noms.");

		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

}
