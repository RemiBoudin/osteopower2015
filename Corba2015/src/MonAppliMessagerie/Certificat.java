package MonAppliMessagerie;

/**
 * Struct definition : Certificat
 * 
 * @author OpenORB Compiler
 */
public final class Certificat implements org.omg.CORBA.portable.IDLEntity {
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

	public Certificat(Certificat c) {
		this.proprietaire = c.proprietaire;
		this.IOR_AV = c.IOR_AV;
		this.Num_Unique = c.Num_Unique;
		this.ValiditeDebut = c.ValiditeDebut;
		this.ValiditeFin = c.ValiditeFin;
		this.ClePubClient = c.ClePubClient;
		this.usage = c.usage;
		this.Signature = c.Signature;
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

}
