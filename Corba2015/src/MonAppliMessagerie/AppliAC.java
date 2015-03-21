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
public class AppliAC implements Runnable{

	private String nodeName;
	private ACimpl acLocal = null;

	public AppliAC(String nodeName) {
		this.nodeName = nodeName;
	}

	public void initServer(String username) {
		try {

			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliCertificationNode.objACServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			this.acLocal = new ACimpl(username);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(acLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(Tools.convertNameToId(username, EntityName.AC_SERVER), "");

			// Enregistrement de l'objet CORBA dans le service de noms
			AppliCertificationNode.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(acLocal));
			System.out.println("AppliUser::initServer() : ==> Nom '" + nameToRegister + "' est enregistre dans le service de noms.");

			String IORServant = AppliCertificationNode.objACServerORB.object_to_string(rootPOA.servant_to_reference(acLocal));
			System.out.println("AppliUser::initServer() : L'objet possede la reference suivante :");
			System.out.println("AppliUser::initServer() : "+IORServant);

	    }
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		AppliCertificationNode.objACServerORB.run();
	}

}
