package MonAppliMessagerie;

import java.util.Hashtable;


public class ACimpl extends ACPOA{

	private Certificat certificat;
	private Hashtable<Integer,Certificat> listeCertificats; 
	private String privateKey;
	private String publicKey;
	private String id;
	private String idAVsrv;
	private String idAEsrv;
	
	/**
	 * Constructeur de la classe ACimpl
	 */
	public ACimpl(){
		this.publicKey=(Tools.generateKeys(this.id))[0];
		this.privateKey=(Tools.generateKeys(this.id))[1];
		
		this.id=Tools.convertNameToId("lvl1",EntityName.AC_SERVER);
		this.idAVsrv=Tools.convertNameToId("lvl1",EntityName.AV_SERVER);
		this.idAVsrv=Tools.convertNameToId("lvl1",EntityName.AE_SERVER);
		
		this.certificat = new Certificat(null,(short) 1,Tools.getDate(),"never",this.publicKey,"",Tools.genererSignature(this.id));
		
		this.listeCertificats = new Hashtable<Integer,Certificat>();
		this.listeCertificats.put(1, this.certificat);
	}
	
	/**
	 * Permet de générer un certificat
	 * @param publicKey clé publique de l'entité propriétaire du certificat
	 * @param id Nom de l'entité propriétaire du certificat
	 * @param dateExp date d'expiration du certificat
	 * @param date date de début de validité du certificat
	 * @param usage type d'usage attendu pour ce certificat
	 * @return le certificat nouvellement créé
	 */
	private Certificat creerCertificat(String publicKey,String id, String dateExp, String date, String usage){
		int nbCertificats = this.listeCertificats.size();
		Certificat newCertif = new Certificat(this.idAVsrv,(short) (nbCertificats+1),date,dateExp,publicKey,usage,Tools.genererSignature(id));
		return newCertif;
	}
	
	@Override
	/**
	 * renvoi le certificat de l'AC
	 */
	public Certificat getCertificat() {
		// TODO Auto-generated method stub
		return this.certificat;
	}

	@Override
	/**
	 * Lance l'enregistrement d'un certificat, suite à une demande
	 */
	public void enregistrer(String clePublique, short idPorteur, String dateExpiration, String usage) {
		
		// Création du certificat
		Certificat newCertif = this.creerCertificat(usage, clePublique, dateExpiration, Tools.getDate(), usage);
		
		// Stockage du certificat dans la base de certificat de l'AC
		this.stockerCertificat(newCertif);
		
	}

	/**
	 * Stocke un certificat dans la liste des certificats
	 * @param newCertif certificat à stocker dans la liste
	 */
	private void stockerCertificat(Certificat newCertif) {
		// TODO Auto-generated method stub
		int nbCertificats = this.listeCertificats.size();
		this.listeCertificats.put(nbCertificats+1,newCertif);
	}

	@Override
	/**
	 * Fait la demande de révocation d'un certificat auprès de l'AV correspondante
	 */
	public void revoquerCertificat(Certificat certificatPorteur, short id, String periode) throws certif_revoque {
		// revoquer certificat sur l'AV
		
		// en fonction du retour, faire le retour
	}

}
