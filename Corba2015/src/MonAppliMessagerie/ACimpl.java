package MonAppliMessagerie;

import java.util.Hashtable;

public class ACimpl extends ACPOA {

	private Certificat certificat;
	private Hashtable<Integer, Certificat> listeCertificats;
	private String privateKey;
	private String publicKey;
	private String nodeName;
	private org.omg.CosNaming.NamingContext namingService;

	/**
	 * Constructeur de la classe ACimpl
	 */
	public ACimpl(String nodeName) {
		this.nodeName = nodeName;
		// this.namingService = namingService;

		this.publicKey = (Tools.generateKeys(this.nodeName))[0];
		this.privateKey = (Tools.generateKeys(this.nodeName))[1];

		this.certificat = new Certificat(this.nodeName, "", (short) 1, Tools.getDate(), "never", this.publicKey, "", Tools.genererSignature(this.nodeName));

		this.listeCertificats = new Hashtable<Integer, Certificat>();
		this.listeCertificats.put(1, this.certificat);
	}

	/**
	 * Permet de générer un certificat
	 * 
	 * @param publicKey
	 *            clé publique de l'entité propriétaire du certificat
	 * @param id
	 *            Nom de l'entité propriétaire du certificat
	 * @param dateExp
	 *            date d'expiration du certificat
	 * @param date
	 *            date de début de validité du certificat
	 * @param usage
	 *            type d'usage attendu pour ce certificat
	 * @return le certificat nouvellement créé
	 */
	private Certificat creerCertificat(String publicKey, String pptaire, String dateExp, String date, String usage) {

		System.out.println("ACimpl::creerCertificat() : nom propriétaire : " + pptaire);
		int nbCertificats = this.listeCertificats.size();
		Certificat newCertif = new Certificat(pptaire, this.nodeName, (short) (nbCertificats + 1), date, dateExp, publicKey, usage, Tools.genererSignature(nodeName));

		System.out.println("ACimpl::creerCertificat() : " + this.nodeName + " - INFO - Certificat créé pour " + pptaire);

		return new Certificat(newCertif);
	}

	@Override
	/**
	 * renvoi le certificat de l'AC
	 */
	public Certificat getCertificat() {
		System.out.println("ACimpl::getCertificat() : " + this.nodeName + " - INFO - Certificat personnel envoyé");
		return new Certificat(this.certificat);
	}

	/**
	 * Stocke un certificat dans la liste des certificats
	 * 
	 * @param newCertif
	 *            certificat à stocker dans la liste
	 */
	private void stockerCertificat(Certificat newCertif) {
		this.listeCertificats.put((int) newCertif.Num_Unique, newCertif);
		System.out.println("ACimpl::stockerCertificat() : " + this.nodeName + " - INFO - Certificat de " + newCertif.proprietaire + " stocké avec l'id " + newCertif.Num_Unique);
	}

	/**
	 * Génère un certificat et le stocke dans la base, suite à une demande.
	 */
	@Override
	public Certificat enregistrer(String clePublique, String proprietaire, String dateExpiration, String usage) {

		// Création du certificat
		Certificat newCertif = this.creerCertificat(clePublique, proprietaire, dateExpiration, Tools.getDate(), usage);

		// Stockage du certificat dans la base de certificat de l'AC
		this.stockerCertificat(newCertif);

		// Retour du certif
		System.out.println("ACimpl::enregistrer() : " + this.nodeName + " - INFO - " + proprietaire + " Publication du certificat auprès de l'AE");
		return new Certificat(newCertif);
	}

	/**
	 * Revoque un certificat auprès de l'AE et fait le retour au porteur
	 */
	@Override
	public boolean revoquerCertificat(Certificat certificatPorteur, String periode) {

		// revoquer certificat sur l'AV
		AV av = AVHelper.narrow(Tools.findObjByORBName2(this.nodeName, EntityName.AV_SERVER));

		System.out.println("ACimpl::revoquerCertificat() : " + this.nodeName + " - INFO - " + certificatPorteur.proprietaire + " Demande de révocation auprès de l'AV");
		if (av.revoquerCertificat(certificatPorteur, periode)) {
			System.out.println("ACimpl::revoquerCertificat() : " + this.nodeName + " - INFO - " + certificatPorteur.proprietaire + " Résultat de la révocation envoyée à l'AE");
			return true;
		} else {
			return false;
		}
	}

}
