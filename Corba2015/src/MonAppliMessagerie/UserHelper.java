package MonAppliMessagerie;

/** 
 * Helper class for : User
 *  
 * @author OpenORB Compiler
 */ 
public class UserHelper
{
    /**
     * Insert User into an any
     * @param a an any
     * @param t User value
     */
    public static void insert(org.omg.CORBA.Any a, MonAppliMessagerie.User t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract User from an any
     * @param a an any
     * @return the extracted User value
     */
    public static MonAppliMessagerie.User extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return MonAppliMessagerie.UserHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the User TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"User");
        }
        return _tc;
    }

    /**
     * Return the User IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/User:1.0";

    /**
     * Read User from a marshalled stream
     * @param istream the input stream
     * @return the readed User value
     */
    public static MonAppliMessagerie.User read(org.omg.CORBA.portable.InputStream istream)
    {
        return(MonAppliMessagerie.User)istream.read_Object(MonAppliMessagerie._UserStub.class);
    }

    /**
     * Write User into a marshalled stream
     * @param ostream the output stream
     * @param value User value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.User value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to User
     * @param obj the CORBA Object
     * @return User Object
     */
    public static User narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof User)
            return (User)obj;

        if (obj._is_a(id()))
        {
            _UserStub stub = new _UserStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to User
     * @param obj the CORBA Object
     * @return User Object
     */
    public static User unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof User)
            return (User)obj;

        _UserStub stub = new _UserStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
