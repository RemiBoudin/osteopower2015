package ac;

/** 
 * Helper class for : AC
 *  
 * @author OpenORB Compiler
 */ 
public class ACHelper
{
    /**
     * Insert AC into an any
     * @param a an any
     * @param t AC value
     */
    public static void insert(org.omg.CORBA.Any a, ac.AC t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract AC from an any
     * @param a an any
     * @return the extracted AC value
     */
    public static ac.AC extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ac.ACHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the AC TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"AC");
        }
        return _tc;
    }

    /**
     * Return the AC IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/AC:1.0";

    /**
     * Read AC from a marshalled stream
     * @param istream the input stream
     * @return the readed AC value
     */
    public static ac.AC read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ac.AC)istream.read_Object(ac._ACStub.class);
    }

    /**
     * Write AC into a marshalled stream
     * @param ostream the output stream
     * @param value AC value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ac.AC value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to AC
     * @param obj the CORBA Object
     * @return AC Object
     */
    public static AC narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AC)
            return (AC)obj;

        if (obj._is_a(id()))
        {
            _ACStub stub = new _ACStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to AC
     * @param obj the CORBA Object
     * @return AC Object
     */
    public static AC unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AC)
            return (AC)obj;

        _ACStub stub = new _ACStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
