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
public class AppliAV implements Runnable {

	private String nodeName;
	private AVimpl avLocal = null;

	public AppliAV(String nodeName) {
		this.nodeName = nodeName;
	}

	public void initServer(String username) {
		try {
			
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliCertificationNode.objAVServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			avLocal = new AVimpl(username);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(avLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(Tools.convertNameToId(username, EntityName.AV_SERVER), "");

			// Enregistrement de l'objet CORBA dans le service de noms
			// Recuperation du service de noms
			//NamingContext nc = Tools.getNamingContext(AppliCertificationNode.objAVServerORB);
			//nc.rebind(nameToRegister, rootPOA.servant_to_reference(avLocal));
			AppliCertificationNode.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(avLocal));
			System.out.println("AppliUser::initServer() : ==> Nom '" + nameToRegister + "' est enregistre dans le service de noms.");

			//String IORServant = AppliCertificationNode.objAVServerORB.object_to_string(rootPOA.servant_to_reference(avLocal));
			//System.out.println("AppliUser::initServer() : L'objet possede la reference suivante :");
			//System.out.println("AppliUser::initServer() : " + IORServant);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		AppliCertificationNode.objAVServerORB.run();
	}

}
