package MonAppliMessagerie;

import java.util.Hashtable;

public class ACimpl extends ACPOA {

	private Certificat certificat;
	private Hashtable<Integer, Certificat> listeCertificats;
	private String privateKey;
	private String publicKey;
	private String id;
	private String idAVsrv;
	private String idAEsrv;

	/**
	 * Constructeur de la classe ACimpl
	 */
	public ACimpl(String username) {
		this.publicKey = (Tools.generateKeys(this.id))[0];
		this.privateKey = (Tools.generateKeys(this.id))[1];

		this.id = Tools.convertNameToId(username, EntityName.AC_SERVER);
		this.idAVsrv = Tools.convertNameToId(username, EntityName.AV_SERVER);
		this.idAVsrv = Tools.convertNameToId(username, EntityName.AE_SERVER);

		this.certificat = new Certificat(this.id, null, (short) 1,
				Tools.getDate(), "never", this.publicKey, "",
				Tools.genererSignature(this.id));

		this.listeCertificats = new Hashtable<Integer, Certificat>();
		this.listeCertificats.put(1, this.certificat);
	}

	/**
	 * Permet de g�n�rer un certificat
	 * 
	 * @param publicKey
	 *            cl� publique de l'entit� propri�taire du certificat
	 * @param id
	 *            Nom de l'entit� propri�taire du certificat
	 * @param dateExp
	 *            date d'expiration du certificat
	 * @param date
	 *            date de d�but de validit� du certificat
	 * @param usage
	 *            type d'usage attendu pour ce certificat
	 * @return le certificat nouvellement cr��
	 */
	private Certificat creerCertificat(String publicKey, String pptaire,
			String dateExp, String date, String usage) {
		int nbCertificats = this.listeCertificats.size();
		Certificat newCertif = new Certificat(pptaire, this.idAVsrv,
				(short) (nbCertificats + 1), date, dateExp, publicKey, usage,
				Tools.genererSignature(id));

		System.out.println(this.id + " - INFO - Certificat cr�� pour "
				+ pptaire);

		return newCertif;
	}

	@Override
	/**
	 * renvoi le certificat de l'AC
	 */
	public Certificat getCertificat() {
		// TODO Auto-generated method stub
		System.out.println(this.id + " - INFO - Certificat personnel envoy�");
		return this.certificat;
	}

	/**
	 * Stocke un certificat dans la liste des certificats
	 * 
	 * @param newCertif
	 *            certificat � stocker dans la liste
	 */
	private void stockerCertificat(Certificat newCertif) {
		// TODO Auto-generated method stub

		this.listeCertificats.put((int) newCertif.Num_Unique, newCertif);
		System.out.println(this.id + " - INFO - Certificat de "
				+ newCertif.proprietaire + " stock� avec l'id "
				+ newCertif.Num_Unique);

	}

	/**
	 * G�n�re un certificat et le stocke dans la base, suite � une demande.
	 */
	@Override
	public void enregistrer(String clePublique, String proprietaire,
			String dateExpiration, String usage) {
		// TODO Auto-generated method stub

		// Cr�ation du certificat
		Certificat newCertif = this.creerCertificat(usage, clePublique,
				dateExpiration, Tools.getDate(), usage);

		// Stockage du certificat dans la base de certificat de l'AC
		this.stockerCertificat(newCertif);

		// Publier aupr�s de l'AE
		System.out.println(this.id + " - INFO - " + proprietaire
				+ " Publication du certificat aupr�s de l'AE");

	}

	/**
	 * Revoque un certificat aupr�s de l'AE et fait le retour au porteur
	 */
	@Override
	public void revoquerCertificat(Certificat certificatPorteur, String periode)
			throws certif_revoque {
		// revoquer certificat sur l'AV
		System.out.println(this.id + " - INFO - "
				+ certificatPorteur.proprietaire
				+ " Demande de r�vocation aurp�s de l'AV");

		// Faire le retour � l'AE
		System.out.println(this.id + " - INFO - "
				+ certificatPorteur.proprietaire
				+ " R�sultat de la r�vocation envoy�e � l'AE");
	}

}
