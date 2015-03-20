package MonAppliMessagerie;

/**
 * Holder class for : Porteur
 * 
 * @author OpenORB Compiler
 */
final public class PorteurHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal Porteur value
     */
    public MonAppliMessagerie.Porteur value;

    /**
     * Default constructor
     */
    public PorteurHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public PorteurHolder(MonAppliMessagerie.Porteur initial)
    {
        value = initial;
    }

    /**
     * Read Porteur from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = PorteurHelper.read(istream);
    }

    /**
     * Write Porteur into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        PorteurHelper.write(ostream,value);
    }

    /**
     * Return the Porteur TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return PorteurHelper.type();
    }

}
