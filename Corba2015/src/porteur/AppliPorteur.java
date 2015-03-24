/**
 * 
 */
package porteur;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import outils.EntityName;
import outils.Tools;
import certificat.Certificat;
import MonAppliMessagerie.AppliChat;
import ae.AE;
import ae.AEHelper;

/**
 * @author jeremy
 * Application du porteur
 */
public class AppliPorteur implements Runnable{
	
	private PorteurImpl porteurLocal = null;
	
	/**
	 * Fonction d'initialisation de l'application Porteur
	 * @param username nom de l'utilisateur 
	 * @param mdp mot de passe de l'utilisateur 
	 */
	public void initServer(String username, String mdp) {
		try {

			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliChat.objServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			this.porteurLocal = new PorteurImpl(username,mdp);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(porteurLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			String nomComplet = Tools.convertNameToId(username, EntityName.PORTEUR_SERVER);
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomComplet, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			Tools.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(porteurLocal));
			Tools.showMessage(Tools.MSG_INFO, "AppliPorteur", "initServer", nomComplet + " est enregistre dans le service de noms.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Enregistre un certificat aurpès d'un noeud de certification
	 * @param usage 
	 * @param dateExp
	 * @param nodeName
	 */
	public void enregistrerCertificat(String usage, String dateExp, String nodeName) {
		this.porteurLocal.enregistrerCertificat(usage, dateExp, nodeName);
	}
	
	/**
	 * Fait une demande de révocation d'un certificat donné, pour une période donnée
	 * @param certificat
	 * @param mdp
	 * @param periode
	 */
	public void revoquerCertificat(Certificat certificat, String mdp, String periode) {
		AE ae = AEHelper.narrow(Tools.findObjByORBName(this.porteurLocal.getCertificatPorteur().IOR_AV, EntityName.AE_SERVER));
		ae.revoquer(this.porteurLocal.getCertificatPorteur(), mdp, periode);
	}

	/**
	 * retourne le certificat du porteur
	 * @return le certificat du porteur
	 */
	public Certificat getCertificat() {
		return new Certificat(this.porteurLocal.getCertificatPorteur());
	}

	@Override
	public void run() {
		// Lancement de l'ORB et mise en attente de requete
		// **************************************************
		AppliChat.objServerORB.run();
	}
}
