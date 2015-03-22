/**
 * 
 */
package MonAppliMessagerie;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 * @author jeremy
 *
 */
public class AppliUser implements Runnable {
	private UserImpl userLocal;
	private String username;

	public void initServer(String username) {
		try {
			this.username = username;
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliChat.objUserServerORB.resolve_initial_references("RootPOA"));

			// Creation du servant
			// *********************
			this.userLocal = new UserImpl(username);

			// Activer le servant au sein du POA et recuperer son ID
			byte[] monEuroId = rootPOA.activate_object(userLocal);

			// Activer le POA manager
			rootPOA.the_POAManager().activate();

			// Enregistrement dans le service de nommage
			// *******************************************

			// Construction du nom a enregistrer
			org.omg.CosNaming.NameComponent[] nameToRegister = new org.omg.CosNaming.NameComponent[1];
			String nomComplet = Tools.convertNameToId(username, EntityName.USER_SERVER);
			System.out.println("CROTTE NOM COMPLET " + nomComplet);
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomComplet, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			AppliChat.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(userLocal));
			System.out.println("AppliUser::initServer() : ==> Nom '" + nameToRegister + "' est enregistre dans le service de noms.");

			String IORServant = AppliChat.objUserServerORB.object_to_string(rootPOA.servant_to_reference(userLocal));
			System.out.println("AppliUser::initServer() : L'objet possede la reference suivante :");
			System.out.println("AppliUser::initServer() : " + IORServant);

		} catch (Exception e) {
			System.out.println("AppliUser::initServer() : Erreur d'initialisation du UserServer");
			e.printStackTrace();
		}
	}


	private boolean checkPeriode(Certificat cert) {
		return this.userLocal.verifierPeriode();
	}

	private String checkUsage(Certificat cert) {
		return this.userLocal.verifierUsage();
	}

	private boolean checkCheminCertification(Certificat cert) {

			return this.userLocal.verifierCheminCertification(cert);

	}

	@Override
	public void run() {
		// Lancement de l'ORB et mise en attente de requete
		// **************************************************
		AppliChat.objUserServerORB.run();
	}

	public void debug(String sender, String message, boolean chiffred, String dest) {
		// User user = UserHelper.narrow(Tools.findObjByORBName(dest,
		// EntityName.USER_SERVER));
		// user.afficherMessage(sender, message, chiffred);
	}

	public void repondreToUser(String message, String receiverName) {

		// #################################################################
		// ## DEMANDE DU CERTIFICAT AU SERVEUR DU PORTEUR DU DESTINATAIRE ##
		// #################################################################

		// Recherche du (serveur du porteur) du (destinataire)
		org.omg.CORBA.Object objDistant = Tools.findObjByORBName(receiverName, EntityName.PORTEUR_SERVER);

		// Cast de l'objet distant au format Porteur
		Porteur objDistantPorteurServer = PorteurHelper.narrow(objDistant);

		// Demande du certificat au porteur
		try {
			Certificat certificatDistant = objDistantPorteurServer.getCertificatPorteur();

			boolean cheminCertifie;

			cheminCertifie = this.userLocal.verifierCheminCertification(certificatDistant);
			System.out.println("AppliUser:: PChemin de certif de l'interlocuteur distant" + receiverName + " OK");
			if (cheminCertifie) {
				User userDistant = UserHelper.narrow(Tools.findObjByORBName(receiverName, EntityName.USER_SERVER));
				String messageChiffre = Tools.chiffrerMessage(message, "");
				userDistant.afficherMessage(this.username, messageChiffre, true);
				System.out.println("AppliUser:: Message affiché chez " + receiverName);
			} else {
				System.out.println("AppliUser:: Problème dans le chemin de certif de l'interlocuteur " + receiverName + " distant");
			}

		} catch (java.lang.NullPointerException e) {
			System.out.println("AppliUser:: " + receiverName + " inconnu au bataillon !");

		} catch (Exception e) {
			System.out.println("AppliUser::repondreToUser() " + receiverName + " Il faut s'enregistrer un certificat avant d'envoyer un message !");
		}

	}

	public void debugCommunication() {
		// Recherche du (serveur du porteur) du (destinataire)
		org.omg.CORBA.Object objDistant = Tools.findObjByORBName(this.username, EntityName.PORTEUR_SERVER);
		Porteur porteur = PorteurHelper.narrow(objDistant);

		porteur.getCertificatPorteur();
	}
}
