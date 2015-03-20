package MonAppliMessagerie;

/**
 * Exception definition : certif_revoque
 * 
 * @author OpenORB Compiler
 */
public final class certif_revoque extends org.omg.CORBA.UserException
{
    /**
     * Exception member certificat_revoque
     */
    public String certificat_revoque;

    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public certif_revoque()
    {
        super(certif_revoqueHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param certificat_revoque certificat_revoque exception member
     * @param id id exception member
     */
    public certif_revoque(String certificat_revoque, short id)
    {
        super(certif_revoqueHelper.id());
        this.certificat_revoque = certificat_revoque;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param certificat_revoque certificat_revoque exception member
     * @param id id exception member
     */
    public certif_revoque(String orb_reason, String certificat_revoque, short id)
    {
        super(certif_revoqueHelper.id() +" " +  orb_reason);
        this.certificat_revoque = certificat_revoque;
        this.id = id;
    }

}
