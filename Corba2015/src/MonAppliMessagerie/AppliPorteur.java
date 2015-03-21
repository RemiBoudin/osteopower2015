/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * @author jeremy
 *
 */
public class AppliPorteur {
	
	public void initServer(String username, String mdp) {
		try {

			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliChat.objPorteurServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			PorteurImpl porteurLocal = new PorteurImpl(username, mdp);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(porteurLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			System.out.println("Sous quel nom voulez-vous enregistrer l'objet Corba ?");
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String nomObj = in.readLine();
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomObj, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			AppliChat.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(porteurLocal));
			System.out.println("==> Nom '" + nomObj + "' est enregistre dans le service de noms.");

			String IORServant = AppliChat.objPorteurServerORB.object_to_string(rootPOA.servant_to_reference(porteurLocal));
			System.out.println("L'objet possede la reference suivante :");
			System.out.println(IORServant);

			// Lancement de l'ORB et mise en attente de requete
			// **************************************************
			AppliChat.objPorteurServerORB.run();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enregistrerCertificat(String usage, String dateExp, String nodeName) {
		return;
	}
	
	public void revoquerCertificat(Certificat certificat, String mdp, String periode) {
		return;
	}

	public Certificat getCertificat() {
		return null;
	}
}
