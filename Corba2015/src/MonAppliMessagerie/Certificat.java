package MonAppliMessagerie;

/**
 * Struct definition : Certificat
 * 
 * @author OpenORB Compiler
 */
public final class Certificat implements org.omg.CORBA.portable.IDLEntity, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5121333938915291460L;

	/**
	 * Struct member proprietaire
	 */
	public String proprietaire;

	/**
	 * Struct member IOR_AV
	 */
	public String IOR_AV;

	/**
	 * Struct member Num_Unique
	 */
	public short Num_Unique;

	/**
	 * Struct member ValiditeDebut
	 */
	public String ValiditeDebut;

	/**
	 * Struct member ValiditeFin
	 */
	public String ValiditeFin;

	/**
	 * Struct member ClePubClient
	 */
	public String ClePubClient;

	/**
	 * Struct member usage
	 */
	public String usage;

	/**
	 * Struct member Signature
	 */
	public String Signature;

	/**
	 * Default constructor
	 */
	public Certificat() {
	}

	/**
	 * Constructor with fields initialization
	 * 
	 * @param proprietaire
	 *            proprietaire struct member
	 * @param IOR_AV
	 *            IOR_AV struct member
	 * @param Num_Unique
	 *            Num_Unique struct member
	 * @param ValiditeDebut
	 *            ValiditeDebut struct member
	 * @param ValiditeFin
	 *            ValiditeFin struct member
	 * @param ClePubClient
	 *            ClePubClient struct member
	 * @param usage
	 *            usage struct member
	 * @param Signature
	 *            Signature struct member
	 */
	public Certificat(String proprietaire, String IOR_AV, short Num_Unique, String ValiditeDebut, String ValiditeFin, String ClePubClient, String usage, String Signature) {
		this.proprietaire = proprietaire;
		this.IOR_AV = IOR_AV;
		this.Num_Unique = Num_Unique;
		this.ValiditeDebut = ValiditeDebut;
		this.ValiditeFin = ValiditeFin;
		this.ClePubClient = ClePubClient;
		this.usage = usage;
		this.Signature = Signature;
	}

	public Certificat(Certificat certificat) {
		this.proprietaire = certificat.proprietaire;
		this.IOR_AV = certificat.IOR_AV;
		this.Num_Unique = certificat.Num_Unique;
		this.ValiditeDebut = certificat.ValiditeDebut;
		this.ValiditeFin = certificat.ValiditeFin;
		this.ClePubClient = certificat.ClePubClient;
		this.usage = certificat.usage;
		this.Signature = certificat.Signature;
	}

	public Object clone() {
		Certificat certificat = null;
		try {
			// On récupère l'instance à renvoyer par l'appel de la
			// méthode super.clone()
			certificat = (Certificat) super.clone();
		} catch (CloneNotSupportedException cnse) {
			// Ne devrait jamais arriver car nous implémentons
			// l'interface Cloneable
			cnse.printStackTrace(System.err);
		}

		// on renvoie le clone
		return certificat;
	}

}
