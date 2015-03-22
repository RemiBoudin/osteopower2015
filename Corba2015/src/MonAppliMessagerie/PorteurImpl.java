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
	
	public PorteurImpl(String username,String mdp){
		//this.namingService=namingService;
		this.username = username;
		this.mdp=mdp;
		
		String[] keys = Tools.generateKeys(this.username);
		this.publicKey=keys[0];
		this.privateKey=keys[1];
	}
	
	public boolean enregistrerCertificat(String usage, String dateExpiration, String nodeName){
		AE ae = AEHelper.narrow(Tools.findObjByORBName(nodeName, EntityName.AE_SERVER));
		//try {
			//this.monCertificat = ae.saveCertificat(this.publicKey, this.username, this.mdp, dateExpiration, usage);	
			System.out.println("PorteurImple::enregistrerCertificat() : "+this.username + " - INFO - Certificat enregistré");
			return true;
		//} catch (erreur_authent e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			//return false;
		//}
	}
	
	@Override
	public Certificat getCertificatPorteur() {
		System.out.println(this.username + " - INFO - Certificat envoyé");
		return this.monCertificat;
	}

}
