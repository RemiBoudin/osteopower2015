package MonAppliMessagerie;

/**
 * Holder class for : User
 * 
 * @author OpenORB Compiler
 */
final public class UserHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal User value
     */
    public MonAppliMessagerie.User value;

    /**
     * Default constructor
     */
    public UserHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public UserHolder(MonAppliMessagerie.User initial)
    {
        value = initial;
    }

    /**
     * Read User from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = UserHelper.read(istream);
    }

    /**
     * Write User into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        UserHelper.write(ostream,value);
    }

    /**
     * Return the User TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return UserHelper.type();
    }

}
