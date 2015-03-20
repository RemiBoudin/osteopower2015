package MonAppliMessagerie;

/** 
 * Helper class for : AV
 *  
 * @author OpenORB Compiler
 */ 
public class AVHelper
{
    /**
     * Insert AV into an any
     * @param a an any
     * @param t AV value
     */
    public static void insert(org.omg.CORBA.Any a, MonAppliMessagerie.AV t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract AV from an any
     * @param a an any
     * @return the extracted AV value
     */
    public static MonAppliMessagerie.AV extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return MonAppliMessagerie.AVHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the AV TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"AV");
        }
        return _tc;
    }

    /**
     * Return the AV IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/AV:1.0";

    /**
     * Read AV from a marshalled stream
     * @param istream the input stream
     * @return the readed AV value
     */
    public static MonAppliMessagerie.AV read(org.omg.CORBA.portable.InputStream istream)
    {
        return(MonAppliMessagerie.AV)istream.read_Object(MonAppliMessagerie._AVStub.class);
    }

    /**
     * Write AV into a marshalled stream
     * @param ostream the output stream
     * @param value AV value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.AV value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to AV
     * @param obj the CORBA Object
     * @return AV Object
     */
    public static AV narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AV)
            return (AV)obj;

        if (obj._is_a(id()))
        {
            _AVStub stub = new _AVStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to AV
     * @param obj the CORBA Object
     * @return AV Object
     */
    public static AV unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AV)
            return (AV)obj;

        _AVStub stub = new _AVStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
