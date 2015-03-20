package MonAppliMessagerie;

/**
 * Holder class for : AV
 * 
 * @author OpenORB Compiler
 */
final public class AVHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal AV value
     */
    public MonAppliMessagerie.AV value;

    /**
     * Default constructor
     */
    public AVHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AVHolder(MonAppliMessagerie.AV initial)
    {
        value = initial;
    }

    /**
     * Read AV from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AVHelper.read(istream);
    }

    /**
     * Write AV into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AVHelper.write(ostream,value);
    }

    /**
     * Return the AV TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AVHelper.type();
    }

}
