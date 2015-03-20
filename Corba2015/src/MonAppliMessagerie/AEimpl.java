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
	 *            nom � donner � l'AE
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
				+ certificatPorteur.proprietaire + " publi� !");

	}

	/**
	 * V�rifie la combinaison identifiant/mot de passe dans la base
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
						+ " authentifi� avec succ�s");

				return true;
			} else {
				System.out.println(this.id + " - ERR - " + user
						+ " echec d'authentification (mdp erron�)");
				return false;

			}
		} else {
			System.out.println(this.id + " - ERR - " + user
					+ " echec d'authentification (username inconnu)");
			return false;

		}
	}

	/**
	 * V�rifie si l'utilisateur a le droit d'utiliser le certificat pour un
	 * usage donn�
	 * 
	 * @param user
	 *            nom de l'utilisateur
	 * @param certifPorteur
	 *            certificat du porteur li� � l'utilisateur
	 * @param usage
	 *            type d'utilisation du certificat
	 * @return true si OK, false si non OK
	 */
	private boolean verifierDroits(String user, Certificat certifPorteur,
			String usage) {
		// si usage certif == usage donn�
		if (true) {
			System.out.println(this.id + " - INFO - " + user + " droits OK");
			return true;
		} else {
			System.out.println(this.id + " - ERR - " + user + " droits NOK");
			return false;
		}
	}

	/**
	 * Authentifie l'utilisateur et envoi la demande d'enregistrement � l'AC
	 */
	@Override
	public Certificat saveCertificat(String clepublique, String proprietaire,
			String mdp, String dateExpiration, String usage)
			throws erreur_authent {
		// Envoi de la demande � l'AC sup�rieure
		Certificat newCertif = null;

		if (!this.authentifier(proprietaire, mdp)) {
			// faire afficher "ERR - Enregistrement - Echec d'authentification"
			System.out.println(this.id + " - ERR - " + proprietaire
					+ " echec d'authentification (username inconnu)");
		} else {
			// faire enregistrer sur l'AC distant
			System.out.println(this.id + " - INFO - " + proprietaire
					+ " Enregistrement aupr�s de l'AC");
		}

		return null;
	}

	/**
	 * R�voque un certificat aupr�s l'AC
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
			// faire r�voquer sur l'AC
			System.out.println(this.id + " - INFO - "
					+ certificatPorteur.proprietaire
					+ " Demande de r�vocation envoy�e � l'AC");

		}
	}

}
