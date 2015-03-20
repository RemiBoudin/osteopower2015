package MonAppliMessagerie;

/**
 * Holder class for : certif_revoque
 * 
 * @author OpenORB Compiler
 */
final public class certif_revoqueHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal certif_revoque value
     */
    public MonAppliMessagerie.certif_revoque value;

    /**
     * Default constructor
     */
    public certif_revoqueHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public certif_revoqueHolder(MonAppliMessagerie.certif_revoque initial)
    {
        value = initial;
    }

    /**
     * Read certif_revoque from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = certif_revoqueHelper.read(istream);
    }

    /**
     * Write certif_revoque into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        certif_revoqueHelper.write(ostream,value);
    }

    /**
     * Return the certif_revoque TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return certif_revoqueHelper.type();
    }

}
