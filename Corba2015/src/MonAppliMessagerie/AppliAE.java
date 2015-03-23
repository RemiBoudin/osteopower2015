/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * @author jeremy
 *
 */
public class AppliAE {

	//private String nodeName;
		//private String parentNode;
		//private ACimpl acLocal = null;
		public static org.omg.CORBA.ORB objServerORB;
		
		public static void main(String[] args){
			try {
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
				System.out.print("A quel noeud appartient cet AE ? ");
				String nodeName = in.readLine();
			
				// initialisation du serveur AE
				initServer(nodeName);
				
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
		
		public static void initServer(String username) {
			try {
				
				// Gestion du POA
				// ****************
				// Recuperation du POA
				POA rootPOA = POAHelper.narrow(objServerORB.resolve_initial_references("RootPOA"));

				// Creation du servant
				// *********************
				AEimpl aeLocal = new AEimpl(username);

				// Activer le servant au sein du POA et recuperer son ID
				byte[] monEuroId = rootPOA.activate_object(aeLocal);

				// Activer le POA manager
				rootPOA.the_POAManager().activate();

				// Enregistrement dans le service de nommage
				// *******************************************

				// Construction du nom a enregistrer
				org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
				nameToRegister[0] = new org.omg.CosNaming.NameComponent(Tools.convertNameToId(username, EntityName.AE_SERVER), "");
				
				// Enregistrement du nom dans l'annuaire
				Tools.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(aeLocal));
				Tools.showMessage(Tools.MSG_INFO, "AppliAE", "initServer", Tools.convertNameToId(username, EntityName.AE_SERVER) + " est enregistre dans le service de noms.");

			} catch (Exception e) {
				e.printStackTrace();
				
			}

		}
}
