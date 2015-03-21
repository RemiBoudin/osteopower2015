/**
 * 
 */
package MonAppliMessagerie;

/**
 * @author Rémi
 *
 */
public class PorteurImpl  extends PorteurPOA{

	private Certificat monCertificat;
	private String publicKey;
	private String privateKey;
	private String username;
	private String mdp;
	private org.omg.CosNaming.NamingContext namingService;
	
	private PorteurImpl(String username,String mdp, org.omg.CosNaming.NamingContext namingService){
		this.namingService=namingService;
		
		String[] keys = Tools.generateKeys(username);
		this.publicKey=keys[0];
		this.privateKey=keys[1];
		
		this.username = username;
		this.mdp=mdp;
	}
	
	@Override
	public Certificat getCertificatPorteur() {
		
		return this.monCertificat;
	}
	
	/////////////
	// INUTILE //
	/////////////
	@Override
	public void afficherMessage(String message) {
		
	}

	/////////////
	// INUTILE //
	/////////////
	@Override
	public void receiveNewCertificat(Certificat newCertif) {
		
	}
	
	private void enregistrerCertificat(String nodeName, String dateExpiration, String usage){
		AE ae = AEHelper.narrow(Tools.findObjByORBName(nodeName, EntityName.AE_SERVER, this.namingService));
		try {
			this.monCertificat=ae.saveCertificat(this.publicKey, this.username, this.mdp, dateExpiration, usage);
		} catch (erreur_authent e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
