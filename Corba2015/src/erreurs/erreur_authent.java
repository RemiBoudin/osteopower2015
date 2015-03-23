package erreurs;

/**
 * Exception definition : erreur_authent
 * 
 * @author OpenORB Compiler
 */
public final class erreur_authent extends org.omg.CORBA.UserException
{
    /**
     * Exception member erreur_authentification
     */
    public String erreur_authentification;

    /**
     * Exception member id
     */
    public short id;

    /**
     * Default constructor
     */
    public erreur_authent()
    {
        super(erreur_authentHelper.id());
    }

    /**
     * Constructor with fields initialization
     * @param erreur_authentification erreur_authentification exception member
     * @param id id exception member
     */
    public erreur_authent(String erreur_authentification, short id)
    {
        super(erreur_authentHelper.id());
        this.erreur_authentification = erreur_authentification;
        this.id = id;
    }

    /**
     * Full constructor with fields initialization
     * @param erreur_authentification erreur_authentification exception member
     * @param id id exception member
     */
    public erreur_authent(String orb_reason, String erreur_authentification, short id)
    {
        super(erreur_authentHelper.id() +" " +  orb_reason);
        this.erreur_authentification = erreur_authentification;
        this.id = id;
    }

}
