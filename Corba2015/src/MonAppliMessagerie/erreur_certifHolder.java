package MonAppliMessagerie;

/**
 * Holder class for : erreur_certif
 * 
 * @author OpenORB Compiler
 */
final public class erreur_certifHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal erreur_certif value
     */
    public MonAppliMessagerie.erreur_certif value;

    /**
     * Default constructor
     */
    public erreur_certifHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public erreur_certifHolder(MonAppliMessagerie.erreur_certif initial)
    {
        value = initial;
    }

    /**
     * Read erreur_certif from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = erreur_certifHelper.read(istream);
    }

    /**
     * Write erreur_certif into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        erreur_certifHelper.write(ostream,value);
    }

    /**
     * Return the erreur_certif TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return erreur_certifHelper.type();
    }

}
