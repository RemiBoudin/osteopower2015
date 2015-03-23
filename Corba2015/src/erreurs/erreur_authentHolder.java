package erreurs;

/**
 * Holder class for : erreur_authent
 * 
 * @author OpenORB Compiler
 */
final public class erreur_authentHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal erreur_authent value
     */
    public erreurs.erreur_authent value;

    /**
     * Default constructor
     */
    public erreur_authentHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public erreur_authentHolder(erreurs.erreur_authent initial)
    {
        value = initial;
    }

    /**
     * Read erreur_authent from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = erreur_authentHelper.read(istream);
    }

    /**
     * Write erreur_authent into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        erreur_authentHelper.write(ostream,value);
    }

    /**
     * Return the erreur_authent TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return erreur_authentHelper.type();
    }

}
