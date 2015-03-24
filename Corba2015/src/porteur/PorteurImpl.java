/**
 * 
 */
package porteur;

import outils.EntityName;
import outils.Tools;
import certificat.Certificat;
import ae.AE;
import ae.AEHelper;

/**
 * @author Rémi
 * Classe implémentant la fonction de PORTEUR
 */
public class PorteurImpl extends PorteurPOA {

	private Certificat monCertificat;
	private String publicKey;
	private String privateKey;
	private String username;
	private String mdp;
	private org.omg.CosNaming.NamingContext namingService;

	/**
	 * Constructeur de PorteurImpl
	 * @param username le nom de l'utilisateur
	 * @param mdp le mot de passe de l'utilisateur
	 */
	public PorteurImpl(String username, String mdp) {
		this.username = username;
		this.mdp = mdp;

		String[] keys = Tools.generateKeys(this.username);
		this.publicKey = keys[0];
		this.privateKey = keys[1];
	}

	/**
	 * Méthode permetant d'enregistrer un nouveau certificat aurpès d'un noeud de certification
	 * @param usage l'usage auquel est destiné le certificat
	 * @param dateExpiration la date à laquelle le certificat doit expirer
	 * @param nodeName nom du noeud auquel on veut se raccrocher
	 * @return TRUE si la demande d'enregistrement a été transmise à l'IDE
	 */
	public boolean enregistrerCertificat(String usage, String dateExpiration, String nodeName) {
		AE ae = AEHelper.narrow(Tools.findObjByORBName(nodeName, EntityName.AE_SERVER));
		try {
			this.monCertificat = ae.saveCertificat(this.publicKey, this.username, this.mdp, dateExpiration, usage);
		} catch (Exception e) {
			Tools.showMessage(Tools.MSG_ERR, "PorteurImpl", "enregistrerCertificat", "Erreur d'obtention du certificat aurpès de l'autorité demandée");
		}
		// Affichages de debug
		if (this.monCertificat == null)
			Tools.showMessage(Tools.MSG_DEBUG, "PorteurImpl", "enregistrerCertificat", "le certificat est nul");
		else {
			Tools.showMessage(Tools.MSG_DEBUG, "PorteurImpl", "enregistrerCertificat", "le certificat n'est pas nul");
			Tools.showMessage(Tools.MSG_INFO, "PorteurImpl", "enregistrerCertificat", this.username + " - Certificat enregistré");
		}

		return true;
	}

	@Override
	public Certificat getCertificatPorteur() {
		try {
			Tools.showMessage(Tools.MSG_INFO, "PorteurImpl", "getCertificatPorteur", this.username + " - Certificat envoyé");

		} catch (Exception e) {
			Tools.showMessage(Tools.MSG_ERR, "PorteurImpl", "getCertificatPorteur", this.username + " - Impossible de récupérer le certificat du porteur distant");
		}
		return this.monCertificat;
	}

}
