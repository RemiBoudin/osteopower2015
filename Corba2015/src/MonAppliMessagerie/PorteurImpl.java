/**
 * 
 */
package MonAppliMessagerie;

/**
 * @author Rémi
 *
 */
public class PorteurImpl extends PorteurPOA {

	private Certificat monCertificat;
	private String publicKey;
	private String privateKey;
	private String username;
	private String mdp;
	private org.omg.CosNaming.NamingContext namingService;

	public PorteurImpl(String username, String mdp) {
		// this.namingService=namingService;
		this.username = username;
		this.mdp = mdp;

		String[] keys = Tools.generateKeys(this.username);
		this.publicKey = keys[0];
		this.privateKey = keys[1];
	}

	public boolean enregistrerCertificat(String usage, String dateExpiration, String nodeName) {
		AE ae = AEHelper.narrow(Tools.findObjByORBName(nodeName, EntityName.AE_SERVER));

		this.monCertificat = ae.saveCertificat(this.publicKey, this.username, this.mdp, dateExpiration, usage);

		// Affichages de debug
		if (this.monCertificat == null)
			Tools.showMessage(Tools.MSG_DEBUG, "PorteurImpl", "enregistrerCertificat", "le certificat est null");
		else
			Tools.showMessage(Tools.MSG_DEBUG, "PorteurImpl", "enregistrerCertificat", "le certificat n'est pas null");

		Tools.showMessage(Tools.MSG_INFO, "PorteurImpl","enregistrerCertificat", this.username + " - Certificat enregistré");
		return true;
	}

	@Override
	public Certificat getCertificatPorteur() {
		try {
		Tools.showMessage(Tools.MSG_INFO, "PorteurImpl","getCertificatPorteur", this.username + " - Certificat envoyé");

		} catch (Exception e){
			System.out.println("PorteurImpl::getCertificatPorteur() : "+this.username + " - ERR - Impossible de récupérer le certificat du porteur distant");
		}
		return this.monCertificat;
	}

}
