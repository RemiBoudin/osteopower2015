package MonAppliMessagerie;

import java.util.Hashtable;

public class AEimpl extends AEPOA {

	private Hashtable<String, String> listeIdClientPorteur;
	private String idACsrv;
	private String id;

	/**
	 * Constructeur de la classe AE
	 * @param username nom � donner � l'AE
	 */
	public AEimpl(String username) {
		this.id = Tools.convertNameToId(username, EntityName.AE_SERVER);
		this.idACsrv = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.listeIdClientPorteur = new Hashtable<String, String>();

		this.listeIdClientPorteur.put("titi", "titi");
		this.listeIdClientPorteur.put("toto", "toto");

	}

	/**
	 * Permet de lancer la proc�dure de cr�ation d'un certificat, puis retourne ce nouveau certificat
	 */
	@Override
	public Certificat saveCertificat(String clepublique, short user, String mdp,
			String dateExpiration, String usage) throws erreur_authent {
		// Envoi de la demande � l'AC sup�rieure
		Certificat newCertif = null;
		
		if(!this.authentifier(user, mdp)){
			// faire afficher "ERR - Enregistrement - Echec d'authentification"
		} else {
			// faire enregistrer sur l'AC distant
		}
		
		
		return null;
	}

	/**
	 * Permet de r�voquer un certificat donn� � la date d'effet souhait�e
	 */
	@Override
	public void revoquer(Certificat certificatPorteur, short user, String mdp,
			String periode) {
		if(!this.authentifier(user, mdp)){
			// faire afficher "ERR - Revocation - Echec d'authentification"
		} else {
			// faire r�voquer sur l'AC
		}
	}

	/**
	 * 
	 */
	@Override
	public void publier(Certificat certificatPorteur) {
		// faire afficher "INFO - Enregistrement - Enregistrement OK" sur le client

	}

	/**
	 * V�rifie la combinaison identifiant/mot de passe dans la base
	 * @param user nom de l'utilisateur
	 * @param mdp mot de passe de l'utilisateur
	 * @return true si OK, false si non OK
	 */
	private boolean authentifier(String user, String mdp) {
		if (this.listeIdClientPorteur.containsKey(user)) {
			if (this.listeIdClientPorteur.get(user).equals(mdp)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * V�rifie si l'utilisateur a le droit d'utiliser le certificat pour un usage donn�
	 * @param user nom de l'utilisateur
	 * @param certifPorteur certificat du porteur li� � l'utilisateur
	 * @param usage type d'utilisation du certificat
	 * @return true si OK, false si non OK
	 */
	private boolean verifierDroits(String user, Certificat certifPorteur, String usage) {
		// si usage certif == usage donn�
		if (true) {
			return true;	
		} else {
			return false;
		}
	}
	
	
}
