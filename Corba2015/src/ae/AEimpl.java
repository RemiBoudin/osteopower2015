package ae;

import java.util.Hashtable;

import outils.EntityName;
import outils.Tools;
import certificat.Certificat;
import porteur.Porteur;
import porteur.PorteurHelper;
import ac.AC;
import ac.ACHelper;

public class AEimpl extends AEPOA {

	private Hashtable<String, String> listeIdClientPorteur;
	private String nodeName;
	private org.omg.CosNaming.NamingContext namingService;

	/**
	 * Constructeur de la classe AE
	 * 
	 * @param username
	 *            nom à donner à l'AE
	 */
	public AEimpl(String nodeName) {
		// this.namingService = namingService;
		this.nodeName = nodeName;
		this.listeIdClientPorteur = new Hashtable<String, String>();

		this.listeIdClientPorteur.put("tata", "tata");
		this.listeIdClientPorteur.put("titi", "titi");
		this.listeIdClientPorteur.put("toto", "toto");
		this.listeIdClientPorteur.put("tutu", "tutu");
		this.listeIdClientPorteur.put("tete", "tete");

		this.listeIdClientPorteur.put("n0", "n0");
		this.listeIdClientPorteur.put("n1", "n1");
		this.listeIdClientPorteur.put("n2", "n2");
		this.listeIdClientPorteur.put("n3", "n3");
		this.listeIdClientPorteur.put("n4", "n4");
		this.listeIdClientPorteur.put("n5", "n5");
		this.listeIdClientPorteur.put("n6", "n6");
		this.listeIdClientPorteur.put("n7", "n7");

	}

	/**
	 * Relai le certificat nouvellement généré au porteur à l'origine de la
	 * demande
	 */
	@Override
	// USELESS DESORMAIS
	public void publier(Certificat certificatPorteur) {
		// Envoi du certificat à l'appli porteur
		Tools.showMessage(Tools.MSG_INFO, "AEimpl", "publier", this.nodeName + " - " + certificatPorteur.proprietaire + " Demande de révocation envoyée à l'AC");
		Porteur porteur = PorteurHelper.narrow(Tools.findObjByORBName(certificatPorteur.proprietaire, EntityName.PORTEUR_SERVER));
		// porteur.receiveNewCertificat(certificatPorteur);
	}

	/**
	 * Vérifie la combinaison identifiant/mot de passe dans la base
	 * 
	 * @param user
	 *            nom de l'utilisateur
	 * @param mdp
	 *            mot de passe de l'utilisateur
	 * @return true si OK, false si non OK
	 */
	private boolean authentifier(String user, String mdp) {
		// Si le user existe dans la DB
		if (this.listeIdClientPorteur.containsKey(user)) {

			// Si le mdp est bon
			if (this.listeIdClientPorteur.get(user).equals(mdp)) {
				Tools.showMessage(Tools.MSG_INFO, "AEimpl", "authentifier", this.nodeName + " - " + user + " authentifié avec succès");

				return true;
			} else { // si le mdp est faux
				Tools.showMessage(Tools.MSG_ERR, "AEimpl", "authentifier", this.nodeName + " - " + user + " echec d'authentification (mdp erroné)");
				return false;

			}
		} else { // si le user n'existe pas
			Tools.showMessage(Tools.MSG_ERR, "AEimpl", "authentifier", this.nodeName + " - " + user + " echec d'authentification (username inconnu)");
			return false;

		}
	}

	/**
	 * Vérifie si l'utilisateur a le droit d'utiliser le certificat pour un
	 * usage donné
	 * 
	 * @param user
	 *            nom de l'utilisateur
	 * @param certifPorteur
	 *            certificat du porteur lié à l'utilisateur
	 * @param usage
	 *            type d'utilisation du certificat
	 * @return true si OK, false si non OK
	 */
	private boolean verifierDroits(String user, Certificat certifPorteur, String usage) {

		// si l'usage précisé dans le certificat correspond à l'usage demandé
		if ((certifPorteur.usage).equals(usage)) {
			Tools.showMessage(Tools.MSG_INFO, "AEimpl", "verifierDroits", this.nodeName + " - " + user + " droits OK");
			return true;
		} else {
			Tools.showMessage(Tools.MSG_ERR, "AEimpl", "verifierDroits", this.nodeName + " - " + user + " droits NOK");
			return false;
		}
	}

	/**
	 * Authentifie l'utilisateur et envoi la demande d'enregistrement à l'AC et
	 * retourne le certificat
	 */
	@Override
	public Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage) {
		try {
			// Si l'authentification n'est pas bonne
			if (!this.authentifier(proprietaire, mdp)) {
				// faire afficher
				// "ERR - Enregistrement - Echec d'authentification"
				Tools.showMessage(Tools.MSG_ERR, "AEimpl", "saveCertificat", this.nodeName + " - " + proprietaire + " echec d'authentification (username inconnu)");
			} else {
				// faire enregistrer sur l'AC supérieure
				Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "Attribut nodeName : [" + this.nodeName + "]");
				AC ac = ACHelper.narrow(Tools.findObjByORBName(this.nodeName, EntityName.AC_SERVER));
				Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "Avant ac.enregistrer()");
				Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "nom propriétaire : " + proprietaire);
				Certificat newCertif = ac.enregistrer(clepublique, proprietaire, dateExpiration, usage);
				Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "Après ac.enregistrer()");

				// Affichages de debug
				if (newCertif == null)
					Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "le certificat est null");
				else
					Tools.showMessage(Tools.MSG_DEBUG, "AEimpl", "saveCertificat", "le certificat n'est pas null");

					Tools.showMessage(Tools.MSG_INFO, "AEimpl", "saveCertificat", this.nodeName + " - " + proprietaire + " Enregistrement auprès de l'AC");
				return new Certificat(newCertif.proprietaire, newCertif.IOR_AV, (short) 1, newCertif.ValiditeDebut, newCertif.ValiditeFin, newCertif.ClePubClient, newCertif.usage, newCertif.Signature);
			}
		} catch (java.lang.NullPointerException e) {
			Tools.showMessage(Tools.MSG_ERR, "AEimpl", "saveCertificat", "Impossible de contacter le noeud de certification demandé !");
		}
		return new Certificat();
	}

	/**
	 * Révoque un certificat auprès l'AC
	 */
	@Override
	public boolean revoquer(Certificat certificatPorteur, String mdp, String periode) {

		// Si l'authentification n'est pas OK
		if (!this.authentifier(certificatPorteur.proprietaire, mdp)) {
			// faire afficher "ERR - Revocation - Echec d'authentification"
			Tools.showMessage(Tools.MSG_ERR, "AEimpl", "saveCertificat", this.nodeName + " - " + certificatPorteur.proprietaire + " echec d'authentification (username inconnu)");
			return false;

		} else { // si l'authentification est OK

			// faire révoquer le certificat sur l'AC
			Tools.showMessage(Tools.MSG_INFO, "AEimpl", "saveCertificat", this.nodeName + " - " + certificatPorteur.proprietaire + " Demande de révocation envoyée à l'AC");
			AC ac = ACHelper.narrow(Tools.findObjByORBName(this.nodeName, EntityName.AC_SERVER));

			return ac.revoquerCertificat(certificatPorteur, periode);

		}
	}
}
