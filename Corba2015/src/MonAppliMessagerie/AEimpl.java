package MonAppliMessagerie;

import java.util.Hashtable;

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
		//this.namingService = namingService;
		this.nodeName = nodeName;
		this.listeIdClientPorteur = new Hashtable<String, String>();

		this.listeIdClientPorteur.put("titi", "titi");
		this.listeIdClientPorteur.put("toto", "toto");

	}

	/**
	 * Relai le certificat nouvellement généré au porteur à l'origine de la
	 * demande
	 */
	@Override
	// USELESS DESORMAIS
	public void publier(Certificat certificatPorteur) {
		// Envoi du certificat à l'appli porteur
		System.out.println(this.nodeName + " - INFO - " + certificatPorteur.proprietaire + " Demande de révocation envoyée à l'AC");

		Porteur porteur = PorteurHelper.narrow(Tools.findObjByORBName(certificatPorteur.proprietaire, EntityName.PORTEUR_SERVER));
		//porteur.receiveNewCertificat(certificatPorteur);
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
				System.out.println(this.nodeName + " - INFO - " + user + " authentifié avec succés");

				return true;
			} else { // si le mdp est faux
				System.out.println(this.nodeName + " - ERR - " + user + " echec d'authentification (mdp erron�)");
				return false;

			}
		} else { // si le user n'existe pas
			System.out.println(this.nodeName + " - ERR - " + user + " echec d'authentification (username inconnu)");
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
			System.out.println(this.nodeName + " - INFO - " + user + " droits OK");
			return true;
		} else {
			System.out.println(this.nodeName + " - ERR - " + user + " droits NOK");
			return false;
		}
	}

	/**
	 * Authentifie l'utilisateur et envoi la demande d'enregistrement à l'AC et retourne le certificat
	 */
	@Override
	public Certificat saveCertificat(String clepublique, String proprietaire, String mdp, String dateExpiration, String usage) throws erreur_authent {

		// Si l'authentification n'est pas bonne
		if (!this.authentifier(proprietaire, mdp)) {
			// faire afficher "ERR - Enregistrement - Echec d'authentification"
			System.out.println(this.nodeName + " - ERR - " + proprietaire + " echec d'authentification (username inconnu)");
			return null; 
		} else {
			// faire enregistrer sur l'AC supérieure
			AC ac = ACHelper.narrow(Tools.findObjByORBName(this.nodeName, EntityName.AC_SERVER));
			Certificat newCertif = ac.enregistrer(clepublique, proprietaire, dateExpiration, usage);

			System.out.println(this.nodeName + " - INFO - " + proprietaire + " Enregistrement aupr�s de l'AC");
			return newCertif;
		}
	}

	/**
	 * Révoque un certificat auprès l'AC
	 */
	@Override
	public boolean revoquer(Certificat certificatPorteur, String mdp, String periode) {

		// Si l'authentification n'est pas OK
		if (!this.authentifier(certificatPorteur.proprietaire, mdp)) {
			// faire afficher "ERR - Revocation - Echec d'authentification"
			System.out.println(this.nodeName + " - ERR - " + certificatPorteur.proprietaire + " echec d'authentification (username inconnu)");
			return false;

		} else { // si l'authentification est OK
			
			// faire révoquer le certificat sur l'AC
			System.out.println(this.nodeName + " - INFO - " + certificatPorteur.proprietaire + " Demande de r�vocation envoy�e � l'AC");
			AC ac = ACHelper.narrow(Tools.findObjByORBName(this.nodeName, EntityName.AC_SERVER));
			try {
				return ac.revoquerCertificat(certificatPorteur, periode);
			} catch (certif_revoque e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}
