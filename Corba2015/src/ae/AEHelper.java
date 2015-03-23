package ae;

/** 
 * Helper class for : AE
 *  
 * @author OpenORB Compiler
 */ 
public class AEHelper
{
    /**
     * Insert AE into an any
     * @param a an any
     * @param t AE value
     */
    public static void insert(org.omg.CORBA.Any a, ae.AE t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract AE from an any
     * @param a an any
     * @return the extracted AE value
     */
    public static ae.AE extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return ae.AEHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the AE TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"AE");
        }
        return _tc;
    }

    /**
     * Return the AE IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/AE:1.0";

    /**
     * Read AE from a marshalled stream
     * @param istream the input stream
     * @return the readed AE value
     */
    public static ae.AE read(org.omg.CORBA.portable.InputStream istream)
    {
        return(ae.AE)istream.read_Object(ae._AEStub.class);
    }

    /**
     * Write AE into a marshalled stream
     * @param ostream the output stream
     * @param value AE value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, ae.AE value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to AE
     * @param obj the CORBA Object
     * @return AE Object
     */
    public static AE narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AE)
            return (AE)obj;

        if (obj._is_a(id()))
        {
            _AEStub stub = new _AEStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to AE
     * @param obj the CORBA Object
     * @return AE Object
     */
    public static AE unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof AE)
            return (AE)obj;

        _AEStub stub = new _AEStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
