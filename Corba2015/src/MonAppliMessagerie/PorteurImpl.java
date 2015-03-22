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
		try {
			this.monCertificat = ae.saveCertificat(this.publicKey, this.username, this.mdp, dateExpiration, usage);
		} catch (Exception e) {
			System.out.println("Erreur d'obtention du certificat aurpès de l'autorité demandée");
		}
		// Affichages de debug
		if (this.monCertificat == null)
			System.out.println("PorteurImpl::saveCertificat() : le certificat est nul");
		else
			System.out.println("PorteurImpl::saveCertificat() : le certificat n'est pas nul");

		System.out.println("PorteurImpl::enregistrerCertificat() : " + this.username + " - INFO - Certificat enregistré");
		return true;
	}

	@Override
	public Certificat getCertificatPorteur() {
		try {
			System.out.println("PorteurImpl::getCertificatPorteur() : " + this.username + " - INFO - Certificat envoyé");

		} catch (Exception e) {
			System.out.println("PorteurImpl::getCertificatPorteur() : " + this.username + " - ERR - Impossible de récupérer le certificat du porteur distant");
		}
		return this.monCertificat;
	}

}
