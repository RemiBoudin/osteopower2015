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
public class AppliAC implements Runnable {

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
			acLocal = new ACimpl(username);

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
			AppliCertificationNode.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(acLocal));
			Tools.showMessage(Tools.MSG_INFO, "AppliAC", "initServer", Tools.convertNameToId(username, EntityName.AC_SERVER) + " est enregistre dans le service de noms.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		AppliCertificationNode.objACServerORB.run();
	}

}
