package MonAppliMessagerie;

/** 
 * Helper class for : Porteur
 *  
 * @author OpenORB Compiler
 */ 
public class PorteurHelper
{
    /**
     * Insert Porteur into an any
     * @param a an any
     * @param t Porteur value
     */
    public static void insert(org.omg.CORBA.Any a, MonAppliMessagerie.Porteur t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract Porteur from an any
     * @param a an any
     * @return the extracted Porteur value
     */
    public static MonAppliMessagerie.Porteur extract(org.omg.CORBA.Any a)
    {
        if (!a.type().equal(type()))
            throw new org.omg.CORBA.MARSHAL();
        try {
            return MonAppliMessagerie.PorteurHelper.narrow(a.extract_Object());
        } catch (final org.omg.CORBA.BAD_PARAM e) {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the Porteur TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc(id(),"Porteur");
        }
        return _tc;
    }

    /**
     * Return the Porteur IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:MonAppliMessagerie/Porteur:1.0";

    /**
     * Read Porteur from a marshalled stream
     * @param istream the input stream
     * @return the readed Porteur value
     */
    public static MonAppliMessagerie.Porteur read(org.omg.CORBA.portable.InputStream istream)
    {
        return(MonAppliMessagerie.Porteur)istream.read_Object(MonAppliMessagerie._PorteurStub.class);
    }

    /**
     * Write Porteur into a marshalled stream
     * @param ostream the output stream
     * @param value Porteur value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, MonAppliMessagerie.Porteur value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to Porteur
     * @param obj the CORBA Object
     * @return Porteur Object
     */
    public static Porteur narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Porteur)
            return (Porteur)obj;

        if (obj._is_a(id()))
        {
            _PorteurStub stub = new _PorteurStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to Porteur
     * @param obj the CORBA Object
     * @return Porteur Object
     */
    public static Porteur unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof Porteur)
            return (Porteur)obj;

        _PorteurStub stub = new _PorteurStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
