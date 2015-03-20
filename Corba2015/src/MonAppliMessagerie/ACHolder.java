package MonAppliMessagerie;

/**
 * Holder class for : AC
 * 
 * @author OpenORB Compiler
 */
final public class ACHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal AC value
     */
    public MonAppliMessagerie.AC value;

    /**
     * Default constructor
     */
    public ACHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public ACHolder(MonAppliMessagerie.AC initial)
    {
        value = initial;
    }

    /**
     * Read AC from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = ACHelper.read(istream);
    }

    /**
     * Write AC into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        ACHelper.write(ostream,value);
    }

    /**
     * Return the AC TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return ACHelper.type();
    }

}
