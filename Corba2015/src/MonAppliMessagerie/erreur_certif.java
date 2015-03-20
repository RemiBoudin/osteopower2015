package MonAppliMessagerie;

/**
 * Exception definition : erreur_certif
 * 
 * @author OpenORB Compiler
 */
public final class erreur_certif extends org.omg.CORBA.UserException
{
    /**
     * Exception member erreur_certification
     */
    public String erreur_certification;

    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public erreur_certif()
    {
        super(erreur_certifHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param erreur_certification erreur_certification exception member
     * @param id id exception member
     */
    public erreur_certif(String erreur_certification, short id)
    {
        super(erreur_certifHelper.id());
        this.erreur_certification = erreur_certification;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param erreur_certification erreur_certification exception member
     * @param id id exception member
     */
    public erreur_certif(String orb_reason, String erreur_certification, short id)
    {
        super(erreur_certifHelper.id() +" " +  orb_reason);
        this.erreur_certification = erreur_certification;
        this.id = id;
    }

}
