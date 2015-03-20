package MonAppliMessagerie;

import java.util.Hashtable;

public class AEimpl extends AEPOA {

	private Hashtable<String, String> listeIdClientPorteur;
	private String idACsrv;
	private String id;

	/**
	 * Constructeur de la classe AE
	 * 
	 * @param username
	 *            nom à donner à l'AE
	 */
	public AEimpl(String username) {
		this.id = Tools.convertNameToId(username, EntityName.AE_SERVER);
		this.idACsrv = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.listeIdClientPorteur = new Hashtable<String, String>();

		this.listeIdClientPorteur.put("titi", "titi");
		this.listeIdClientPorteur.put("toto", "toto");

	}

	/**
	 * 
	 */
	@Override
	public void publier(Certificat certificatPorteur) {
		// faire afficher "INFO - Enregistrement - Enregistrement OK" sur le
		// client
		System.out.println(this.id + " - INFO - Certificat de "
				+ certificatPorteur.proprietaire + " publié !");

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
		if (this.listeIdClientPorteur.containsKey(user)) {
			if (this.listeIdClientPorteur.get(user).equals(mdp)) {
				System.out.println(this.id + " - INFO - " + user
						+ " authentifié avec succès");

				return true;
			} else {
				System.out.println(this.id + " - ERR - " + user
						+ " echec d'authentification (mdp erroné)");
				return false;

			}
		} else {
			System.out.println(this.id + " - ERR - " + user
					+ " echec d'authentification (username inconnu)");
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
	private boolean verifierDroits(String user, Certificat certifPorteur,
			String usage) {
		// si usage certif == usage donné
		if (true) {
			System.out.println(this.id + " - INFO - " + user + " droits OK");
			return true;
		} else {
			System.out.println(this.id + " - ERR - " + user + " droits NOK");
			return false;
		}
	}

	/**
	 * Authentifie l'utilisateur et envoi la demande d'enregistrement à l'AC
	 */
	@Override
	public Certificat saveCertificat(String clepublique, String proprietaire,
			String mdp, String dateExpiration, String usage)
			throws erreur_authent {
		// Envoi de la demande à l'AC supérieure
		Certificat newCertif = null;

		if (!this.authentifier(proprietaire, mdp)) {
			// faire afficher "ERR - Enregistrement - Echec d'authentification"
			System.out.println(this.id + " - ERR - " + proprietaire
					+ " echec d'authentification (username inconnu)");
		} else {
			// faire enregistrer sur l'AC distant
			System.out.println(this.id + " - INFO - " + proprietaire
					+ " Enregistrement auprès de l'AC");
		}

		return null;
	}

	/**
	 * Révoque un certificat auprès l'AC
	 */
	@Override
	public void revoquer(Certificat certificatPorteur, String mdp,
			String periode) {
		// TODO Auto-generated method stub
		if (!this.authentifier(certificatPorteur.proprietaire, mdp)) {
			// faire afficher "ERR - Revocation - Echec d'authentification"
			System.out.println(this.id + " - ERR - "
					+ certificatPorteur.proprietaire
					+ " echec d'authentification (username inconnu)");

		} else {
			// faire révoquer sur l'AC
			System.out.println(this.id + " - INFO - "
					+ certificatPorteur.proprietaire
					+ " Demande de révocation envoyée à l'AC");

		}
	}

}
