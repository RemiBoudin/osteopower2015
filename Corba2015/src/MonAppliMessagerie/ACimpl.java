package MonAppliMessagerie;

import java.util.Hashtable;


public class ACimpl extends ACPOA{

	private Certificat certificat;
	private Hashtable<String,Certificat> listeCertificats; 
	private String privateKey;
	private String id;
		
	public ACimpl(){
		this.id="AC";
		this.certificat = new Certificat();

	}
	
	private Certificat creerCertificat(String publicKey,String id, String dateExp, String date, String usage){
		return new Certificat(null,0,Tools.getDate(),dateExp,publicKey,usage,Tools.genererSignature(IOR, idCertif));
	}
	@Override
	public Certificat getCertificat() {
		// TODO Auto-generated method stub
		return this.certificatAC;
	}

	@Override
	public void enregistrer(String clePublique, short idPorteur,
			String dateExpiration, String usage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void revoquerCertificat(Certificat certificatPorteur, short id,
			String periode) throws certif_revoque {
		// TODO Auto-generated method stub
		
	}

}
