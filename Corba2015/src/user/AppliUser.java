/**
 * 
 */
package user;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import MonAppliMessagerie.AppliChat;
import outils.EntityName;
import outils.Tools;
import certificat.Certificat;
import porteur.Porteur;
import porteur.PorteurHelper;

/**
 * @author jeremy
 * Classe permettant de lancer l'application "Utilisateur"
 */
public class AppliUser implements Runnable {
	private UserImpl userLocal;
	private String username;

	/**
	 * Methode d'initialisation de l'objet CORBA User
	 * @param username nom de l'utilisateur en question
	 */
	public void initServer(String username) {
		try {
			this.username = username;
			// Gestion du POA
			// ****************
			// Recuperation du POA
			POA rootPOA = POAHelper.narrow(AppliChat.objServerORB.resolve_initial_references("RootPOA"));

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
			nameToRegister[0] = new org.omg.CosNaming.NameComponent(nomComplet, "");

			// Enregistrement de l'objet CORBA dans le service de noms
			Tools.objDistantNamingService.rebind(nameToRegister, rootPOA.servant_to_reference(userLocal));
			Tools.showMessage(Tools.MSG_INFO, "AppliUser", "initServer", nomComplet + " est enregistre dans le service de noms.");

		} catch (Exception e) {
			Tools.showMessage(Tools.MSG_ERR, "AppliUser", "initServer", "Erreur d'initialisation du UserServer");
			e.printStackTrace();
		}
	}


	/**
	 * Vérifie la validité temporelle du certificat
	 * @param cert certificat à vérifier
	 * @return VRAI si le certificat n'est pas périmé
	 */
	private boolean checkPeriode(Certificat cert) {
		return this.userLocal.verifierPeriode();
	}

	/**
	 * Vérifie l'usage d'un certificat
	 * @param cert certificat a vérifier
	 * @return VRAI si l'usage est correct
	 */
	private String checkUsage(Certificat cert) {
		return this.userLocal.verifierUsage(cert);
	}

	/**
	 * Vérifie le chemin de certification d'un certificat
	 * @param cert certificat à vérifier
	 * @return VRAI si le chemin de certiication est valide
	 */
	private boolean checkCheminCertification(Certificat cert) {


			return this.userLocal.verifierCheminCertification(cert);

	}

	@Override
	public void run() {
		// Lancement de l'ORB et mise en attente de requete
		// **************************************************
		AppliChat.objServerORB.run();
	}

/**
 * Methode permettant d'afficher un message
 * @param message message à afficher
 * @param receiverName nom du destinataire
 */
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
			Tools.showMessage(Tools.MSG_INFO, "AppliUser", "repondreToUser", "Chemin de certif de l'interlocuteur distant" + receiverName + " OK");
			if (cheminCertifie) {
				User userDistant = UserHelper.narrow(Tools.findObjByORBName(receiverName, EntityName.USER_SERVER));
				String messageChiffre = Tools.chiffrerMessage(message, "");
				userDistant.afficherMessage(this.username, messageChiffre, true);
				Tools.showMessage(Tools.MSG_INFO, "AppliUser", "repondreToUser", "Message envoyé à " + receiverName);
			} else {
				Tools.showMessage(Tools.MSG_INFO, "AppliUser", "repondreToUser", "Problème dans le chemin de certif de l'interlocuteur " + receiverName + " distant");
			}

		} catch (java.lang.NullPointerException e) {
			Tools.showMessage(Tools.MSG_ERR, "AppliUser", "repondreToUser", receiverName + " inconnu au bataillon !");

		} catch (Exception e) {
			Tools.showMessage(Tools.MSG_ERR, "AppliUser", "repondreToUser", receiverName + " Il faut s'enregistrer un certificat avant d'envoyer un message !");
		}

	}

	/**
	 * Fonction de debug
	 */
	public void debugCommunication() {
		// Recherche du (serveur du porteur) du (destinataire)
		org.omg.CORBA.Object objDistant = Tools.findObjByORBName(this.username, EntityName.PORTEUR_SERVER);
		Porteur porteur = PorteurHelper.narrow(objDistant);

		porteur.getCertificatPorteur();
	}
}
