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
public class AppliPorteur implements Runnable{
	
	private PorteurImpl porteurLocal = null;
	
	public void initServer(String username, String mdp) {
		try {
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliChat.objPorteurServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			this.porteurLocal = new PorteurImpl(username, mdp);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(this.porteurLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(Tools.convertNameToId(username, EntityName.PORTEUR_SERVER), "");

			// Enregistrement de l'objet CORBA dans le service de noms
			AppliChat.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(porteurLocal));
			System.out.println("Appliporteur::initServer() : ==> Nom '" + nameToRegister + "' est enregistre dans le service de noms.");

			String IORServant = AppliChat.objPorteurServerORB.object_to_string(rootPOA.servant_to_reference(porteurLocal));
			System.out.println("Appliporteur::initServer() : L'objet possede la reference suivante :");
			System.out.println("Appliporteur::initServer() : "+IORServant);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void enregistrerCertificat(String usage, String dateExp, String nodeName) {
		return;
	}
	
	public void revoquerCertificat(Certificat certificat, String mdp, String periode) {
		System.out.println("si ca marche c cool :)");
		return;
	}

	public Certificat getCertificat() {
		return null;
	}

	@Override
	public void run() {
		// Lancement de l'ORB et mise en attente de requete
		// **************************************************
		AppliChat.objPorteurServerORB.run();
	}
}
