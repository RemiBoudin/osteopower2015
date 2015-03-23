package ae;

/**
 * Holder class for : AE
 * 
 * @author OpenORB Compiler
 */
final public class AEHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal AE value
     */
    public ae.AE value;

    /**
     * Default constructor
     */
    public AEHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public AEHolder(ae.AE initial)
    {
        value = initial;
    }

    /**
     * Read AE from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = AEHelper.read(istream);
    }

    /**
     * Write AE into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        AEHelper.write(ostream,value);
    }

    /**
     * Return the AE TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return AEHelper.type();
    }

}
